package com.cintsoft.ace.business.provider.business.controller;


import com.cintsoft.ace.business.provider.business.model.VisitSysMenu;
import com.cintsoft.ace.business.provider.business.service.VisitSysMenuService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/test")
    public Object test(){
        List<VisitSysMenu> list = visitSysMenuService.list();
        return list;
    }
}

