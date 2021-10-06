package com.trello.controller;

import com.trello.model.Board;
import com.trello.service.board.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/boards")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping
    public ResponseEntity<Iterable<Board>> findAll() {
        Iterable<Board> boardIterable = boardService.findAll();
        return new ResponseEntity<>(boardIterable, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Board> findById(@PathVariable Long id) {
        Optional<Board> boardOptional = boardService.findById(id);
        if (!boardOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(boardOptional.get(), HttpStatus.OK);
    }

    @GetMapping("/sort/{id}")
    public ResponseEntity<Board> findByIdSorted(@PathVariable Long id) {
        return new ResponseEntity<>(boardService.findByIdSort(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Board> add(@RequestBody Board board) {
        Board savedBoard = boardService.save(board);
        return new ResponseEntity<>(savedBoard, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Board> update(@RequestBody Board board) {
        Board updatedBoard = boardService.save(board);
        return new ResponseEntity<>(updatedBoard, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Board> deleteById(@PathVariable Long id) {
        Optional<Board> boardOptional = boardService.findById(id);
        if (!boardOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        boardService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/available-to/{searcherId}")
    public ResponseEntity<Iterable<Board>> findAllAvailableToSearcher(@PathVariable Long searcherId) {
        Iterable<Board> boardIterable = boardService.findAllAvailableToSearcher(searcherId);
        return new ResponseEntity<>(boardIterable, HttpStatus.OK);
    }


}
