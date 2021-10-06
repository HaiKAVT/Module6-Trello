package com.trello.service.board;

import com.trello.model.Board;
import com.trello.model.SimpleBoard;
import com.trello.model.User;
import com.trello.service.GeneralService;

public interface BoardService extends GeneralService<Board> {
    Board findByIdSort(Long id);
    Iterable<Board> findAllByOwner(User owner);
    Iterable<SimpleBoard> findAllOwnedBoardsByUserId(Long userId);
    Iterable<SimpleBoard> findAllSharedBoardsByUserId(Long userId);
    Iterable<Board> findAllAvailableToSearcher(Long searcherId);
    Iterable<Board> findAllBoardByType(String type);
}
