package com.cintsoft.system.controller;


import com.cintsoft.common.enums.ErrorCodeInfo;
import com.cintsoft.common.vo.ResultBean;
import com.cintsoft.system.model.SysResource;
import com.cintsoft.system.service.SysResourceService;
import com.cintsoft.system.validator.sys.resource.SysResourceValidator;
import com.cintsoft.system.vo.SysResourceView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 资源信息 前端控制器
 * </p>
 *
 * @author 胡昊
 * @since 2020-07-23
 */
@Api(value = "/sysResource", tags = "资源管理")
@RestController
@RequestMapping("/sysResource")
public class SysResourceController {

    @Resource
    private SysResourceService sysResourceService;

    /**
     * @param sysResource 资源信息
     * @description 新增资源
     * @author 胡昊
     * @email huhao9277@gmail.com
     * @date 2020/7/25 20:39:11
     */
    @ApiOperation("新增资源")
    @PreAuthorize("@cintSecurity.hasPermission('sysResource:insert')")
    @PostMapping("/insert")
    public ResultBean<Boolean> insert(@RequestBody SysResource sysResource) {
        //参数校验
        SysResourceValidator.insertValidate(sysResource);
        return ResultBean.restResult(sysResourceService.save(sysResource), ErrorCodeInfo.CREATED);
    }

    /**
     * @param idList 待删除资源id列表
     * @description 批量删除资源
     * @author 胡昊
     * @email huhao9277@gmail.com
     * @date 2020/7/25 20:47:10
     */
    @ApiOperation("批量删除资源")
    @PreAuthorize("@cintSecurity.hasPermission('sysResource:deleteBatch')")
    @DeleteMapping("/deleteBatch")
    public ResultBean<Boolean> deleteBatch(@RequestBody List<String> idList) {
        //参数校验
        SysResourceValidator.deleteValidate(idList);
        return ResultBean.restResult(sysResourceService.deleteBatch(idList), ErrorCodeInfo.NO_CONTENT);
    }

    /**
     * @param sysResource 资源信息
     * @description 更新资源
     * @author 胡昊
     * @email huhao9277@gmail.com
     * @date 2020/7/25 21:14:40
     */
    @ApiOperation("更新资源")
    @PreAuthorize("@cintSecurity.hasPermission('sysResource:update')")
    @PatchMapping("/update")
    public ResultBean<Boolean> update(@RequestBody SysResource sysResource) {
        //参数校验
        SysResourceValidator.updateValidate(sysResource);
        return ResultBean.restResult(sysResourceService.updateById(sysResource), ErrorCodeInfo.OK);
    }

    /**
     * @description 获取资源树形结构
     * @author 胡昊
     * @email huhao9277@gmail.com
     * @date 2020/7/25 21:28:02
     */
    @ApiOperation("获取资源树形结构")
    @PreAuthorize("@cintSecurity.hasPermission('sysResource:treeSysResource')")
    @GetMapping("/treeSysResource")
    public ResultBean<List<SysResourceView>> treeSysResource() {
        return ResultBean.restResult(sysResourceService.treeSysResource(), ErrorCodeInfo.OK);
    }

    /**
     * @description 用户获取菜单
     * @author 胡昊
     * @email huhao9277@gmail.com
     * @date 2021/1/7 22:38
     */
    @ApiOperation("用户获取菜单")
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/userTreeSysResource")
    public ResultBean<List<SysResourceView>> userTreeSysResource() {
        return ResultBean.restResult(sysResourceService.userTreeSysResource(), ErrorCodeInfo.OK);
    }
}

