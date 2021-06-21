package io.github.talelin.latticy.controller.v1;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.core.annotation.GroupRequired;
import io.github.talelin.core.annotation.Logger;
import io.github.talelin.core.annotation.LoginRequired;
import io.github.talelin.core.annotation.PermissionMeta;
import io.github.talelin.latticy.common.util.PageUtil;
import io.github.talelin.latticy.dto.sort.CreateOrUpdateSortDTO;
import io.github.talelin.latticy.model.SortDO;
import io.github.talelin.latticy.service.SortService;
import io.github.talelin.latticy.vo.CreatedVO;
import io.github.talelin.latticy.vo.DeletedVO;
import io.github.talelin.latticy.vo.PageResponseVO;
import io.github.talelin.latticy.vo.UpdatedVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/sort")
@Validated
public class SortController {
    @Autowired
    private SortService sortService;

    @GetMapping("")
    @LoginRequired // 登录校验
    @PermissionMeta(value = "搜索类别", module = "类别", mount = true) // 权限校验
    public List<SortDO> getSorts() {
        List<SortDO> sorts = sortService.findAll();
        return sorts;
    }

    @GetMapping("/search")
    @LoginRequired
    @PermissionMeta(value = "搜索类别", module = "类别", mount = true)
    public PageResponseVO<SortDO> fetchSorts(
            @RequestParam(name = "page", required = false, defaultValue = "0")
            @Min(value = 0, message = "page.number.min") Integer page,
            @RequestParam(name = "count", required = false, defaultValue = "10")
            @Min(value = 1, message = "{page.count.min}")
            @Max(value = 30, message = "page.count.max") Integer count,
            @RequestParam(name = "keyWord", required = false) String keyWord

    ) {
        IPage<SortDO> iPage = sortService.selectPageByKeyWord(page, count, keyWord);
        return PageUtil.build(iPage);
    }

    @GetMapping("/{id}")
    public SortDO getSortById(@PathVariable(value = "id") @Positive(message = "id必须为正整数") Integer id) {
        SortDO sort = sortService.getSortById(id);
        if (sort == null) {
            throw new NotFoundException(10022);
        }
        return sort;
    }

    @PostMapping("")
    public CreatedVO createSort(@RequestBody @Validated CreateOrUpdateSortDTO validator) {
        sortService.createSort(validator);
        return new CreatedVO(12);
    }

    @PutMapping("/{id}")
    public UpdatedVO updateSort(@PathVariable("id") @Positive(message = "{id.positive}") Integer id, @RequestBody @Validated CreateOrUpdateSortDTO validator) {
        SortDO sort = sortService.getSortById(id);
        if (sort == null) {
            throw new NotFoundException(10022);
        }
        sortService.updateSort(sort, validator);
        return new UpdatedVO(13);
    }

    @DeleteMapping("/{id}")
    @PermissionMeta(value = "删除类别", module = "类别", mount = true)
    @GroupRequired
    public DeletedVO deleteSort(@PathVariable("id") @Positive(message = "{id}") Integer id) {
        SortDO sort = sortService.getSortById(id);
        if (sort == null) {
            throw new NotFoundException(10022);
        }
        sortService.deleteById(sort.getId());
        return new DeletedVO(14);
    }
}
