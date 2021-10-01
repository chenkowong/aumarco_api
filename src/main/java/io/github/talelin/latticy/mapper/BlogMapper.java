package io.github.talelin.latticy.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.latticy.common.mybatis.Page;
import io.github.talelin.latticy.model.BlogDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface BlogMapper extends BaseMapper<BlogDO> {
    IPage<BlogDO> selectPageByKeyWord(Page<BlogDO> paper, String keyWord, Integer removeId, Date start, Date end);

    BlogDO selectBlogById(Integer id);
}
