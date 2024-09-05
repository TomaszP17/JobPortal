package com.jobportal.jobportal.controllers;

import com.jobportal.jobportal.dtos.admin.CreateAdminRequestDTO;
import com.jobportal.jobportal.entities.user.Admin;
import com.jobportal.jobportal.services.admin.AdminServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    private final AdminServiceImpl adminService;

    public AdminController(AdminServiceImpl adminService) {
        this.adminService = adminService;
    }

    @PostMapping
    public ResponseEntity<Admin> createAdmin(@Valid @RequestBody CreateAdminRequestDTO createAdminDTO){
        return new ResponseEntity<>(adminService.createAdmin(createAdminDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Admin>> getAllAdmins(){
        return new ResponseEntity<>(adminService.getAllAdmins(), HttpStatus.OK);
    }

    //    @GetMapping
//    public ResponseEntity<List<User>> getAdmins(){
//        return new ResponseEntity<>(adminRepository.findAll(), HttpStatus.OK);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<User> getUserById(@PathVariable Long id) {
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<User> deleteUserById(@PathVariable Long id) {
//        userService.softDeleteUser(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

}

