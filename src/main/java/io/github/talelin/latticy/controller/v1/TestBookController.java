package io.github.talelin.latticy.controller.v1;

import io.github.talelin.core.annotation.Logger;
import io.github.talelin.core.annotation.LoginRequired;
import io.github.talelin.latticy.model.TestBookDO;
import io.github.talelin.latticy.service.TestBookService;
import io.github.talelin.autoconfigure.exception.NotFoundException; // 异常提示
import io.github.talelin.latticy.dto.book.CreateOrUpdateTestBookDTO; // 请求体检验

import io.github.talelin.core.annotation.GroupRequired; // 校验拥有权限的分组
import io.github.talelin.core.annotation.PermissionMeta; // 校验请求权限

import io.github.talelin.latticy.vo.CreatedVO; // 新增
import io.github.talelin.latticy.vo.DeletedVO;
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

    @GetMapping("/search")
    @LoginRequired
    @PermissionMeta(value = "搜索图书", module = "图书", mount = true)
    @Logger(template = "{user.nickname}搜索的一本书")
    public List<TestBookDO> searchTestBook(@RequestParam(value = "q", required = false) String q) {
        List<TestBookDO> testbooks = testBookService.getTestBookByKeyword("%" + q + "%");
        return testbooks;
    }

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
        return new CreatedVO(12);
    }

    @DeleteMapping("/{id}")
    @PermissionMeta(value = "删除图书", module = "图书", mount = true)
    @GroupRequired
    public DeletedVO deleteTestBook(@PathVariable("id") @Positive(message = "{id}") Integer id) {
        TestBookDO testbook = testBookService.getTestBookById(id);
        if (testbook == null) {
            throw new NotFoundException(10022);
        }
        testBookService.deleteById(testbook.getId());
        return new DeletedVO(14);
    }
}
