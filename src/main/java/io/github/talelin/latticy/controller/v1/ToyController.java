package io.github.talelin.latticy.controller.v1;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.core.annotation.GroupRequired;
import io.github.talelin.core.annotation.PermissionMeta;
import io.github.talelin.latticy.common.util.PageUtil;
import io.github.talelin.latticy.dto.toy.CreateOrUpdateToyDTO;
import io.github.talelin.latticy.dto.toy_user.DispatchToyUserDTO;
import io.github.talelin.latticy.model.ToyDO;
import io.github.talelin.latticy.model.ToyUserDO;
import io.github.talelin.latticy.model.UserDO;
import io.github.talelin.latticy.module.wechat.WechatUtil;
import io.github.talelin.latticy.service.ToyService;
import io.github.talelin.latticy.service.ToySortService;
import io.github.talelin.latticy.service.ToyUserService;
import io.github.talelin.latticy.service.UserService;
import io.github.talelin.latticy.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/toy")
@Validated
public class ToyController {

    @Autowired
    private ToyService toyService;

    @Autowired
    private ToySortService toySortService;

    @Autowired
    private ToyUserService toyUserService;

    @Autowired
    private UserService userService;

    /**
     * 查询宝贝（分页 + 关键字）
     * @param page
     * @param count
     * @param keyWord
     * @return
     */
    @GetMapping("/search")
    public PageResponseVO<ToyInfoVO> fetchToys(
            @RequestParam(name = "page", required = false, defaultValue = "0")
            @Min(value = 0, message = "page.number.min") Integer page,
            @RequestParam(name = "count", required = false, defaultValue = "20")
            @Min(value = 1, message = "page.count.min")
            @Max(value = 30, message = "page.count.max") Integer count,
            @RequestParam(name = "keyWord", required = false) String keyWord
    ) {
        IPage<ToyDO> iPage = toyService.selectPageByKeyWord(page, count, keyWord);
        List<ToyInfoVO> toyInfos = iPage.getRecords().stream().map(toy -> {
            // 获取user
            UserDO user = userService.selectUserByToyId(toy.getId());
            if (user == null) {
                throw new NotFoundException("找不到宝贝所属的相关用户信息");
            }
            ToyUserDO toyUser = toyUserService.selectByToyId(toy.getId());
            if (toyUser == null) {
                throw new NotFoundException("找不到宝贝所属的相关用户信息状态");
            }
            return new ToyInfoVO(toy, user, toyUser);
        }).collect(Collectors.toList());
        return PageUtil.build(iPage, toyInfos);
    }

    @GetMapping("/user")
    public PageResponseVO<ToyInfoVO> fetchToysByUser(
            @RequestParam(name = "page", required = false, defaultValue = "0")
            @Min(value = 0, message = "page.number.min") Integer page,
            @RequestParam(name = "count", required = false, defaultValue = "20")
            @Min(value = 1, message = "page.count.min")
            @Max(value = 30, message = "page.count.max") Integer count,
            @RequestParam(name = "userId", required = false) Integer userId,
            @RequestParam(name = "status", required = false) Integer status
    ) {
        IPage<ToyUserDO> iPage = toyUserService.selectPageByUserId(page, count, userId, status);
        List<ToyInfoVO> toys = iPage.getRecords().stream().map(toyUser -> {
            ToyDO toy = toyService.selectById(toyUser.getToyId());
            UserDO user = userService.getById(toyUser.getUserId());
            if (toyUser == null) {
                throw new NotFoundException("找不到宝贝所属的相关用户信息状态");
            }
            return new ToyInfoVO(toy, user, toyUser);
        }).collect(Collectors.toList());
        toys.sort((t1, t2) -> t2.getCreateTime().compareTo(t1.getCreateTime()));
        return PageUtil.build(iPage, toys);
    }

