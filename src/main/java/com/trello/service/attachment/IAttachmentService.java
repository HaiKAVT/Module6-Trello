package com.trello.service.attachment;

import com.trello.model.Attachment;
import com.trello.service.GeneralService;

public interface IAttachmentService extends GeneralService<Attachment> {
    Iterable<Attachment> findAttachmentsByCard_Id(Long cardId);

}
