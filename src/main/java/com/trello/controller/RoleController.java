package com.trello.controller;

import com.trello.model.Role;
import com.trello.model.Tag;
import com.trello.service.role.IRolesService;
import com.trello.service.role.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
@CrossOrigin("*")
public class RoleController {
    @Autowired
    IRolesService iroleService;

    @GetMapping
    public ResponseEntity<Iterable<Role>> findAll() {
        Iterable<Role> roles = iroleService.findAll();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }
}
