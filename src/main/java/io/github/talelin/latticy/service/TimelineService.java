package io.github.talelin.latticy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.latticy.dto.timeline.CreateOrUpdateTimelineDTO;
import io.github.talelin.latticy.model.TimelineDO;

import java.util.List;

public interface TimelineService {

    IPage<TimelineDO> selectPageBySort(Integer page, Integer count, String keyWord, Integer parentId);

    IPage<TimelineDO> selectPageWithoutSort(Integer page, Integer count, String keyWord);

    IPage<TimelineDO> selectPageByKeyWord(Integer page, Integer count, String keyWord);

    List<TimelineDO> selectTimelineByParentId(Integer parentId);

    TimelineDO selectById(Integer id);

    boolean createTimeline(CreateOrUpdateTimelineDTO validator);

    boolean updateTimeline(TimelineDO timeline, CreateOrUpdateTimelineDTO validator);

    boolean deleteById(Integer id);
}
