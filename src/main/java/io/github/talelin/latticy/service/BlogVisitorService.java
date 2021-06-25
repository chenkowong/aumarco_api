package io.github.talelin.latticy.service;

import io.github.talelin.latticy.dto.blog_visitor.DispatchBlogVisitorDTO;
import io.github.talelin.latticy.dto.blog_visitor.UpdateBlogVisitorDTO;
import io.github.talelin.latticy.model.BlogVisitorDO;

public interface BlogVisitorService {

    boolean dispatchBlogVisitor(BlogVisitorDO validator);

    BlogVisitorDO selectBlogVisitor(DispatchBlogVisitorDTO dto);

    boolean updateBlogVisitor(BlogVisitorDO blogVisitor, UpdateBlogVisitorDTO validator);
}
