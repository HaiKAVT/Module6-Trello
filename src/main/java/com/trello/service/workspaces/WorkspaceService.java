package com.trello.service.workspaces;

import com.trello.model.Workspace;
import com.trello.service.GeneralService;

public interface WorkspaceService extends GeneralService<Workspace> {
    Iterable<Workspace>  findAllByOwnerId (Long id);
    Boolean isBoardInWorkspace(Long boardId);
}
