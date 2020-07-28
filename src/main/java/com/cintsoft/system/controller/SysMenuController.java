package com.cintsoft.system.controller;


import com.cintsoft.common.enums.ErrorCodeInfo;
import com.cintsoft.common.vo.ResultBean;
import com.cintsoft.system.model.SysMenu;
import com.cintsoft.system.service.SysMenuService;
import com.cintsoft.system.validator.sys.menu.SysMenuValidator;
import com.cintsoft.system.vo.SysMenuView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    /**
     * @param idList 带删除菜单id列表
     * @description 批量删除菜单
     * @author 胡昊
     * @email huhao9277@gmail.com
     * @date 2020/7/28 9:44
     */
    @ApiOperation("批量删除菜单")
    @PreAuthorize("@cintSecurity.hasPermission('sysMenu:deleteBatch')")
    @DeleteMapping("/deleteBatch")
    public ResultBean<Boolean> deleteBatch(@RequestBody List<String> idList) {
        //参数校验
        SysMenuValidator.deleteValidate(idList);
        return ResultBean.restResult(sysMenuService.deleteBatch(idList), ErrorCodeInfo.NO_CONTENT);
    }

    /**
     * @param sysMenu 菜单信息
     * @description 更新菜单
     * @author 胡昊
     * @email huhao9277@gmail.com
     * @date 2020/7/28 10:33
     */
    @ApiOperation("更新菜单")
    @PreAuthorize("@cintSecurity.hasPermission('sysMenu:update')")
    @PatchMapping("/update")
    public ResultBean<Boolean> update(@RequestBody SysMenu sysMenu) {
        //参数校验
        SysMenuValidator.updateValidate(sysMenu, sysMenuService);
        return ResultBean.restResult(sysMenuService.updateById(sysMenu), ErrorCodeInfo.OK);
    }

    /**
     * @description 查询菜单树形结构
     * @author 胡昊
     * @email huhao9277@gmail.com
     * @date 2020/7/28 10:41
     */
    @ApiOperation("查询菜单树形结构")
    @PreAuthorize("@cintSecurity.hasPermission('sysMenu:treeSysMenu')")
    @GetMapping("/treeSysMenu")
    public ResultBean<List<SysMenuView>> treeSysMenu() {
        return ResultBean.restResult(sysMenuService.treeSysMenu(), ErrorCodeInfo.OK);
    }
}

