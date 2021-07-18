package io.github.talelin.latticy.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.talelin.latticy.model.ToyDO;
import io.github.talelin.latticy.model.ToyUserDO;
import io.github.talelin.latticy.model.UserDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ToyInfoVO {

    private Integer id;
    private Integer userId;
    private String nickname;
    private String toyNo;
    private Integer status;
    private String toyCover;
    private String toyContent;
    private Integer toyViews;
    private Integer toyAmount;
    private Integer toyLikeCount;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date updateTime;

    public ToyInfoVO(ToyDO toy, UserDO user, ToyUserDO toyUser) {
        BeanUtils.copyProperties(toy, this);
        this.userId = user.getId();
        this.nickname = user.getNickname();
        this.status = toyUser.getStatus();
    }
}
