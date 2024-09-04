package com.jobportal.jobportal.services.user;

import com.jobportal.jobportal.entities.user.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    Boolean softDeleteUser(Long id);
}
