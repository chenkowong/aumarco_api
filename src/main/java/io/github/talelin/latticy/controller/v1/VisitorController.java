package io.github.talelin.latticy.controller.v1;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.latticy.common.util.PageUtil;
import io.github.talelin.latticy.dto.blog.CreateOrUpdateBlogDTO;
import io.github.talelin.latticy.dto.blog_visitor.DispatchBlogVisitorDTO;
import io.github.talelin.latticy.dto.blog_visitor.UpdateBlogVisitorDTO;
import io.github.talelin.latticy.dto.visitor.CreateOrUpdateVisitorDTO;
import io.github.talelin.latticy.model.BlogDO;
import io.github.talelin.latticy.model.BlogVisitorDO;
import io.github.talelin.latticy.model.VisitorDO;
import io.github.talelin.latticy.service.BlogService;
import io.github.talelin.latticy.service.BlogVisitorService;
import io.github.talelin.latticy.service.VisitorService;
import io.github.talelin.latticy.vo.PageResponseVO;
import io.github.talelin.latticy.vo.UpdatedVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/v1/visitor")
@Validated
public class VisitorController {

    @Autowired
    private VisitorService visitorService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogVisitorService blogVisitorService;

    @GetMapping("/search")
    public PageResponseVO<VisitorDO> fetchVisitors(
            @RequestParam(name = "page", required = false, defaultValue = "0")
            @Min(value = 0, message = "page.number.min") Integer page,
            @RequestParam(name = "count", required = false, defaultValue = "10")
            @Min(value = 1, message = "page.count.min")
            @Max(value = 30, message = "page.count.max") Integer count,
            @RequestParam(name = "keyWord", required = false) String keyWord
    ) {
        IPage<VisitorDO> iPage = visitorService.selectPageByKeyWord(page, count, keyWord);
        return PageUtil.build(iPage);
    }

    @GetMapping("")
    public UpdatedVO refreshWebsiteViews(
            @RequestParam(name = "cip") String cip,
            @RequestParam(name = "cid") String cid,
            @RequestParam(name = "cname") String cname
    ) {
        VisitorDO visitor = visitorService.selectVisitorByCip(cip);
        // 如果数据库中没有查到IP，则保存这个IP并新增IP的访问数为1
        if (visitor == null) {
            CreateOrUpdateVisitorDTO new_visitor = new CreateOrUpdateVisitorDTO();
            new_visitor.setCip(cip);
            new_visitor.setCid(cid);
            new_visitor.setCname(cname);
            new_visitor.setCount(1);
            visitorService.createVisitor(new_visitor);
        } else {
            // 如果数据库有保存此IP，则访问数 + 1
            CreateOrUpdateVisitorDTO upd_visitor = new CreateOrUpdateVisitorDTO();
            upd_visitor.setCount(visitor.getCount() + 1);
            visitorService.updateVisitor(visitor, upd_visitor);
        }
        // 博客访问数 + 1
        VisitorDO zion = visitorService.selectById(1);
        CreateOrUpdateVisitorDTO upd_zion = new CreateOrUpdateVisitorDTO();
        upd_zion.setCount(zion.getCount() + 1);
        visitorService.updateVisitor(zion, upd_zion);
        return new UpdatedVO(42);
    }

    @GetMapping("/{id}")
    public VisitorDO getVisitorById(@PathVariable(value = "id") @Positive(message = "id必须为正整数") Integer id) {
        VisitorDO visitor = visitorService.selectById(id);
        return visitor;
    }

    @GetMapping("/view")
    public UpdatedVO refreshBlogViews(
            @RequestParam(name = "blog_id") Integer blog_id,
            @RequestParam(name = "cip") String cip
    ) {
        BlogDO blog = blogService.selectById(blog_id);
        if (blog == null) {
            throw new NotFoundException(10022);
        }
        VisitorDO visitor = visitorService.selectVisitorByCip(cip);
        if (visitor == null) {
            throw new NotFoundException("查找不到访问者");
        }
        DispatchBlogVisitorDTO blogVisitorDTO = new DispatchBlogVisitorDTO();
        blogVisitorDTO.setBlogId(blog.getId());
        blogVisitorDTO.setVisitorId(visitor.getId());
        BlogVisitorDO blogVisitor = blogVisitorService.selectBlogVisitor(blogVisitorDTO);
        if (blogVisitor == null) {
            BlogVisitorDO new_blogVisitor = new BlogVisitorDO();
            new_blogVisitor.setBlogId(blog.getId());
            new_blogVisitor.setVisitorId(visitor.getId());
            new_blogVisitor.setCount(1);
            blogVisitorService.dispatchBlogVisitor(new_blogVisitor);
        } else {
            UpdateBlogVisitorDTO upd_blogVisitor = new UpdateBlogVisitorDTO();
            upd_blogVisitor.setCount(blogVisitor.getCount() + 1);
            blogVisitorService.updateBlogVisitor(blogVisitor, upd_blogVisitor);
        }
        CreateOrUpdateBlogDTO upd_blog = new CreateOrUpdateBlogDTO();
        upd_blog.setBlogViews(blog.getBlogViews() + 1);
        blogService.updateBlogViews(blog, upd_blog);
        return new UpdatedVO(42);
    }
}
