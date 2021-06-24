package io.github.talelin.latticy.controller.v1;

import io.github.talelin.latticy.dto.visitor.CreateOrUpdateVisitorDTO;
import io.github.talelin.latticy.model.VisitorDO;
import io.github.talelin.latticy.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/v1/visitor")
@Validated
public class VisitorController {

    @Autowired
    private VisitorService visitorService;

    @GetMapping("")
    public VisitorDO selectVisitorByCip(
            @RequestParam(name = "cip") String cip,
            @RequestParam(name = "cid") String cid,
            @RequestParam(name = "cname") String cname
    ) {
        VisitorDO visitor = visitorService.selectVisitorByCip(cip);
        // 如果数据库中没有查到IP，则保存这个IP并新增IP的访问数为1
        if (visitor == null) {
            CreateOrUpdateVisitorDTO new_visitor = new CreateOrUpdateVisitorDTO();
            new_visitor.setCip(cip);
            new_visitor.setCid(cid);
            new_visitor.setCname(cname);
            new_visitor.setCount(1);
            visitorService.createVisitor(new_visitor);
        } else {
            // 如果数据库有保存此IP，则访问数 + 1
            CreateOrUpdateVisitorDTO upd_visitor = new CreateOrUpdateVisitorDTO();
            upd_visitor.setCount(visitor.getCount() + 1);
            visitorService.updateVisitor(visitor, upd_visitor);
        }
        // 博客访问数 + 1
        VisitorDO zion = visitorService.selectById(1);
        CreateOrUpdateVisitorDTO upd_zion = new CreateOrUpdateVisitorDTO();
        upd_zion.setCount(zion.getCount() + 1);
        visitorService.updateVisitor(zion, upd_zion);
        return visitor;
    }

    @GetMapping("/{id}")
    public VisitorDO getVisitorById(@PathVariable(value = "id") @Positive(message = "id必须为正整数") Integer id) {
        VisitorDO visitor = visitorService.selectById(id);
        return visitor;
    }
}
