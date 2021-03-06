package com.cintsoft.ace.business.provider.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cintsoft.ace.business.provider.system.dao.SysResourceMapper;
import com.cintsoft.ace.business.provider.system.model.SysResource;
import com.cintsoft.ace.business.provider.system.model.SysRoleResource;
import com.cintsoft.ace.business.provider.system.model.SysRoleUser;
import com.cintsoft.ace.business.provider.system.model.SysUser;
import com.cintsoft.ace.business.provider.system.service.SysResourceService;
import com.cintsoft.ace.business.provider.system.service.SysRoleResourceService;
import com.cintsoft.ace.business.provider.system.service.SysRoleUserService;
import com.cintsoft.ace.business.provider.system.vo.SysResourceView;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 资源信息 服务实现类
 * </p>
 *
 * @author 胡昊
 * @since 2020-07-23
 */
@Transactional
@Service
public class SysResourceServiceImpl extends ServiceImpl<SysResourceMapper, SysResource> implements SysResourceService {

    @Resource
    private SysRoleResourceService sysRoleResourceService;
    @Resource
    private SysRoleUserService sysRoleUserService;

    @Override
    public Boolean deleteBatch(List<String> idList) {
        //递归删除该资源下子资源
        idList.forEach(id -> {
            final List<SysResource> sysResourceList = listChildResource(id);
            if (!CollectionUtils.isEmpty(sysResourceList)) {
                //删除资源与角色关联
                sysRoleResourceService.remove(Wrappers.<SysRoleResource>lambdaQuery().in(SysRoleResource::getResourceId, sysResourceList.stream().map(SysResource::getId).collect(Collectors.toList())));
                removeByIds(sysResourceList.stream().map(SysResource::getId).collect(Collectors.toList()));
            }
        });
        return true;
    }

    @Override
    public List<SysResourceView> treeSysResource() {
        return buildResourceTree(list());
    }

    @Override
    public List<SysResourceView> userTreeSysResource() {
        final SysUser sysUser = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final List<String> roleIdList = sysRoleUserService.list(Wrappers.<SysRoleUser>lambdaQuery().in(SysRoleUser::getUserId, sysUser.getId()))
                .stream()
                .map(SysRoleUser::getRoleId)
                .collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(roleIdList)) {
            final List<String> resourceIdList = sysRoleResourceService.list(Wrappers.<SysRoleResource>lambdaQuery().in(SysRoleResource::getRoleId, roleIdList))
                    .stream()
                    .map(SysRoleResource::getResourceId)
                    .collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(resourceIdList)) {
                return buildResourceTree(listByIds(resourceIdList));
            }
        }
        return Collections.emptyList();
    }

    private List<SysResourceView> buildResourceTree(List<SysResource> sysResourceList) {
        final List<SysResourceView> sysResourceViewList = sysResourceList
                .stream()
                .filter(sysResource -> sysResource.getParentId().equals("0"))
                .sorted(Comparator.comparingLong(SysResource::getCreateTime).reversed())
                .map(sysResource -> {
                    final SysResourceView sysResourceView = new SysResourceView();
                    BeanUtils.copyProperties(sysResource, sysResourceView);
                    return sysResourceView;
                })
                .collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(sysResourceViewList)) {
            treeChildSysResource(sysResourceViewList, sysResourceList);
        }
        return sysResourceViewList;
    }

    private void treeChildSysResource(List<SysResourceView> sysResourceViewList, List<SysResource> allSysResourceList) {
        sysResourceViewList.forEach(sysResourceView -> {
            final List<SysResourceView> childSysResourceViewList = allSysResourceList
                    .stream()
                    .filter(sysResource -> sysResource.getParentId().equals(sysResourceView.getId()))
                    .sorted(Comparator.comparingLong(SysResource::getCreateTime).reversed())
                    .map(sysResource -> {
                        final SysResourceView childSysResourceView = new SysResourceView();
                        BeanUtils.copyProperties(sysResource, childSysResourceView);
                        return childSysResourceView;
                    })
                    .collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(childSysResourceViewList)) {
                sysResourceView.setChildren(childSysResourceViewList);
                treeChildSysResource(childSysResourceViewList, allSysResourceList);
            }
        });
    }

    private List<SysResource> listChildResource(String id) {
        final List<SysResource> result = new ArrayList<>();
        final SysResource sysResource = getById(id);
        if (sysResource != null) {
            result.add(sysResource);
        }
        final List<SysResource> sysResourceList = list(Wrappers.<SysResource>lambdaQuery().eq(SysResource::getParentId, id).orderByDesc(SysResource::getCreateTime));
        if (!CollectionUtils.isEmpty(sysResourceList)) {
            listChildResource(sysResourceList, result);
        }
        return result;
    }

    private void listChildResource(List<SysResource> sysResourceList, List<SysResource> result) {
        result.addAll(sysResourceList);
        final List<SysResource> childSysResourceList = list(Wrappers.<SysResource>lambdaQuery().in(SysResource::getParentId, sysResourceList.stream().map(SysResource::getId).collect(Collectors.toList())).orderByDesc(SysResource::getCreateTime));
        if (!CollectionUtils.isEmpty(childSysResourceList)) {
            listChildResource(childSysResourceList, result);
        }
    }
}
