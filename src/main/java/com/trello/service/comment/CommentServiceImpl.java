package com.trello.service.comment;

import com.trello.model.Comment;
import com.trello.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
   private CommentRepository commentRepository;

    @Override
    public Iterable findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Optional findById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }


    @Override
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public Iterable<Comment> findAllCommentByCardId(Long id) {
        return commentRepository.findAllByCardId(id);
    }

    @Override
    public Iterable<Comment> saveAll(Iterable<Comment> comments) {
        return commentRepository.saveAll(comments);
    }
}
