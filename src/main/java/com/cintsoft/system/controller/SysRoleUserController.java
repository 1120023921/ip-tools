package com.cintsoft.system.controller;


import com.cintsoft.common.enums.ErrorCodeInfo;
import com.cintsoft.common.vo.ResultBean;
import com.cintsoft.system.service.SysRoleUserService;
import com.cintsoft.system.validator.sys.roleuser.SysRoleUserValidator;
import com.cintsoft.system.vo.UserRoleResourceVo;
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
 * 角色-用户关联 前端控制器
 * </p>
 *
 * @author 胡昊
 * @since 2020-07-23
 */
@Api(value = "/sysRoleUser", tags = "角色-用户管理")
@RestController
@RequestMapping("/sysRoleUser")
public class SysRoleUserController {

    @Resource
    private SysRoleUserService sysRoleUserService;

    /**
     * @param userRoleResourceVo 用户角色参数
     * @description 保存用户的角色
     * @author 胡昊
     * @email huhao9277@gmail.com
     * @date 2020/7/26 15:21:03
     */
    @ApiOperation("保存用户角色")
    @PreAuthorize("@cintSecurity.hasPermission('sysRoleUser:saveUserRole')")
    @PostMapping("/saveUserRole")
    public ResultBean<Boolean> saveUserRole(@RequestBody UserRoleResourceVo userRoleResourceVo) {
        //参数校验
        SysRoleUserValidator.saveUserRoleValidate(userRoleResourceVo);
        return ResultBean.restResult(sysRoleUserService.saveUserRoleValidate(userRoleResourceVo), ErrorCodeInfo.OK);
    }
}

