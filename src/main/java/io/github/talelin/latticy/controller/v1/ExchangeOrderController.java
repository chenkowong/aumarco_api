package io.github.talelin.latticy.controller.v1;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.core.annotation.GroupRequired;
import io.github.talelin.core.annotation.PermissionMeta;
import io.github.talelin.latticy.common.util.PageUtil;
import io.github.talelin.latticy.dto.order.CreateOrUpdateExchangeOrder;
import io.github.talelin.latticy.dto.toy_user.DispatchToyUserDTO;
import io.github.talelin.latticy.model.ExchangeOrderDO;
import io.github.talelin.latticy.model.ToyDO;
import io.github.talelin.latticy.model.ToyUserDO;
import io.github.talelin.latticy.model.UserDO;
import io.github.talelin.latticy.service.ExchangeOrderService;
import io.github.talelin.latticy.service.ToyService;
import io.github.talelin.latticy.service.ToyUserService;
import io.github.talelin.latticy.service.UserService;
import io.github.talelin.latticy.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/exchange_order")
@Validated
public class ExchangeOrderController {

    @Autowired
    private ExchangeOrderService exchangeOrderService;

    @Autowired
    private UserService userService;

    @Autowired
    private ToyService toyService;

    @Autowired
    private ToyUserService toyUserService;

    /**
     * 查询交换订单（分页 + 状态 + 用户id）
     * @param page
     * @param count
     * @param status
     * @param requestUserId
     * @param exchangeUserId
     * @return
     */
    @GetMapping("/search")
    @GroupRequired
    @PermissionMeta(value = "查询交换订单", module = "订单", mount = true)
    public PageResponseVO<ExchangeOrderVO> fetchExchangeOrders(
            @RequestParam(name = "page", required = false, defaultValue = "0")
            @Min(value = 0, message = "page.number.min") Integer page,
            @RequestParam(name = "count", required = false, defaultValue = "20")
            @Min(value = 1, message = "page.count.min")
            @Max(value = 30, message = "page.count.max") Integer count,
            @RequestParam(name = "status", required = false, defaultValue = "0") Integer status,
            @RequestParam(name = "requestUserId", required = false) Integer requestUserId,
            @RequestParam(name = "exchangeUserId", required = false) Integer exchangeUserId
    ) {
        IPage<ExchangeOrderDO> iPage = exchangeOrderService.selectPageByKeyWord(page, count, status, requestUserId, exchangeUserId);
        List<ExchangeOrderVO> orders = iPage.getRecords().stream().map(order -> {
            UserDO requestUser = userService.getById(order.getRequestUserId());
            if (requestUser == null) { throw new NotFoundException("没有找到订单相关申请用户"); }

            UserDO exchangeUser = userService.getById(order.getExchangeUserId());
            if (exchangeUser == null) { throw new NotFoundException("没有找到订单相关交换用户"); }

            ToyDO requestToy = toyService.selectById(order.getRequestToyId());
            if (requestToy == null) { throw new NotFoundException("没有找到订单相关申请宝贝"); }

            ToyUserDO requestToyUser = toyUserService.selectByToyId(order.getRequestToyId());
            if (requestToyUser == null) {
                throw new NotFoundException("没有找到订单相关申请宝贝的状态");
            }
            ToyInfoVO requestToyInfo = new ToyInfoVO(requestToy, requestUser, requestToyUser);

            ToyDO exchangeToy = toyService.selectById(order.getExchangeToyId());
            if (exchangeToy == null) {
                ToyInfoVO exchangeToyInfo = new ToyInfoVO();
                return new ExchangeOrderVO(order, requestUser, requestToyInfo, exchangeUser, exchangeToyInfo);
            } else {
                ToyUserDO exchangeToyUser = toyUserService.selectByToyId(exchangeToy.getId());
                if (exchangeToyUser == null) {
                    throw new NotFoundException("没有找到订单相关申请宝贝的状态");
                }
                ToyInfoVO exchangeToyInfo = new ToyInfoVO(exchangeToy, exchangeUser, exchangeToyUser);
                return new ExchangeOrderVO(order, requestUser, requestToyInfo, exchangeUser, exchangeToyInfo);
            }
        }).collect(Collectors.toList());
        return PageUtil.build(iPage, orders);
    }

    @PostMapping("")
    @GroupRequired
    @PermissionMeta(value = "新增交换订单", module = "订单", mount = true)
    public CreatedVO createExchangeOrder(@RequestBody @Validated CreateOrUpdateExchangeOrder validator) {
        exchangeOrderService.createExchangeOrder(validator);
        return new CreatedVO("新增交换请求成功");
    }

    @PutMapping("/{id}")
    @GroupRequired
    @PermissionMeta(value = "更新交换订单", module = "订单", mount = true)
    public UpdatedVO updateExchangeOrder(
            @PathVariable("id") @Positive(message = "{id.positive}") Integer id,
            @RequestBody @Validated CreateOrUpdateExchangeOrder validator
    ) {
        ExchangeOrderDO order = exchangeOrderService.selectById(id);
        if (order == null) { throw new NotFoundException("交换订单不存在"); }
        exchangeOrderService.updateExchangeOrder(order, validator);
        return new UpdatedVO("更新交换请求成功");
    }

    @PutMapping("/status/{id}")
    @GroupRequired
    @PermissionMeta(value = "更新交换订单", module = "订单", mount = true)
    public UpdatedVO switchExchangeOrderStatus(
            @PathVariable("id") @Positive(message = "{id.positive}") Integer id,
            @RequestBody @Validated CreateOrUpdateExchangeOrder validator
    ) {
        ExchangeOrderDO order = exchangeOrderService.selectById(id);
        if (order == null) { throw new NotFoundException("交换订单不存在"); }
        // 如果订单已经生成，就不能再次取消
        if (order.getStatus() == 3) { throw new NotFoundException("Oops! 下手太慢了，订单已经生成啦"); }
        if (order.getStatus() == 998 || order.getStatus() == 999) { throw new NotFoundException("Oops! 订单已经被别人取消啦"); }

        if (validator.getStatus() == 3) {
            ToyDO request_toy = toyService.selectById(order.getRequestToyId());
            ToyUserDO request_toy_user = toyUserService.selectByToyId(request_toy.getId());
            if (request_toy_user.getStatus() == 2) { throw new NotFoundException("Oops! 下单失败，订单中的宝贝已经下架或失效了"); }
            DispatchToyUserDTO request_dto = new DispatchToyUserDTO();
            request_dto.setStatus(2);
            request_dto.setRemark(validator.getOrderNo());

            ToyDO exchange_toy = toyService.selectById(order.getExchangeToyId());
            ToyUserDO exchange_toy_user = toyUserService.selectByToyId(exchange_toy.getId());
            if (exchange_toy_user.getStatus() == 2) { throw new NotFoundException("Oops! 下单失败，订单中的宝贝已经下架或失效了"); }
            DispatchToyUserDTO exchange_dto = new DispatchToyUserDTO();
            exchange_dto.setStatus(2);
            exchange_dto.setRemark(validator.getOrderNo());

            toyUserService.updateToyUser(request_toy_user, request_dto);
            toyUserService.updateToyUser(exchange_toy_user, exchange_dto);

        }
        exchangeOrderService.switchExchangeOrderStatus(order, validator);
        return new UpdatedVO("更新交换请求成功");
    }
}
