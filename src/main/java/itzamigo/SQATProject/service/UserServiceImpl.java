package itzamigo.SQATProject.service;

import itzamigo.SQATProject.entity.User;
import itzamigo.SQATProject.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        User user = null;
        if(optionalUser.isPresent()) {
            user = optionalUser.get();
        } else {
            throw new RuntimeException("User not found for id: " + id);
        }
        return user;
    }

    @Override
    public User updateUser(User user, Long id) {
        User userDB = userRepository.findById((id)).get();

        if(Objects.nonNull(user.getFirstName()) && !"".equalsIgnoreCase(user.getFirstName())) {
            userDB.setFirstName((user.getFirstName()));
        }
        if(Objects.nonNull(user.getLastName()) && !"".equalsIgnoreCase(user.getLastName())) {
            userDB.setLastName((user.getLastName()));
        }
        if(Objects.nonNull(user.getEmail()) && !"".equalsIgnoreCase(user.getEmail())) {
            userDB.setEmail((user.getEmail()));
        }
        if(Objects.nonNull(user.getNumber()) && !"".equalsIgnoreCase(user.getNumber())) {
            userDB.setNumber((user.getNumber()));
        }

        return userRepository.save(userDB);
    }

    @Override
    public void deleteUserById(long id) {
        this.userRepository.deleteById(id);
    }
    public void UserService(UserRepository repository) {
        this.userRepository = repository;
    }
}
