package com.trello.controller;

import com.trello.model.MemberWorkspace;
import com.trello.model.Workspace;
import com.trello.service.email.EmailService;
import com.trello.service.memberworkspace.MemberWorkspaceService;
import com.trello.service.workspaces.WorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/member-workspace")
public class MemberWorkspaceController {
    @Autowired
    EmailService emailService;

    @Autowired
    private MemberWorkspaceService memberWorkspaceService;


    @GetMapping("")
    public ResponseEntity<Iterable<MemberWorkspace>> showListTag() {
        return new ResponseEntity<>(memberWorkspaceService.findAll(), HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<MemberWorkspace> saveTag(@RequestBody MemberWorkspace memberWorkspace) {

        try {
            emailService.sendVerificationEmail(memberWorkspace.getUser());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(memberWorkspaceService.save(memberWorkspace), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<MemberWorkspace>> findTagById(@PathVariable Long id){
        Optional<MemberWorkspace> memberWorkspaceOptional = memberWorkspaceService.findById(id);
        if (!memberWorkspaceOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(memberWorkspaceOptional, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<MemberWorkspace> updateTagById(@PathVariable Long id, @RequestBody MemberWorkspace memberWorkspace){
        Optional<MemberWorkspace> memberWorkspaceOptional = memberWorkspaceService.findById(id);
        if (!memberWorkspaceOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        memberWorkspace.setId(memberWorkspaceOptional.get().getId());
        memberWorkspaceService.save(memberWorkspace);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<MemberWorkspace> deleteTagById(@PathVariable Long id){
        Optional<MemberWorkspace> workspaceOptional = memberWorkspaceService.findById(id);
        if (!workspaceOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        memberWorkspaceService.deleteById(workspaceOptional.get().getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @Transactional
    @PostMapping("/delete")
    public ResponseEntity<MemberWorkspace> deleteAllById(@RequestBody Iterable<MemberWorkspace> memberWorkspaces){
        for (MemberWorkspace memberWorkspace: memberWorkspaces){
            memberWorkspaceService.deleteById(memberWorkspace.getId());
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("search/{keyword}/{workspaceId}")
    public ResponseEntity<Iterable<MemberWorkspace>> showListMemberWorkspace(@PathVariable String keyword, @PathVariable Long workspaceId) {
        return new ResponseEntity<>(memberWorkspaceService.findByKeyword(keyword, workspaceId), HttpStatus.OK);
    }

    @GetMapping("/{workspaceId}/workspace")
    public ResponseEntity<Map<String,Object>> findWorkspaceMember( @PathVariable Long workspaceId,
                                                                  @RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "2") int size){
        Pageable paging = PageRequest.of(page,size);
        Page<MemberWorkspace> memberWorkspacePage = memberWorkspaceService.findByWorkspace(workspaceId,paging);
        List<MemberWorkspace> memberWorkspaceList = memberWorkspacePage.getContent();
        Map<String, Object> response = new HashMap<>();
        response.put("members",memberWorkspaceList);
        response.put("currentPage",memberWorkspacePage.getNumber());
        response.put("totalItems",memberWorkspacePage.getTotalElements());
        response.put("totalPages",memberWorkspacePage.getTotalPages());
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
