package com.cintsoft.system.controller;


import com.cintsoft.common.enums.ErrorCodeInfo;
import com.cintsoft.common.exception.BusinessCode;
import com.cintsoft.common.exception.ParameterValidateException;
import com.cintsoft.common.vo.ResultBean;
import com.cintsoft.system.model.SysMenu;
import com.cintsoft.system.service.SysRoleMenuService;
import com.cintsoft.system.validator.sys.rolemenu.SysRoleMenuValidator;
import com.cintsoft.system.vo.UserRoleResourceVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 角色-菜单关联 前端控制器
 * </p>
 *
 * @author 胡昊
 * @since 2020-07-28
 */
@Api(value = "/sysRoleMenu", tags = "角色-菜单管理")
@RestController
@RequestMapping("/sysRoleMenu")
public class SysRoleMenuController {

    @Resource
    private SysRoleMenuService sysRoleMenuService;

    /**
     * @param userRoleResourceVo 角色菜单信息
     * @description 保存角色的菜单
     * @author 胡昊
     * @email huhao9277@gmail.com
     * @date 2020/7/28 11:22
     */
    @ApiOperation("保存角色的菜单")
    @PreAuthorize("@cintSecurity.hasPermission('sysRoleMenu:saveRoleMenu')")
    @PostMapping("/saveRoleMenu")
    public ResultBean<Boolean> saveRoleMenu(@RequestBody UserRoleResourceVo userRoleResourceVo) {
        //参数校验
        SysRoleMenuValidator.saveRoleMenu(userRoleResourceVo);
        return ResultBean.restResult(sysRoleMenuService.saveRoleMenu(userRoleResourceVo), ErrorCodeInfo.OK);
    }

    /**
     * @param roleId 角色id
     * @description 获取角色下的菜单
     * @author 胡昊
     * @email huhao9277@gmail.com
     * @date 2020/7/28 11:56
     */
    @ApiOperation("获取角色下的菜单")
    @PreAuthorize("@cintSecurity.hasPermission('sysRoleMenu:listRoleMenu')")
    @GetMapping("/listRoleMenu")
    public ResultBean<List<SysMenu>> listRoleMenu(String roleId) {
        //参数校验
        if (!StringUtils.hasText(roleId)) {
            throw new ParameterValidateException(BusinessCode.ROLE_ID_EMPTY_ERROR);
        }
        return ResultBean.restResult(sysRoleMenuService.listRoleMenu(roleId), ErrorCodeInfo.OK);
    }
}

