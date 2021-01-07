package com.cintsoft.ace.business.provider.system.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cintsoft.ace.business.provider.common.enums.ErrorCodeInfo;
import com.cintsoft.ace.business.provider.common.vo.ResultBean;
import com.cintsoft.ace.business.provider.system.model.SysRole;
import com.cintsoft.ace.business.provider.system.service.SysRoleService;
import com.cintsoft.ace.business.provider.system.validator.sys.role.SysRoleValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 表基础信息 前端控制器
 * </p>
 *
 * @author 胡昊
 * @since 2020-07-23
 */
@Api(value = "/sysRole", tags = "角色管理")
@RestController
@RequestMapping("/sysRole")
public class SysRoleController {

    @Resource
    private SysRoleService sysRoleService;

    /**
     * @param sysRole 角色信息
     * @description 新增角色
     * @author 胡昊
     * @email huhao9277@gmail.com
     * @date 2020/7/25 16:09:48
     */
    @ApiOperation("新增角色")
    @PreAuthorize("@cintSecurity.hasPermission('sysRole:insert')")
    @PostMapping("/insert")
    public ResultBean<Boolean> insert(@RequestBody SysRole sysRole) {
        //参数校验
        SysRoleValidator.insertValidate(sysRole);
        return ResultBean.restResult(sysRoleService.save(sysRole), ErrorCodeInfo.CREATED);
    }

    /**
     * @param idList 待删除角色id列表
     * @description 批量删除角色
     * @author 胡昊
     * @email huhao9277@gmail.com
     * @date 2020/7/25 16:15:39
     */
    @ApiOperation("批量删除角色")
    @PreAuthorize("@cintSecurity.hasPermission('sysRole:deleteBatch')")
    @DeleteMapping("/deleteBatch")
    public ResultBean<Boolean> deleteBatch(@RequestBody List<String> idList) {
        //参数校验
        SysRoleValidator.deleteValidate(idList);
        return ResultBean.restResult(sysRoleService.deleteBatch(idList), ErrorCodeInfo.NO_CONTENT);
    }

    /**
     * @param sysRole 角色信息
     * @description 更新角色
     * @author 胡昊
     * @email huhao9277@gmail.com
     * @date 2020/7/25 16:23:22
     */
    @ApiOperation("更新角色")
    @PreAuthorize("@cintSecurity.hasPermission('sysRole:update')")
    @PatchMapping("/update")
    public ResultBean<Boolean> update(@RequestBody SysRole sysRole) {
        //参数校验
        SysRoleValidator.updateValidate(sysRole);
        return ResultBean.restResult(sysRoleService.updateById(sysRole), ErrorCodeInfo.OK);
    }

    /**
     * @param pageNum  页号
     * @param pageSize 页大小
     * @param sysRole  查询条件
     * @description 分页查询角色
     * @author 胡昊
     * @email huhao9277@gmail.com
     * @date 2020/7/25 16:28:38
     */
    @ApiOperation("分页查询角色")
    @PreAuthorize("@cintSecurity.hasPermission('sysRole:page')")
    @PostMapping("/page")
    public ResultBean<Page<SysRole>> page(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize, @RequestBody SysRole sysRole) {
        return ResultBean.restResult(sysRoleService.page(new Page<>(pageNum, pageSize), Wrappers.lambdaQuery(sysRole).orderByDesc(SysRole::getCreateTime)), ErrorCodeInfo.OK);
    }
}

