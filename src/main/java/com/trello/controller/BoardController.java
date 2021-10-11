package com.trello.controller;

import com.trello.model.Board;
import com.trello.model.DetailedMember;
import com.trello.model.Member;
import com.trello.model.SimpleBoard;
import com.trello.service.board.BoardService;
import com.trello.service.member.IMemberService;
import com.trello.service.workspaces.WorkspaceService;
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

    @GetMapping("/type/{type}")
    public ResponseEntity<Iterable<Board>> findAllByType(@PathVariable("type") String type){
        Iterable<Board> boardIterable = boardService.findAllBoardByType(type);
        return new ResponseEntity<>(boardIterable,HttpStatus.OK);
    }
        @GetMapping("/owned/{type}/{id}")
    public ResponseEntity<Iterable<Board>> findAllByTypeAndUser(@PathVariable("type") String type,@PathVariable Long id){
        Iterable<Board> boardIterable = boardService.findAllPrivateOwned(type,id);
        return new ResponseEntity<>(boardIterable,HttpStatus.OK);
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
        Member member = new Member(null, savedBoard.getOwner(), true, savedBoard);
        Member savedMember = memberService.save(member);
        System.out.println(savedMember);
        return new ResponseEntity<>(savedBoard, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Board> update(@PathVariable Long id,@RequestBody Board board) {
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
    @GetMapping("/{userId}/owned-boards")
    public ResponseEntity<Iterable<Board>> findAllOwnedBoardsByUserId(@PathVariable Long userId) {
        return new ResponseEntity<>(boardService.findByOwner(userId), HttpStatus.OK);
    }
    @Autowired
    private IMemberService memberService;
    @GetMapping("/{id}/members")
    public ResponseEntity<Iterable<DetailedMember>> findMembersByBoardId(@PathVariable Long id) {
        return new ResponseEntity<>(memberService.getMembersByBoardId(id), HttpStatus.OK);
    }
    @Autowired
    private WorkspaceService workspaceService;
    @GetMapping("/{id}/is-in-workspace")
    public ResponseEntity<Boolean> isBoardInWorkspace(@PathVariable Long id) {
        return new ResponseEntity<>(workspaceService.isBoardInWorkspace(id), HttpStatus.OK);
    }


}
