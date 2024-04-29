//package com.numadic.restapi.controller;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class VehicleControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    public void testRegisterVehicle() throws Exception {
//        String vehicleJson = "{\"model\":\"Corolla\",\"make\":\"Mazda\",\"registrationNumber\":\"YZA 567 NW\",\"year\":\"2012\"}";
//        
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/vehicles/add")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(vehicleJson))
//                .andExpect(status().isCreated());
//                //.andExpect(content().string("Vehicle registered successfully!"));
//    }
//
//    @Test
//    public void testGetAllVehicles() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/vehicles"))
//                .andExpect(status().isOk())
//                .andExpect(content().string("List of all vehicles"));
//    }
//}
