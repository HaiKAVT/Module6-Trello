package com.trello.service.member;

import com.trello.model.DetailedMember;
import com.trello.model.Member;
import com.trello.service.GeneralService;

public interface IMemberService extends GeneralService<Member> {
    Iterable<DetailedMember> getMembersByBoardId(Long boardId);
    Iterable<Member> saveAll(Iterable<Member> members);
    void deleteByBoardIdAndUserId(Long boardId, Long userId);
}
