package io.github.talelin.latticy.controller.v1;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.core.annotation.GroupRequired;
import io.github.talelin.core.annotation.PermissionMeta;
import io.github.talelin.latticy.common.util.PageUtil;
import io.github.talelin.latticy.dto.timeline.CreateOrUpdateTimelineDTO;
import io.github.talelin.latticy.model.TimelineDO;
import io.github.talelin.latticy.service.TimelineService;
import io.github.talelin.latticy.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/timeline")
@Validated
public class TimelineController {

    @Autowired
    private TimelineService timelineService;

    @GetMapping("/sort")
    public PageResponseVO<TimelineVO> fetchTimeline(
            @RequestParam(name = "page", required = false, defaultValue = "0")
            @Min(value = 0, message = "page.number.min") Integer page,
            @RequestParam(name = "count", required = false, defaultValue = "10")
            @Min(value = 1, message = "page.count.min")
            @Max(value = 20, message = "page.count.max") Integer count,
            @RequestParam(name = "keyWord", required = false) String keyWord,
            @RequestParam(name = "parentId", required = true) Integer parentId
    ) {
        IPage<TimelineDO> iPage = timelineService.selectPageBySort(page, count, keyWord, parentId);
        List<TimelineVO> lines = iPage.getRecords().stream().map(timeline -> {
            List<TimelineDO> children = timelineService.selectTimelineByParentId(timeline.getId());
            if (children.isEmpty()) {
                List<TimelineDO> null_children = new ArrayList<>();
                return new TimelineVO(timeline, null_children);
            }
            return new TimelineVO(timeline, children);
        }).collect(Collectors.toList());
        return PageUtil.build(iPage, lines);
    }

    @GetMapping("")
    public PageResponseVO<TimelineDO> fetchTimelineByTime(
            @RequestParam(name = "page", required = false, defaultValue = "0")
            @Min(value = 0, message = "page.number.min") Integer page,
            @RequestParam(name = "count", required = false, defaultValue = "10")
            @Min(value = 1, message = "page.count.min")
            @Max(value = 20, message = "page.count.max") Integer count,
            @RequestParam(name = "keyWord", required = false) String keyWord
    ) {
        IPage<TimelineDO> iPage = timelineService.selectPageWithoutSort(page, count, keyWord);
        return PageUtil.build(iPage);
    }

    @GetMapping("/search")
    public PageResponseVO<TimelineDO> fetchTimeline(
            @RequestParam(name = "page", required = false, defaultValue = "0")
            @Min(value = 0, message = "page.number.min") Integer page,
            @RequestParam(name = "count", required = false, defaultValue = "10")
            @Min(value = 1, message = "page.count.min")
            @Max(value = 20, message = "page.count.max") Integer count,
            @RequestParam(name = "keyWord", required = false) String keyWord
    ) {
        IPage<TimelineDO> iPage = timelineService.selectPageByKeyWord(page, count, keyWord);
        return PageUtil.build(iPage);
    }

    @GetMapping("/{id}")
    public TimelineDO selectTimelineById(@PathVariable(value = "id") @Positive(message = "id必须为正整数") Integer id) {
        TimelineDO timeline = timelineService.selectById(id);
        if (timeline == null) {
            throw new NotFoundException(10022);
        }
        return timeline;
    }

    @PostMapping("")
    @GroupRequired
    @PermissionMeta(value = "新增时间轴", module = "时间轴", mount = true)
    public CreatedVO createTimeline(@RequestBody @Validated CreateOrUpdateTimelineDTO validator) {
        timelineService.createTimeline(validator);
        return new CreatedVO(31);
    }

    @PutMapping("/{id}")
    @GroupRequired
    @PermissionMeta(value = "更新时间轴", module = "时间轴", mount = true)
    public UpdatedVO updateTimeline(@PathVariable("id") @Positive(message = "{id.positive}") Integer id, @RequestBody @Validated CreateOrUpdateTimelineDTO validator) {
        TimelineDO timeline = timelineService.selectById(id);
        if (timeline == null) {
            throw new NotFoundException(10022);
        }
        timelineService.updateTimeline(timeline, validator);
        return new UpdatedVO(32);
    }

    @DeleteMapping("/{id}")
    @GroupRequired
    @PermissionMeta(value = "删除时间轴", module = "时间轴", mount = true)
    public DeletedVO deleteTimeline(@PathVariable("id") @Positive(message = "{id}") Integer id) {
        TimelineDO timeline = timelineService.selectById(id);
        if (timeline == null) {
            throw new NotFoundException(10022);
        }
        timelineService.deleteById(id);
        return new DeletedVO(33);
    }
}
