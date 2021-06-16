package io.github.talelin.latticy.controller.v1;

import io.github.talelin.latticy.model.TestBookDO;
import io.github.talelin.latticy.service.TestBookService;
import io.github.talelin.autoconfigure.exception.NotFoundException; // 异常提示
import io.github.talelin.latticy.dto.book.CreateOrUpdateTestBookDTO; // 请求体检验

import io.github.talelin.latticy.vo.CreatedVO; // 新增
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/v1/testbook")
@Validated
public class TestBookController {

    @Autowired
    private TestBookService testBookService;

    @GetMapping("/{id}")
    public TestBookDO getTestBook(@PathVariable(value = "id") @Positive(message = "id必须为正整数") Integer id) {
        TestBookDO testbook = testBookService.getTestBookById(id);
        if (testbook == null) {
//          NotFoundException(String message, Integer code)已经弃用，直接输入code就可以引用code-message否则直接使用message
            throw new NotFoundException(10022);
        }
        return testbook;
    }

    @PostMapping("")
    public CreatedVO createTestBook(@RequestBody @Validated CreateOrUpdateTestBookDTO validator) {
        testBookService.createTestBook(validator);
        return new CreatedVO("创建图书成功");
    }
}
