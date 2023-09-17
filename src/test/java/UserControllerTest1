package itzamigo.SQATProject;

import com.fasterxml.jackson.databind.ObjectMapper;
import itzamigo.SQATProject.entity.User;
import itzamigo.SQATProject.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
@WebMvcTest
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void givenUserObject_whenCreateUser_thenReturnSavedUser() throws Exception {
        User user = User.builder()
                .firstName("Arnur")
                .lastName("Sovetkali")
                .email("arnur.sovetkali@gmail.com")
                .number("+77777777777")
                .build();
        given(userService.saveUser(any(User.class)))
                .willAnswer((invocation -> invocation.getArgument(0)));

        ResultActions response = mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)));

        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", is(user.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(user.getLastName())))
                .andExpect(jsonPath("$.email", is(user.getEmail())))
                .andExpect(jsonPath("$.number", is(user.getNumber())));

    }

    @Test
    public void givenListOfUsers_whenGetAllUsers_thenReturnUsersList() throws Exception {
        List<User> listOfUsers = new ArrayList<>();
        listOfUsers.add(User.builder().firstName("Arnur").lastName("Sovetkali").email("arnur.sovetkali@gmail.com").number("+77477474477").build());
        listOfUsers.add(User.builder().firstName("Peter").lastName("Parker").email("peter_parker0801@gmail.com").number("+15555555555").build());
        given(userService.getAllUsers()).willReturn(listOfUsers);

        ResultActions response = mockMvc.perform(get("/api/users"));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(listOfUsers.size())));
    }

    @Test
    public void givenUserId_whenGetUserById_thenReturnUserObject() throws Exception {
        long id = 1L;
        User user = User.builder()
                .firstName("Arnur")
                .lastName("Sovetkali")
                .email("arnur.sovetkali@gmail.com")
                .number("+77777777777")
                .build();
       // given(userService.getUserById(id)).willReturn(Optional.of(user));


        ResultActions response = mockMvc.perform(get("/api/users/{id}", id));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.firstName", is(user.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(user.getLastName())))
                .andExpect(jsonPath("$.email", is(user.getEmail())))
                .andExpect(jsonPath("$.number", is(user.getNumber())));
    }

    @Test
    public User givenInvalidUserId_whenGetUserById_thenReturnEmpty() throws Exception{
        long id = 1L;
        User user = User.builder()
                .firstName("Arnur")
                .lastName("Sovetkali")
                .email("arnur.sovetkali@gmail.com")
                .number("+77777777777")
                .build();
        given(userService.getUserById(id)).willReturn(givenInvalidUserId_whenGetUserById_thenReturnEmpty());

        ResultActions response = mockMvc.perform(get("/api/employees/{id}", id));

        response.andExpect(status().isNotFound())
                .andDo(print());

        return user;
    }

    @Test
    public void givenUpdatedUser_whenUpdateUser_thenReturnUpdateUserObject() throws Exception{
        long id = 1L;
        User savedUser = User.builder()
                .firstName("Arnur")
                .lastName("Sovetkali")
                .email("arnur.sovetkali@gmail.com")
                .number("+77777777777")
                .build();

        User updatedUser = User.builder()
                .firstName("Naruto")
                .lastName("Uzumaki")
                .email("koboha_hokage@gmail.com")
                .number("+818181818181")
                .build();
       // given(userService.getUserById(id)).willReturn(Optional.of(savedUser));
        //given(userService.updateUser(any(User.class)))
                //.willAnswer((invocation)-> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(put("/api/users/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedUser)));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.firstName", is(updatedUser.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(updatedUser.getLastName())))
                .andExpect(jsonPath("$.email", is(updatedUser.getEmail())))
                .andExpect(jsonPath("$.number", is(updatedUser.getNumber())));
    }

    @Test
    public void givenUserId_whenDeleteUser_thenReturn200() throws Exception{
        long id = 1L;
        willDoNothing().given(userService).deleteUserById(id);

        ResultActions response = mockMvc.perform(delete("/api/users/{id}", id));

        response.andExpect(status().isOk())
                .andDo(print());
    }
}
