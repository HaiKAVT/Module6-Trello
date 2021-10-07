package com.trello.controller;

import com.trello.model.User;
import com.trello.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Iterable<User>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
    }
    @PostMapping("/recoverpassword")
    public ResponseEntity<User> findByUserNameAndNickName(@RequestBody User user) {
        User userOptional = userService.findByUsernameAndEmail(user.getUsername(), user.getEmail());
        return new ResponseEntity<>(userOptional, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> add(@RequestBody User user) {
        user.setImage("https://firebasestorage.googleapis.com/v0/b/trello-h3k.appspot.com/o/h3k.png?alt=media&token=2f7182c6-69b5-47a5-a9ab-5e6ad9e7bd91");
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
        return new ResponseEntity<>(userService.update(user), HttpStatus.OK);
    }


    @PutMapping("/{id}/recover")
    public ResponseEntity<User> updateRecoveredUser(@PathVariable Long id, @RequestBody User user) {
        return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteById(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.deleteById(id);
        return new ResponseEntity<>(userOptional.get(), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<Iterable<User>> findUserByKeyword(@PathVariable String keyword) {
        return new ResponseEntity<>(userService.findUserByKeyword(keyword), HttpStatus.OK);
    }

}
