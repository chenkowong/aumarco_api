package io.github.talelin.latticy.dto.wxuser;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@NoArgsConstructor
public class RegisterWxUserDTO {

    private String openId;

    private String skey;

    private String sessionKey;

    private String city;
    private String province;
    private String country;
    private String avatarUrl;
    private Integer gender;
    private String nickName;
}
