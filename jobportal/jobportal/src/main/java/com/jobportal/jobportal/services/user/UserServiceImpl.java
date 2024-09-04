package com.jobportal.jobportal.services.user;

import com.jobportal.jobportal.entities.user.User;
import com.jobportal.jobportal.exceptions.UserDoesNotExistException;
import com.jobportal.jobportal.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserDoesNotExistException("User with id: " + id + " has not been found"));
    }

    @Override
    public Boolean softDeleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserDoesNotExistException("User with id: " + id + " has not been found"));

        user.setIsDeleted(true);
        userRepository.save(user);
        return true;
    }


}
