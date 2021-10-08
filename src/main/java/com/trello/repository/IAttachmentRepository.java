package com.trello.repository;

import com.trello.model.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAttachmentRepository extends JpaRepository<Attachment, Long> {
    Iterable<Attachment> findAttachmentsByCard_Id(Long cardId);

}
