package com.trello.repository;

import com.trello.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    @Query(value = "select * from card where column_id in(select column_id from columns where board_id = ?1)",
            nativeQuery = true)
    Iterable<Card> findCardsByBoardId(Long boardId);

}
