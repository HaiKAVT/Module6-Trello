package com.trello.service.column;

import com.trello.model.Card;
import com.trello.model.Column;
import com.trello.service.GeneralService;

public interface ColumnService extends GeneralService<Column> {
    Iterable<Column> saveAll(Iterable<Column> columns);
}
