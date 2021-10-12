package com.trello.service.activity;

import com.trello.model.ActivityLog;
import com.trello.service.GeneralService;

public interface ActivityLogService extends GeneralService<ActivityLog> {
    Iterable<ActivityLog> findByBoardId (Long boardId);
}
