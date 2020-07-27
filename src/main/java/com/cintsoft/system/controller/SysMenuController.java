package com.cintsoft.system.controller;


import com.cintsoft.common.enums.ErrorCodeInfo;
import com.cintsoft.common.vo.ResultBean;
import com.cintsoft.system.model.SysMenu;
import com.cintsoft.system.service.SysMenuService;
import com.cintsoft.system.validator.sys.menu.SysMenuValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 菜单信息 前端控制器
 * </p>
 *
 * @author 胡昊
 * @since 2020-07-27
 */
@Api(value = "/sysMenu", tags = "菜单管理")
@RestController
@RequestMapping("/sysMenu")
public class SysMenuController {

    @Resource
    private SysMenuService sysMenuService;

    /**
     * @param sysMenu 菜单信息
     * @description 新增菜单
     * @author 胡昊
     * @email huhao9277@gmail.com
     * @date 2020/7/27 17:50
     */
    @ApiOperation("新增菜单")
    @PreAuthorize("@cintSecurity.hasPermission('sysMenu:insert')")
    @PostMapping("/insert")
    public ResultBean<Boolean> insert(@RequestBody SysMenu sysMenu) {
        //参数校验
        SysMenuValidator.insert(sysMenu, sysMenuService);
        return ResultBean.restResult(sysMenuService.save(sysMenu), ErrorCodeInfo.CREATED);
    }
}

