package io.github.talelin.latticy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.latticy.common.mybatis.Page;
import io.github.talelin.latticy.model.BookDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.List;

/**
 * @author pedro@TaleLin
 */
@Repository
public interface BookMapper extends BaseMapper<BookDO> {

    IPage<BookDO> selectByTitleLikeKeyword(Page<BookDO> paper, String keyWord);

    List<BookDO> selectByTitleLikeKeyword(@Param("keyWord") String keyWord);

    List<BookDO> selectByTitle(@Param("title") String title);
}
