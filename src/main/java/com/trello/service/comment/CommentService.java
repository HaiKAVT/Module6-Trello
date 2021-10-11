package com.trello.service.comment;

import com.trello.model.Comment;
import com.trello.repository.CommentRepository;
import com.trello.service.GeneralService;

public interface CommentService extends GeneralService<Comment> {

    Iterable<Comment> findAllCommentByCardId(Long id);

    Iterable<Comment> saveAll(Iterable<Comment> comments);
}
