package com.trello.service.memberworkspace;

import com.trello.model.MemberWorkspace;
import com.trello.service.GeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberWorkspaceService extends GeneralService<MemberWorkspace> {
    Iterable<MemberWorkspace> findByKeyword(String keyword, Long workspaceId);

    Page<MemberWorkspace> findByWorkspace(Long workspaceId, Pageable pageable);
}
