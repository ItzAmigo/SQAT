package com.example.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import itzamigo.SQATProject.controller.UserController;
import itzamigo.SQATProject.entity.User;
import itzamigo.SQATProject.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;

@SpringBootTest(classes = UserControllerTest.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        user = new User();
        user.setFirstName("Arnur");
        user.setLastName("Sovetkali");
        user.setEmail("arnur.sovetkali@gmail.com");
        user.setNumber("123-456-7890");
    }

    @Test
    public void testSaveUser() throws Exception {
        when(userService.saveUser(user)).thenReturn(user);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(user)));
    }

    @Test
    public void testGetAllUsers() throws Exception {
        when(userService.getAllUsers()).thenReturn(Arrays.asList(user));

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(Arrays.asList(user))));
    }

    @Test
    public void testGetUserById() throws Exception {
        when(userService.getUserById(1L)).thenReturn(user);

        mockMvc.perform(get("/api/users/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(user)));
    }

    @Test
    public void testUpdateUser(@PathVariable Long id) throws Exception {
        user.setFirstName("Jane");

        when(userService.updateUser(user, id)).thenReturn(user);

        mockMvc.perform(put("/api/users/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(user)));
    }

    @Test
    public void testDeleteUserById() throws Exception {
        mockMvc.perform(delete("/api/users/{id}", 1L))
                .andExpect(status().isOk());
    }
}