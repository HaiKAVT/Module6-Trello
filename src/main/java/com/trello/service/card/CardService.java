package com.trello.service.card;

import com.trello.model.Card;
import com.trello.service.GeneralService;

public interface CardService extends GeneralService<Card> {
    Iterable<Card> saveAll(Iterable<Card> cards);
}
