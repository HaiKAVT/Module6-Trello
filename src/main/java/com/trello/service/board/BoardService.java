package com.trello.service.board;

import com.trello.model.Board;
import com.trello.model.SimpleBoard;
import com.trello.model.User;
import com.trello.service.GeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService extends GeneralService<Board> {
    Board findByIdSort(Long id);
    Iterable<Board> findAllByOwner(User owner);
    Iterable<Board> findByOwner(Long id);
    Iterable<SimpleBoard> findAllOwnedBoardsByUserId(Long userId);
    Iterable<SimpleBoard> findAllSharedBoardsByUserId(Long userId);
    Page<Board> findAllAvailableToSearcher(Long searcherId,String keyword, Pageable pageable);
    Iterable<Board> findAllBoardByType(String type);
    Iterable<Board> findAllPrivateOwned(String type, Long id);
}
