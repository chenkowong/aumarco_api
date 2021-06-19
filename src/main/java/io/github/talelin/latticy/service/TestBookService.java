package io.github.talelin.latticy.service;

import io.github.talelin.latticy.dto.book.CreateOrUpdateTestBookDTO;
import io.github.talelin.latticy.model.TestBookDO;

import java.util.List;

public interface TestBookService {
    TestBookDO getTestBookById(Integer id); // 通过id查询数据

    boolean createTestBook(CreateOrUpdateTestBookDTO validator); // 新增

    boolean deleteById(Integer id); // 删除

    List<TestBookDO> getTestBookByKeyword(String s); // 查询数据列表 + 关键字查询
}
