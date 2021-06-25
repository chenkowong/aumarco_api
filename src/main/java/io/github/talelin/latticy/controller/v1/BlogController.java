package io.github.talelin.latticy.controller.v1;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.core.annotation.GroupRequired;
import io.github.talelin.core.annotation.PermissionMeta;
import io.github.talelin.latticy.common.util.PageUtil;
import io.github.talelin.latticy.dto.blog.CreateOrUpdateBlogDTO;
import io.github.talelin.latticy.dto.blog_sort.RemoveBlogSortDTO;
import io.github.talelin.latticy.model.*;
import io.github.talelin.latticy.service.*;
import io.github.talelin.latticy.vo.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/blog")
@Validated
public class BlogController {


    @Autowired
    private BlogService blogService;

    @Autowired
    private SortService sortService;

    @Autowired
    private BlogSortService blogSortService;

    @Autowired
    private BlogVisitorService blogVisitorService;

    @Autowired
    private VisitorService visitorService;

    @GetMapping("/search")
    public PageResponseVO<BlogInfoVO> fetchBlogs(
            @RequestParam(name = "page", required = false, defaultValue = "0")
            @Min(value = 0, message = "page.number.min") Integer page,
            @RequestParam(name = "count", required = false, defaultValue = "10")
            @Min(value = 1, message = "page.count.min")
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
    public BlogContentVO selectBlogById(@PathVariable(value = "id") @Positive(message = "id必须为正整数") Integer id) {
        BlogDO blog = blogService.selectBlogById(id);
        if (blog == null) {
            throw new NotFoundException(10022);
        }
        SortDO sort = sortService.selectSortByBlogId(blog.getId());
        BlogDO prevBlog = blogService.selectBlogById(id - 1);
        BlogDO nextBlog = blogService.selectBlogById(id + 1);
        BlogContentVO blogContent = new BlogContentVO(blog, prevBlog, nextBlog, sort);
        return blogContent;
    }

    @PostMapping("")
    @GroupRequired
    @PermissionMeta(value = "新增博客", module = "博客", mount = true)
    public CreatedVO createBlog(@RequestBody @Validated CreateOrUpdateBlogDTO validator) {
        blogService.createBlog(validator);
        return new CreatedVO(31);
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
        return new UpdatedVO(32);
    }

    @DeleteMapping("/{id}")
    @GroupRequired
    @PermissionMeta(value = "删除博客", module = "博客", mount = true)
    public DeletedVO deleteBlog(@PathVariable("id") @Positive(message = "{id}") Integer id, @Param("sortId") @Positive(message = "{sortId}") Integer sort_id) {
        if (sort_id == null) {
            throw new NotFoundException(10023);
        }
        BlogDO blog = blogService.selectById(id);
        if (blog == null) {
            throw new NotFoundException(10022);
        }
        blogService.deleteById(blog.getId());
        RemoveBlogSortDTO blogSort = new RemoveBlogSortDTO();
        blogSort.setBlogId(blog.getId());
        blogSort.setSortId(sort_id);
        blogSortService.removeBlogSort(blogSort);
        return new DeletedVO(33);
    }

    @GetMapping("/visitor")
    @GroupRequired
    @PermissionMeta(value = "查询博客访客", module = "博客", mount = true)
    public PageResponseVO<BlogVisitorInfoVO> fetchBlogVisitors(
            @RequestParam(name = "page", required = false, defaultValue = "0")
            @Min(value = 0, message = "page.number.min") Integer page,
            @RequestParam(name = "count", required = false, defaultValue = "10")
            @Min(value = 1, message = "page.count.min")
            @Max(value = 30, message = "page.count.max") Integer count,
            @RequestParam(name = "blog_id", required = false) Integer blogId
    ) {
        IPage<BlogVisitorDO> iPage = blogVisitorService.selectPageByBlogId(page, count, blogId);
        List<BlogVisitorInfoVO> visitors = iPage.getRecords().stream().map(blogVisitor -> {
            VisitorDO visitor = visitorService.selectById(blogVisitor.getVisitorId());
            return new BlogVisitorInfoVO(visitor, blogVisitor);
        }).collect(Collectors.toList());
        return PageUtil.build(iPage, visitors);
    }
}
