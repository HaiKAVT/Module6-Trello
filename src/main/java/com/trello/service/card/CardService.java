package com.trello.service.card;

import com.trello.model.Board;
import com.trello.model.Card;
import com.trello.service.GeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CardService extends GeneralService<Card> {
    Iterable<Card> saveAll(Iterable<Card> cards);
    Page<Card> findAllAvailableToSearcher(Long searcherId, Pageable pageable);
}
