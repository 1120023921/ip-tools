package com.cintsoft.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cintsoft.system.model.SysResource;
import com.cintsoft.system.model.SysRoleResource;
import com.cintsoft.system.model.SysRoleUser;
import com.cintsoft.system.model.SysUser;
import com.cintsoft.system.dao.SysUserMapper;
import com.cintsoft.system.service.SysResourceService;
import com.cintsoft.system.service.SysRoleResourceService;
import com.cintsoft.system.service.SysRoleUserService;
import com.cintsoft.system.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author 胡昊
 * @since 2020-07-23
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Resource
    private SysRoleUserService sysRoleUserService;
    @Resource
    private SysRoleResourceService sysRoleResourceService;
    @Resource
    private SysResourceService sysResourceService;

    @Override
    public SysUser loadUserByUsername(String username) throws UsernameNotFoundException {
        final SysUser sysUser = getOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUsername, username));
        if (sysUser == null) {
            return null;
        }
        final List<SysRoleUser> sysRoleUserList = sysRoleUserService.list(Wrappers.<SysRoleUser>lambdaQuery().eq(SysRoleUser::getUserId, sysUser.getId()));
        if (!CollectionUtils.isEmpty(sysRoleUserList)) {
            final List<SysRoleResource> sysRoleResourceList = sysRoleResourceService.list(Wrappers.<SysRoleResource>lambdaQuery().in(SysRoleResource::getRoleId, sysRoleUserList.stream().map(SysRoleUser::getRoleId).collect(Collectors.toList())));
            if (!CollectionUtils.isEmpty(sysRoleResourceList)) {
                final List<SysResource> sysResourceList = sysResourceService.listByIds(sysRoleResourceList.stream().map(SysRoleResource::getResourceId).collect(Collectors.toList()));
                if (!CollectionUtils.isEmpty(sysResourceList)) {
                    sysUser.setSysResourceList(sysResourceList);
                }
            }
        }
        return sysUser;
    }
}
