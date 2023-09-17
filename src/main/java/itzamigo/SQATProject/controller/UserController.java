package itzamigo.SQATProject.controller;

import itzamigo.SQATProject.entity.User;
import itzamigo.SQATProject.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@Valid @RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return  userService.getUserById(id);
       // return  userService.getUserById(id).map(ResponseEntity::ok).orElseGet
    }



    @PutMapping("/users/{id}")
    public User updateUser(@RequestBody User user, @PathVariable("id") Long id) {
        return userService.updateUser(user, id);
    }

    @DeleteMapping("/users/{id}")
    public String deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "Deleted Successfully";
    }

}
