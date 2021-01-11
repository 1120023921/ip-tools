package com.cintsoft.ace.business.provider.business.controller;


import com.cintsoft.ace.business.provider.business.model.VisitSysMenu;
import com.cintsoft.ace.business.provider.business.service.VisitSysMenuService;
import com.cintsoft.spring.security.annotation.Inner;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 菜单信息 前端控制器
 * </p>
 *
 * @author 胡昊
 * @since 2021-01-08
 */
@RefreshScope
@RestController
@RequestMapping("/visitSysMenu")
public class VisitSysMenuController {

    @Resource
    private VisitSysMenuService visitSysMenuService;

    @PreAuthorize("@cintSecurity.hasPermission('sss')")
    @GetMapping("/test")
    public Object test() {
        List<VisitSysMenu> list = visitSysMenuService.list();
        return list;
    }

    @Inner
    @PostMapping("/test2")
    public Object test2(@RequestBody VisitSysMenu visitSysMenu) {
        return visitSysMenuService.updateById(visitSysMenu);
    }
}

