package io.github.talelin.latticy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.latticy.common.mybatis.Page;
import io.github.talelin.latticy.model.TimelineDO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimelineMapper extends BaseMapper<TimelineDO> {

    IPage<TimelineDO> selectPageBySort(Page<TimelineDO> paper, String keyWord, Integer parentId);

    IPage<TimelineDO> selectPageByKeyWord(Page<TimelineDO> paper, String keyWord);

    List<TimelineDO> selectTimelineByParentId(Integer parentId);
}
