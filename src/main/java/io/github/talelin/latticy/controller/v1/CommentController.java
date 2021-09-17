package io.github.talelin.latticy.controller.v1;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.core.annotation.GroupRequired;
import io.github.talelin.core.annotation.PermissionMeta;
import io.github.talelin.latticy.common.util.PageUtil;
import io.github.talelin.latticy.dto.blog.CreateOrUpdateCommentDTO;
import io.github.talelin.latticy.model.BlogDO;
import io.github.talelin.latticy.model.CommentDO;
import io.github.talelin.latticy.service.BlogService;
import io.github.talelin.latticy.service.CommentService;
import io.github.talelin.latticy.vo.CommentInfoVO;
import io.github.talelin.latticy.vo.CreatedVO;
import io.github.talelin.latticy.vo.DeletedVO;
import io.github.talelin.latticy.vo.PageResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/comment")
@Validated
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    @GetMapping("")
    public PageResponseVO<CommentInfoVO> fetchComments(
            @RequestParam(name = "page", required = false, defaultValue = "0")
            @Min(value = 0, message = "page.number.min") Integer page,
            @RequestParam(name = "count", required = false, defaultValue = "10")
            @Min(value = 1, message = "page.count.min")
            @Max(value = 30, message = "page.count.max") Integer count,
            @RequestParam(name = "blogId", required = false) Integer blogId,
            @RequestParam(name = "parentId", required = false) Integer parentId,
            @RequestParam(name = "start", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date start,
            @RequestParam(name = "end", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date end
    ) {
        IPage<CommentDO> iPage = commentService.selectPageByBlogId(page, count, blogId, parentId, start, end);
        List<CommentInfoVO> comments = iPage.getRecords().stream().map(comment -> {
            BlogDO blog = blogService.selectBlogById(comment.getBlogId());
            List<CommentDO> children = commentService.selectChildrenByParentId(blogId, comment.getId());
            if (children.isEmpty()) {
                List<CommentDO> null_children = new ArrayList<>();
                return new CommentInfoVO(comment, blog, null_children);
            }
            return new CommentInfoVO(comment, blog, children);
        }).collect(Collectors.toList());
        return PageUtil.build(iPage, comments);
    }

    @PostMapping("")
    public CreatedVO createComment(@RequestBody @Validated CreateOrUpdateCommentDTO validator) {
        commentService.createComment(validator);
        return new CreatedVO("发送成功");
    }

    @DeleteMapping("/{id}")
    @GroupRequired
    @PermissionMeta(value = "删除评论", module = "博客", mount = true)
    public DeletedVO deleteComment(@PathVariable("id") @Positive(message = "{id}") Integer id) {
        commentService.deleteById(id);
        return new DeletedVO("成功删除评论");
    }
}
