package com.cintsoft.ace.business.provider.system.controller;


import com.cintsoft.ace.business.provider.system.constant.SysBusinessCode;
import com.cintsoft.ace.business.provider.system.model.SysResource;
import com.cintsoft.ace.business.provider.system.service.SysRoleResourceService;
import com.cintsoft.ace.business.provider.system.validator.sys.roleresource.SysRoleResourceValidator;
import com.cintsoft.ace.business.provider.system.vo.UserRoleResourceVo;
import com.cintsoft.common.exception.ParameterValidateException;
import com.cintsoft.common.web.ErrorCodeInfo;
import com.cintsoft.common.web.ResultBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

    /**
     * @param roleId 角色id
     * @description 获取角色下的资源
     * @author 胡昊
     * @email huhao9277@gmail.com
     * @date 2020/7/27 9:44
     */
    @ApiOperation("获取角色下的资源")
    @PreAuthorize("@cintSecurity.hasPermission('sysRoleResource:listRoleResource')")
    @GetMapping("/listRoleResource")
    public ResultBean<List<SysResource>> listRoleResource(String roleId) {
        //参数校验
        if (!StringUtils.hasText(roleId)) {
            throw new ParameterValidateException(SysBusinessCode.ROLE_ID_EMPTY_ERROR.getBusinessCode());
        }
        return ResultBean.restResult(sysRoleResourceService.listRoleResource(roleId), ErrorCodeInfo.OK);
    }
}