    /**
     * 新增宝贝
     * @param validator
     * @return
     */
    @PostMapping("")
    @GroupRequired
    @PermissionMeta(value = "新增宝贝", module = "商品", mount = true)
    public CreatedVO createToy(@RequestBody @Validated CreateOrUpdateToyDTO validator) {
        toyService.createToy(validator);
        return new CreatedVO("新增宝贝成功");
    }

    @PostMapping("/wx_toy")
    @GroupRequired
    @PermissionMeta(value = "新增宝贝", module = "商品", mount = true)
    public CreatedVO createToyByWx(@RequestBody @Validated CreateOrUpdateToyDTO validator) {
        JSONObject getAccessToken = WechatUtil.getAccessToken();
        String access_token = getAccessToken.getString("access_token");
        String err_msg = getAccessToken.getString("errmsg");
        if (access_token == null) {
            throw new NotFoundException("获取微信凭证接口失败: " + err_msg);
        }
        JSONObject res = WechatUtil.msgSecCheck(access_token, validator.getToyContent());
        String err_code = res.getString("errcode");
        if (Integer.valueOf(err_code) != 0) {
            throw new NotFoundException("文字内容存在潜在风险，请重新编辑文字");
        }
        toyService.createToy(validator);
        return new CreatedVO("新增宝贝成功" + err_code);
    }

    @GetMapping("/{id}")
    public ToyContentVO selectToyById(@PathVariable(value = "id") @Positive(message = "id必须为正整数") Integer id) {
        ToyDO toy = toyService.selectById(id);
        if (toy == null) {
            throw new NotFoundException("查询不到指定的宝贝");
        }
        UserDO user = userService.selectUserByToyId(toy.getId());
        if (user == null) {
            throw new NotFoundException("找不到宝贝所属的相关用户信息");
        }
        return new ToyContentVO(toy, user);
    }

    @PutMapping("/{id}")
    @GroupRequired
    @PermissionMeta(value = "更新宝贝", module = "商品", mount = true)
    public UpdatedVO updateToy(@PathVariable("id") @Positive(message = "{id.positive}") Integer id, @RequestBody @Validated CreateOrUpdateToyDTO validator) {
        ToyDO toy = toyService.selectById(id);
        if (toy == null) {
            throw new NotFoundException("找不到宝贝信息");
        }
        toyService.updateToy(toy, validator);
        return new UpdatedVO("宝贝已成功更新");
    }

    @PutMapping("/status/{id}")
    @GroupRequired
    @PermissionMeta(value = "变更宝贝状态", module = "商品", mount = true)
    public UpdatedVO switchToyStatus(@PathVariable("id") @Positive(message = "{id.positive}") Integer id, @RequestBody @Validated DispatchToyUserDTO validator) {
        ToyDO toy = toyService.selectById(id);
        if (toy == null) {
            throw new NotFoundException("找不到宝贝信息");
        }
        ToyUserDO toy_user = toyUserService.selectByToyId(id);

        if (toy_user.getStatus() == 3) { throw new NotFoundException("已交易的宝贝不能再次编辑，请刷新查看宝贝信息"); }
        if (toy_user.getStatus() == validator.getStatus()) { throw new NotFoundException("执行重复的命令，请刷新宝贝状态"); }

        toyUserService.updateToyUser(toy_user, validator);
        if (validator.getStatus() == 1) { return new UpdatedVO("宝贝已成功上架"); }
        return new UpdatedVO("宝贝已成功下架");
    }

    @DeleteMapping("/{id}")
    @GroupRequired
    @PermissionMeta(value = "删除宝贝", module = "商品", mount = true)
    public DeletedVO deleteToy(@PathVariable("id") @Positive(message = "{id}") Integer id) {
        ToyDO toy = toyService.selectById(id);
        if (toy == null) {
            throw new NotFoundException("找不到宝贝信息");
        }
        toyService.deleteById(id);
        return new DeletedVO("宝贝已成功删除");
    }
}
