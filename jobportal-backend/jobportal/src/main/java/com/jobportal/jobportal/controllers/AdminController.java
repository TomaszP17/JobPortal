package com.jobportal.jobportal.controllers;

import com.jobportal.jobportal.dtos.admin.AdminResponseDTO;
import com.jobportal.jobportal.dtos.admin.CreateAdminRequestDTO;
import com.jobportal.jobportal.entities.user.Admin;
import com.jobportal.jobportal.services.admin.AdminService;
import com.jobportal.jobportal.services.admin.AdminServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping
    public ResponseEntity<String> createAdmin(@Valid @RequestBody CreateAdminRequestDTO createAdminDTO){
        adminService.createAdmin(createAdminDTO);
        return new ResponseEntity<>("Create Admin Successfully!", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AdminResponseDTO>> getAllAdmins(){
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

