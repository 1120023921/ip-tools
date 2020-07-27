package com.cintsoft.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cintsoft.system.model.SysRoleResource;
import com.cintsoft.system.dao.SysRoleResourceMapper;
import com.cintsoft.system.service.SysRoleResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cintsoft.system.vo.UserRoleResourceVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色-资源关联 服务实现类
 * </p>
 *
 * @author 胡昊
 * @since 2020-07-23
 */
@Transactional
@Service
public class SysRoleResourceServiceImpl extends ServiceImpl<SysRoleResourceMapper, SysRoleResource> implements SysRoleResourceService {

    @Override
    public Boolean saveRoleResource(UserRoleResourceVo userRoleResourceVo) {
        //找出现有关联
        final List<SysRoleResource> sysRoleResourceList = list(Wrappers.<SysRoleResource>lambdaQuery().eq(SysRoleResource::getRoleId, userRoleResourceVo.getRoleId()));
        //处理删除的
        final List<String> deleteIdList = sysRoleResourceList.stream()
                .filter(sysRoleResource -> !userRoleResourceVo.getResourceIdList().contains(sysRoleResource.getResourceId()))
                .map(SysRoleResource::getId)
                .collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(deleteIdList)) {
            removeByIds(deleteIdList);
        }
        //处理新增的
        final List<String> resourceIdList = sysRoleResourceList.stream().map(SysRoleResource::getResourceId).collect(Collectors.toList());
        final List<String> resourceIdListNew = userRoleResourceVo.getResourceIdList().stream()
                .filter(id -> !resourceIdList.contains(id))
                .collect(Collectors.toList());
        final List<SysRoleResource> sysRoleResourceListNew = new ArrayList<>();
        resourceIdListNew.forEach(id -> {
            final SysRoleResource sysRoleResource = new SysRoleResource();
            sysRoleResource.setRoleId(userRoleResourceVo.getRoleId());
            sysRoleResource.setResourceId(id);
            sysRoleResourceListNew.add(sysRoleResource);
        });
        return saveBatch(sysRoleResourceListNew);
    }
}
