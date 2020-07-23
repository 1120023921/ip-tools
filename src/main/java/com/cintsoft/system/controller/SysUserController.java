package com.cintsoft.system.controller;


import com.cintsoft.common.enums.ErrorCodeInfo;
import com.cintsoft.common.vo.ResultBean;
import com.cintsoft.system.model.SysUser;
import com.cintsoft.system.service.SysUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

//    @PreAuthorize("isAuthenticated()")
    @GetMapping("/list")
    public ResultBean<List<SysUser>> list() {
        return ResultBean.restResult(sysUserService.list(), ErrorCodeInfo.OK);
    }
}

