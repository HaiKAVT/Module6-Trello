package com.trello.controller;

import com.trello.model.Column;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/columns")
@CrossOrigin("*")
public class ColumnController {
   @GetMapping
    public ResponseEntity<Iterable<Column>> findAll(){
       return null;
   }

}
