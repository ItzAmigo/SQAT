package itzamigo.SQATProject.service;

import itzamigo.SQATProject.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User saveUser(User user);

    User getUserById(Long id);
    //User updateUser(User user);

    User updateUser(User user, Long id);

    void deleteUserById(long id);
}
