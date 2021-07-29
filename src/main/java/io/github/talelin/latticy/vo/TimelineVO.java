package io.github.talelin.latticy.vo;

import io.github.talelin.latticy.model.TimelineDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TimelineVO {

    private Integer id;
    private String title;
    private List<TimelineDO> line;

    public TimelineVO(TimelineDO timeline, List<TimelineDO> line) {
        BeanUtils.copyProperties(timeline, this);
        if (line.isEmpty()) {
            this.line = null;
        } else {
            this.line = line;
        }
    }
}
