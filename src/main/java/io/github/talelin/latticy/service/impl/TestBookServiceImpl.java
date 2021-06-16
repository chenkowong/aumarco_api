package io.github.talelin.latticy.service.impl;

import io.github.talelin.latticy.dto.book.CreateOrUpdateTestBookDTO;
import io.github.talelin.latticy.mapper.TestBookMapper;
import io.github.talelin.latticy.model.TestBookDO;
import io.github.talelin.latticy.service.TestBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestBookServiceImpl implements TestBookService {

    @Autowired
    private TestBookMapper testBookMapper;

    @Override
    public TestBookDO getTestBookById(Integer id) {
        TestBookDO testbook = testBookMapper.selectById(id);
        return  testbook;
    }

    @Override
    public boolean createTestBook(CreateOrUpdateTestBookDTO validator) {
        TestBookDO testbook = new TestBookDO();
        testbook.setAuthor(validator.getAuthor());
        testbook.setTitle(validator.getTitle());
        testbook.setImage(validator.getImage());
        testbook.setSummary(validator.getSummary());
        return testBookMapper.insert(testbook) > 0;
    }
}
