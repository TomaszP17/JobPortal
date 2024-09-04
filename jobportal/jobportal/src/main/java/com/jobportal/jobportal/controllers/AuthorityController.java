package com.jobportal.jobportal.controllers;

import com.jobportal.jobportal.entities.user.Authority;
import com.jobportal.jobportal.repositories.AuthorityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authorities")
public class AuthorityController {

    private final AuthorityRepository authorityRepository;

    public AuthorityController(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Authority>> getAllAuthorities(){
        return new ResponseEntity<>(authorityRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(params =  "name")
    public ResponseEntity<Authority> getAuthorityByName(@RequestParam String name){
        return new ResponseEntity<>(authorityRepository.findByName(name), HttpStatus.OK);
    }

}
