package com.cintsoft.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cintsoft.system.model.SysRole;
import com.cintsoft.system.model.SysRoleUser;
import com.cintsoft.system.dao.SysRoleUserMapper;
import com.cintsoft.system.model.SysUser;
import com.cintsoft.system.service.SysRoleService;
import com.cintsoft.system.service.SysRoleUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cintsoft.system.service.SysUserService;
import com.cintsoft.system.vo.UserRoleResourceVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色-用户关联 服务实现类
 * </p>
 *
 * @author 胡昊
 * @since 2020-07-23
 */
@Transactional
@Service
public class SysRoleUserServiceImpl extends ServiceImpl<SysRoleUserMapper, SysRoleUser> implements SysRoleUserService {

    @Resource
    private SysRoleService sysRoleService;
    @Resource
    private SysUserService sysUserService;

    @Override
    public Boolean saveUserRoleValidate(UserRoleResourceVo userRoleResourceVo) {
        //找出现有关联
        final List<SysRoleUser> sysRoleUserList = list(Wrappers.<SysRoleUser>lambdaQuery().eq(SysRoleUser::getUserId, userRoleResourceVo.getUserId()));
        //处理删除的
        final List<String> deleteIdList = sysRoleUserList.stream()
                .filter(sysRoleUser -> !userRoleResourceVo.getRoleIdList().contains(sysRoleUser.getRoleId()))
                .map(SysRoleUser::getId)
                .collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(deleteIdList)) {
            removeByIds(deleteIdList);
        }
        //处理新增的
        final List<String> roleIdList = sysRoleUserList.stream().map(SysRoleUser::getRoleId).collect(Collectors.toList());
        final List<String> roleIdListNew = userRoleResourceVo.getRoleIdList().stream()
                .filter(id -> !roleIdList.contains(id))
                .collect(Collectors.toList());
        final List<SysRoleUser> sysRoleUserListNew = new ArrayList<>();
        roleIdListNew.forEach(id -> {
            final SysRoleUser sysRoleUser = new SysRoleUser();
            sysRoleUser.setUserId(userRoleResourceVo.getUserId());
            sysRoleUser.setRoleId(id);
            sysRoleUserListNew.add(sysRoleUser);
        });
        return saveBatch(sysRoleUserListNew);
    }

    @Override
    public List<SysRole> listUserRole(String userId) {
        final List<String> roleIdList = list(Wrappers.<SysRoleUser>lambdaQuery().eq(SysRoleUser::getUserId, userId))
                .stream()
                .map(SysRoleUser::getRoleId)
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(roleIdList)) {
            return Collections.emptyList();
        }
        return sysRoleService.listByIds(roleIdList);
    }

    @Override
    public Boolean saveRoleUser(UserRoleResourceVo userRoleResourceVo) {
        //找出现有关联
        final List<SysRoleUser> sysRoleUserList = list(Wrappers.<SysRoleUser>lambdaQuery().eq(SysRoleUser::getRoleId, userRoleResourceVo.getRoleId()));
        //处理删除的
        final List<String> deleteIdList = sysRoleUserList.stream()
                .filter(sysRoleUser -> !userRoleResourceVo.getUserIdList().contains(sysRoleUser.getUserId()))
                .map(SysRoleUser::getId)
                .collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(deleteIdList)) {
            removeByIds(deleteIdList);
        }
        //处理新增的
        final List<String> userIdList = sysRoleUserList.stream().map(SysRoleUser::getUserId).collect(Collectors.toList());
        final List<String> userIdListNew = userRoleResourceVo.getUserIdList().stream()
                .filter(id -> !userIdList.contains(id))
                .collect(Collectors.toList());
        final List<SysRoleUser> sysRoleUserListNew = new ArrayList<>();
        userIdListNew.forEach(id -> {
            final SysRoleUser sysRoleUser = new SysRoleUser();
            sysRoleUser.setUserId(id);
            sysRoleUser.setRoleId(userRoleResourceVo.getRoleId());
            sysRoleUserListNew.add(sysRoleUser);
        });
        return saveBatch(sysRoleUserListNew);
    }

    @Override
    public List<SysUser> listRoleUser(String roleId) {
        final List<String> userIdList = list(Wrappers.<SysRoleUser>lambdaQuery().eq(SysRoleUser::getRoleId, roleId))
                .stream()
                .map(SysRoleUser::getUserId)
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(userIdList)) {
            return Collections.emptyList();
        }
        return sysUserService.listByIds(userIdList);
    }
}
