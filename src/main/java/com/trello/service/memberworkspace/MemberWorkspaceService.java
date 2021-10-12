package com.trello.service.memberworkspace;

import com.trello.model.MemberWorkspace;
import com.trello.service.GeneralService;

public interface MemberWorkspaceService extends GeneralService<MemberWorkspace> {
    Iterable<MemberWorkspace> findByKeyword(String keyword, Long workspaceId);
}
