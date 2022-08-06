//package com.blog.blogservice.controller;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@AutoConfigureMockMvc
//@SpringBootTest
//class LoginController {
//
//    @Autowired
//    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
//    MockMvc mockMvc;
//    @Autowired
//    MemberController memberController;
//    @Autowired
//    LoginController loginController;
//
//    @Test
//    @DisplayName("login - form")
//    void loginSuccess() throws Exception {
//        //given
//        mockMvc.perform(get("/login")
//                )
//                .andExpect(status().isOk())
//                .andDo(print());
//        //when
//
//        //then
//    }
//}