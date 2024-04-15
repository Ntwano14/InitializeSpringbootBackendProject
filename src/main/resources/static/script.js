document.getElementById("vehicleForm").addEventListener("submit", function(event) {
    event.preventDefault(); // Prevent the form from submitting normally
    
    // Get form data
    var formData = {
        make: document.getElementById("make").value,
        model: document.getElementById("model").value,
        registrationNumber: document.getElementById("registrationNumber").value,
        ownerName: document.getElementById("ownerName").value,
        ownerContactNumber: document.getElementById("ownerContactNumber").value
    };

    // Send POST request to backend
    fetch("/api/vehicles/add", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(formData)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Failed to register vehicle");
        }
        document.getElementById("message").innerText = "Vehicle registered successfully";
    })
    .catch(error => {
        console.error("Error:", error);
        document.getElementById("message").innerText = "Error: " + error.message;
    });
});

// Fetch the latest location data from the backend API
function fetchLatestLocation(vehicleId) {
    fetch(`/api/locations/latest/${vehicleId}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to fetch latest location');
            }
            return response.json();
        })
        .then(data => {
            // Update the markers on the map with the latest location data
            updateMapMarker(data.latitude, data.longitude);
        })
        .catch(error => console.error('Error fetching latest location:', error));
}

// Throttle the fetch requests to reduce the frequency of API calls
function throttle(func, limit) {
    let lastFunc;
    let lastRan;
    return function() {
        const context = this;
        const args = arguments;
        if (!lastRan) {
            func.apply(context, args);
            lastRan = Date.now();
        } else {
            clearTimeout(lastFunc);
            lastFunc = setTimeout(function() {
                if ((Date.now() - lastRan) >= limit) {
                    func.apply(context, args);
                    lastRan = Date.now();
                }
            }, limit - (Date.now() - lastRan));
        }
    }
}

// Throttle the fetchLatestLocation function to reduce API calls
const throttledFetchLatestLocation = throttle(fetchLatestLocation, 5000);

// Periodically fetch the latest location data every few seconds
setInterval(() => {
    const vehicleId = 3; // Change this to the ID of the vehicle you want to track
    throttledFetchLatestLocation(vehicleId);
}, 5000); // Fetch every 5 seconds
