package io.github.talelin.latticy.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.talelin.latticy.common.mybatis.Page;
import io.github.talelin.latticy.dto.timeline.CreateOrUpdateTimelineDTO;
import io.github.talelin.latticy.mapper.TimelineMapper;
import io.github.talelin.latticy.model.TimelineDO;
import io.github.talelin.latticy.service.TimelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimelineServiceImpl extends ServiceImpl<TimelineMapper, TimelineDO> implements TimelineService {

    @Autowired TimelineMapper timelineMapper;

    @Override
    public IPage<TimelineDO> selectPageBySort(Integer page, Integer count, String keyWord, Integer parentId) {
        Page<TimelineDO> pager = new Page<>(page, count);
        IPage<TimelineDO> iPage = timelineMapper.selectPageBySort(pager, keyWord, parentId);
        return iPage;
    }

    @Override
    public IPage<TimelineDO> selectPageWithoutSort(Integer page, Integer count, String keyWord) {
        Page<TimelineDO> pager = new Page<>(page, count);
        IPage<TimelineDO> iPage = timelineMapper.selectPageWithoutSort(pager, keyWord);
        return iPage;
    }

    @Override
    public IPage<TimelineDO> selectPageByKeyWord(Integer page, Integer count, String keyWord) {
        Page<TimelineDO> pager = new Page<>(page, count);
        IPage<TimelineDO> iPage = timelineMapper.selectPageByKeyWord(pager, keyWord);
        return iPage;
    }

    @Override
    public List<TimelineDO> selectTimelineByParentId(Integer parentId) {
        List<TimelineDO> timeline = timelineMapper.selectTimelineByParentId(parentId);
        return timeline;
    }

    @Override
    public TimelineDO selectById(Integer id) {
        TimelineDO timeline = timelineMapper.selectById(id);
        return timeline;
    }

    @Override
    public boolean createTimeline(CreateOrUpdateTimelineDTO validator) {
        TimelineDO timeline = new TimelineDO();
        timeline.setTitle(validator.getTitle());
        timeline.setDate(validator.getDate());
        timeline.setPath(validator.getPath());
        timeline.setParentId(validator.getParentId());
        timeline.setRadio(0);
        return timelineMapper.insert(timeline) > 0;
    }

    @Override
    public boolean updateTimeline(TimelineDO timeline, CreateOrUpdateTimelineDTO validator) {
        timeline.setTitle(validator.getTitle());
        timeline.setDate(timeline.getDate());
        timeline.setPath(timeline.getPath());
        timeline.setParentId(timeline.getParentId());
        timeline.setRadio(timeline.getRadio());
        return timelineMapper.updateById(timeline) > 0;
    }

    @Override
    public boolean deleteById(Integer id) { return timelineMapper.deleteById(id) > 0; }
}
