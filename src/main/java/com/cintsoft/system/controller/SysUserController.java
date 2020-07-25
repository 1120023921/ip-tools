package com.cintsoft.system.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cintsoft.common.enums.ErrorCodeInfo;
import com.cintsoft.common.vo.ResultBean;
import com.cintsoft.system.model.SysUser;
import com.cintsoft.system.service.SysUserService;
import com.cintsoft.system.validator.sys.user.SysUserValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户信息 前端控制器
 * </p>
 *
 * @author 胡昊
 * @since 2020-07-23
 */
@Api(value = "/sysUser", tags = "用户管理")
@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    /**
     * @param sysUser 用户信息
     * @description 新增用户
     * @author 胡昊
     * @email huhao9277@gmail.com
     * @date 2020/7/24 13:28
     */
    @ApiOperation("新增用户")
    @PreAuthorize("@cintSecurity.hasPermission('sysUser:insert')")
    @PostMapping("/insert")
    public ResultBean<Boolean> insert(@RequestBody SysUser sysUser) {
        //参数校验
        SysUserValidator.insertValidate(sysUser);
        return ResultBean.restResult(sysUserService.insert(sysUser), ErrorCodeInfo.CREATED);
    }

    /**
     * @param idList 待删除用户id列表
     * @description 批量删除用户
     * @author 胡昊
     * @email huhao9277@gmail.com
     * @date 2020/7/24 13:51
     */
    @ApiOperation("批量删除用户")
    @PreAuthorize("@cintSecurity.hasPermission('sysUser:deleteBatch')")
    @DeleteMapping("/deleteBatch")
    public ResultBean<Boolean> deleteBatch(@RequestBody List<String> idList) {
        //参数校验
        SysUserValidator.deleteValidate(idList);
        return ResultBean.restResult(sysUserService.deleteBatch(idList), ErrorCodeInfo.NO_CONTENT);
    }

    /**
     * @param sysUser 用户信息
     * @description 更新用户信息
     * @author 胡昊
     * @email huhao9277@gmail.com
     * @date 2020/7/24 15:25
     */
    @ApiOperation("更新用户")
    @PreAuthorize("@cintSecurity.hasPermission('sysUser:update')")
    @PatchMapping("/update")
    public ResultBean<Boolean> update(@RequestBody SysUser sysUser) {
        //参数校验
        SysUserValidator.updateValidate(sysUser);
        return ResultBean.restResult(sysUserService.update(sysUser), ErrorCodeInfo.OK);
    }

    /**
     * @param pageNum  页号
     * @param pageSize 页大小
     * @param sysUser  查询条件
     * @description 分页查询用户
     * @author 胡昊
     * @email huhao9277@gmail.com
     * @date 2020/7/24 15:39
     */
    @ApiOperation("分页查询用户")
    @PreAuthorize("@cintSecurity.hasPermission('sysUser:page')")
    @PostMapping("/page")
    public ResultBean<Page<SysUser>> page(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize, @RequestBody SysUser sysUser) {
        return ResultBean.restResult(sysUserService.page(new Page<>(pageNum, pageSize), Wrappers.lambdaQuery(sysUser).select(i -> !"password".equals(i.getProperty())).orderByDesc(SysUser::getCreateTime)), ErrorCodeInfo.OK);
    }
}

