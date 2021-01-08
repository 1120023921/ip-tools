package com.cintsoft.ace.business.provider.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cintsoft.ace.business.provider.system.constant.SecurityConstant;
import com.cintsoft.ace.business.provider.system.dao.SysUserMapper;
import com.cintsoft.ace.business.provider.system.model.SysResource;
import com.cintsoft.ace.business.provider.system.model.SysRoleResource;
import com.cintsoft.ace.business.provider.system.model.SysRoleUser;
import com.cintsoft.ace.business.provider.system.model.SysUser;
import com.cintsoft.ace.business.provider.system.service.SysResourceService;
import com.cintsoft.ace.business.provider.system.service.SysRoleResourceService;
import com.cintsoft.ace.business.provider.system.service.SysRoleUserService;
import com.cintsoft.ace.business.provider.system.service.SysUserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

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
@Transactional
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Resource
    private SysRoleUserService sysRoleUserService;
    @Resource
    private SysRoleResourceService sysRoleResourceService;
    @Resource
    private SysResourceService sysResourceService;
    @Resource
    private PasswordEncoder passwordEncoder;

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

    @Override
    public Boolean insert(SysUser sysUser) {
        //填充用户来源
        sysUser.setUserSource(SecurityConstant.USER_SOURCE);
        //密码加密
        sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
        return save(sysUser);
    }

    @Override
    public Boolean deleteBatch(List<String> idList) {
        //删除用户角色关联
        sysRoleUserService.remove(Wrappers.<SysRoleUser>lambdaQuery().in(SysRoleUser::getUserId, idList));
        return removeByIds(idList);
    }

    @Override
    public Boolean update(SysUser sysUser) {
        if (StringUtils.hasText(sysUser.getPassword())) {
            final SysUser sysUserOld = getById(sysUser.getId());
            //如果密码与原先不一致则代表密码更新
            if (!passwordEncoder.matches(sysUser.getPassword(), sysUserOld.getPassword())) {
                sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
            } else {
                sysUser.setPassword(null);
            }
        }
        return updateById(sysUser);
    }
}