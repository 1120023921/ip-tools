package com.cintsoft.ace.business.provider.system.controller;


import com.cintsoft.ace.business.provider.common.enums.ErrorCodeInfo;
import com.cintsoft.ace.business.provider.common.exception.BusinessCode;
import com.cintsoft.ace.business.provider.common.exception.ParameterValidateException;
import com.cintsoft.ace.business.provider.common.vo.ResultBean;
import com.cintsoft.ace.business.provider.system.model.SysRole;
import com.cintsoft.ace.business.provider.system.model.SysUser;
import com.cintsoft.ace.business.provider.system.service.SysRoleUserService;
import com.cintsoft.ace.business.provider.system.validator.sys.roleuser.SysRoleUserValidator;
import com.cintsoft.ace.business.provider.system.vo.UserRoleResourceVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

    /**
     * @param userId 用户id
     * @description 获取用户下角色
     * @author 胡昊
     * @email huhao9277@gmail.com
     * @date 2020/7/27 9:56
     */
    @ApiOperation("获取用户下角色")
    @PreAuthorize("@cintSecurity.hasPermission('sysRoleUser:listUserRole')")
    @GetMapping("/listUserRole")
    public ResultBean<List<SysRole>> listUserRole(String userId) {
        if (!StringUtils.hasText(userId)) {
            throw new ParameterValidateException(BusinessCode.USER_ID_EMPTY_ERROR);
        }
        return ResultBean.restResult(sysRoleUserService.listUserRole(userId), ErrorCodeInfo.OK);
    }

    /**
     * @param userRoleResourceVo 角色用户参数
     * @description 保存角色用户
     * @author 胡昊
     * @email huhao9277@gmail.com
     * @date 2020/8/7 16:43:05
     */
    @ApiOperation("保存角色用户")
    @PreAuthorize("@cintSecurity.hasPermission('sysRoleUser:saveRoleUser')")
    @GetMapping("/saveRoleUser")
    public ResultBean<Boolean> saveRoleUser(@RequestBody UserRoleResourceVo userRoleResourceVo) {
        //参数校验
        SysRoleUserValidator.saveRoleUserValidate(userRoleResourceVo);
        return ResultBean.restResult(sysRoleUserService.saveRoleUser(userRoleResourceVo), ErrorCodeInfo.OK);
    }

    /**
     * @param roleId 角色id
     * @description 获取角色下用户
     * @author 胡昊
     * @email huhao9277@gmail.com
     * @date 2020/8/7 16:32:47
     */
    @ApiOperation("获取角色下用户")
    @PreAuthorize("@cintSecurity.hasPermission('sysRoleUser:listRoleUser')")
    @GetMapping("/listRoleUser")
    public ResultBean<List<SysUser>> listRoleUser(String roleId) {
        if (!StringUtils.hasText(roleId)) {
            throw new ParameterValidateException(BusinessCode.ROLE_ID_EMPTY_ERROR);
        }
        return ResultBean.restResult(sysRoleUserService.listRoleUser(roleId), ErrorCodeInfo.OK);
    }
}

