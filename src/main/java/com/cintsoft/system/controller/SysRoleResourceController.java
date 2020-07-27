package com.cintsoft.system.controller;


import com.cintsoft.common.enums.ErrorCodeInfo;
import com.cintsoft.common.vo.ResultBean;
import com.cintsoft.system.service.SysRoleResourceService;
import com.cintsoft.system.validator.sys.roleresource.SysRoleResourceValidator;
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
 * 角色-资源关联 前端控制器
 * </p>
 *
 * @author 胡昊
 * @since 2020-07-23
 */
@Api(value = "/sysRoleResource", tags = "角色-资源管理")
@RestController
@RequestMapping("/sysRoleResource")
public class SysRoleResourceController {

    @Resource
    private SysRoleResourceService sysRoleResourceService;

    /**
     * @param userRoleResourceVo 角色资源信息
     * @description 保存角色的资源
     * @author 胡昊
     * @email huhao9277@gmail.com
     * @date 2020/7/27 9:23
     */
    @ApiOperation("保存角色的资源")
    @PreAuthorize("@cintSecurity.hasPermission('sysRoleResource:saveRoleResource')")
    @PostMapping("/saveRoleResource")
    public ResultBean<Boolean> saveRoleResource(@RequestBody UserRoleResourceVo userRoleResourceVo) {
        //参数校验
        SysRoleResourceValidator.saveRoleResource(userRoleResourceVo);
        return ResultBean.restResult(sysRoleResourceService.saveRoleResource(userRoleResourceVo), ErrorCodeInfo.OK);
    }
}

