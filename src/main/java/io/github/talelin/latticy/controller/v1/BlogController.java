package io.github.talelin.latticy.controller.v1;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.core.annotation.GroupRequired;
import io.github.talelin.core.annotation.PermissionMeta;
import io.github.talelin.latticy.common.util.PageUtil;
import io.github.talelin.latticy.dto.blog.CreateOrUpdateBlogDTO;
import io.github.talelin.latticy.model.BlogDO;
import io.github.talelin.latticy.model.SortDO;
import io.github.talelin.latticy.service.BlogService;
import io.github.talelin.latticy.service.SortService;
import io.github.talelin.latticy.vo.BlogInfoVO;
import io.github.talelin.latticy.vo.CreatedVO;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/blog")
@Validated
public class BlogController {


    @Autowired
    private BlogService blogService;

    @Autowired
    private SortService sortService;

    @GetMapping("/search")
    public PageResponseVO<BlogInfoVO> fetchBlogs(
            @RequestParam(name = "page", required = false, defaultValue = "0")
            @Min(value = 0, message = "page.number.min") Integer page,
            @RequestParam(name = "count", required = false, defaultValue = "10")
            @Min(value = 1, message = "{page.count.min}")
            @Max(value = 30, message = "page.count.max") Integer count,
            @RequestParam(name = "keyWord", required = false) String keyWord
    ) {
        IPage<BlogDO> iPage = blogService.selectPageByKeyWord(page, count, keyWord);
        List<BlogInfoVO> blogInfos = iPage.getRecords().stream().map(blog -> {
            SortDO sort = sortService.selectSortByBlogId(blog.getId());
            return new BlogInfoVO(blog, sort);
        }).collect(Collectors.toList());
        return PageUtil.build(iPage, blogInfos);
    }

    @GetMapping("/{id}")
    public BlogDO selectBlogById(@PathVariable(value = "id") @Positive(message = "id必须为正整数") Integer id) {
        BlogDO blog = blogService.selectBlogById(id);
        if (blog == null) {
            throw new NotFoundException(10022);
        }
        return blog;
    }

    @PostMapping("")
    @GroupRequired
    @PermissionMeta(value = "新增博客", module = "博客", mount = true)
    public CreatedVO createBlog(@RequestBody @Validated CreateOrUpdateBlogDTO validator) {
        blogService.createBlog(validator);
        return new CreatedVO(12);
    }

    @PutMapping("/{id}")
    @GroupRequired
    @PermissionMeta(value = "更新博客", module = "博客", mount = true)
    public UpdatedVO updateBlog(@PathVariable("id") @Positive(message = "{id.positive}") Integer id, @RequestBody @Validated CreateOrUpdateBlogDTO validator) {
        BlogDO blog = blogService.selectById(id);
        if (blog == null) {
            throw new NotFoundException(10022);
        }
        blogService.updateBlog(blog, validator);
        return new UpdatedVO(13);
    }
}
