package io.github.talelin.latticy.service;

import io.github.talelin.latticy.dto.book.CreateOrUpdateTestBookDTO;
import io.github.talelin.latticy.model.TestBookDO;
import io.github.talelin.latticy.dto.book.CreateOrUpdateTestBookDTO;

public interface TestBookService {
    TestBookDO getTestBookById(Integer id);

    boolean createTestBook(CreateOrUpdateTestBookDTO validator);
}
