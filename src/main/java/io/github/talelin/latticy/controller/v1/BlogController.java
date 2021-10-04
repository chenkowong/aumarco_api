package io.github.talelin.latticy.controller.v1;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.core.annotation.GroupRequired;
import io.github.talelin.core.annotation.PermissionMeta;
import io.github.talelin.latticy.common.util.PageUtil;
import io.github.talelin.latticy.dto.blog.CreateOrUpdateBlogDTO;
import io.github.talelin.latticy.dto.blog_sort.RemoveBlogSortDTO;
import io.github.talelin.latticy.mapper.BlogSortMapper;
import io.github.talelin.latticy.model.*;
import io.github.talelin.latticy.service.*;
import io.github.talelin.latticy.vo.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.util.Date;
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
    private BlogSortMapper blogSortMapper;

    @Autowired
    private BlogVisitorService blogVisitorService;

    @Autowired
    private VisitorService visitorService;

    /**
     * 查询博客（分页 + 关键字）
     * @param page
     * @param count
     * @param keyWord
     * @return 分页结果
     */
    @GetMapping("/search")
    public PageResponseVO<BlogInfoVO> fetchBlogs(
            @RequestParam(name = "page", required = false, defaultValue = "0")
            @Min(value = 0, message = "page.number.min") Integer page,
            @RequestParam(name = "count", required = false, defaultValue = "10")
            @Min(value = 1, message = "page.count.min")
            @Max(value = 30, message = "page.count.max") Integer count,
            @RequestParam(name = "keyWord", required = false) String keyWord,
            @RequestParam(name = "removeId", required = false) Integer removeId,
            @RequestParam(name = "start", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date start,
            @RequestParam(name = "end", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date end
    ) {
        IPage<BlogDO> iPage = blogService.selectPageByKeyWord(page, count, keyWord, removeId, start, end);
        List<BlogInfoVO> blogInfos = iPage.getRecords().stream().map(blog -> {
            List<BlogSortDO> sort = blogSortMapper.selectBlogSortByBlogId(blog.getId());
//            SortDO sort = sortService.selectSortByBlogId(blog.getId());
            return new BlogInfoVO(blog, sort);
        }).collect(Collectors.toList());
        return PageUtil.build(iPage, blogInfos);
    }

    /**
     * 查询博客（按类别）
     * @param page pageIndex
     * @param count pageCount
     * @param sortId 类别id
     * @return
     */
    @GetMapping("/sort")
    public PageResponseVO<BlogInfoVO> fetchBlogsBySort(
            @RequestParam(name = "page", required = false, defaultValue = "0")
            @Min(value = 0, message = "page.number.min") Integer page,
            @RequestParam(name = "count", required = false, defaultValue = "10")
            @Min(value = 1, message = "page.count.min")
            @Max(value = 30, message = "page.count.max") Integer count,
            @RequestParam(name = "sort_id", required = false) Integer sortId,
            @RequestParam(name = "removeId", required = false) Integer removeId,
            @RequestParam(name = "desc", required = false) Integer desc
    ) {
        IPage<BlogSortDO> iPage = blogSortService.selectPageBySortId(page, count, sortId, removeId, desc);
        List<BlogInfoVO> blogs = iPage.getRecords().stream().map(blogSort -> {
            BlogDO blog = blogService.selectById(blogSort.getBlogId());
            List<BlogSortDO> sort = blogSortMapper.selectBlogSortByBlogId(blog.getId());
            return new BlogInfoVO(blog, sort);
        }).collect(Collectors.toList());
        // 使用List.sort()做时间排序，日期从大到小
        blogs.sort((t1, t2) -> t2.getCreateTime().compareTo(t1.getCreateTime()));
        return PageUtil.build(iPage, blogs);
    }

    /**
     * 查询博客（单一）
     * @param id
     * @return BlogContentVO
     */
    @GetMapping("/{id}")
    public BlogContentVO selectBlogById(@PathVariable(value = "id") @Positive(message = "id必须为正整数") Integer id) {
        BlogDO blog = blogService.selectBlogById(id);
        if (blog == null) {
            throw new NotFoundException(10022);
        }
        List<BlogSortDO> sort = blogSortMapper.selectBlogSortByBlogId(blog.getId());
        BlogDO prevBlog = blogService.selectBlogById(id - 1);
        BlogDO nextBlog = blogService.selectBlogById(id + 1);
        BlogContentVO blogContent = new BlogContentVO(blog, prevBlog, nextBlog, sort);
        return blogContent;
    }

    /**
     * 新增博客
     * @param validator CreateOrUpdateBlogDTO
     * @return
     */
    @PostMapping("")
    @GroupRequired
    @PermissionMeta(value = "新增博客", module = "博客", mount = true)
    public CreatedVO createBlog(@RequestBody @Validated CreateOrUpdateBlogDTO validator) {
        blogService.createBlog(validator);
        return new CreatedVO(31);
    }

    /**
     * 更新博客
     * @param id
     * @param validator CreateOrUpdateBlogDTO
     * @return
     */
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

    /**
     * 删除博客
     * @param id 博客id
     * @return
     */
    @DeleteMapping("/{id}")
    @GroupRequired
    @PermissionMeta(value = "删除博客", module = "博客", mount = true)
    public DeletedVO deleteBlog(@PathVariable("id") @Positive(message = "{id}") Integer id) {
        blogService.deleteById(id);
        return new DeletedVO(33);
    }

    /**
     * 查询博客访客（分页）
     * @param page
     * @param count
     * @param blogId
     * @return
     */
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
