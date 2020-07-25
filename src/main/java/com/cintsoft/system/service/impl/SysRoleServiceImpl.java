package com.cintsoft.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cintsoft.system.model.SysRole;
import com.cintsoft.system.dao.SysRoleMapper;
import com.cintsoft.system.model.SysRoleResource;
import com.cintsoft.system.model.SysRoleUser;
import com.cintsoft.system.service.SysRoleResourceService;
import com.cintsoft.system.service.SysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cintsoft.system.service.SysRoleUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 表基础信息 服务实现类
 * </p>
 *
 * @author 胡昊
 * @since 2020-07-23
 */
@Transactional
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Resource
    private SysRoleResourceService sysRoleResourceService;
    @Resource
    private SysRoleUserService sysRoleUserService;

    @Override
    public Boolean deleteBatch(List<String> idList) {
        //删除角色与资源关联
        sysRoleResourceService.remove(Wrappers.<SysRoleResource>lambdaQuery().in(SysRoleResource::getRoleId, idList));
        //删除角色与用户关联
        sysRoleUserService.remove(Wrappers.<SysRoleUser>lambdaQuery().in(SysRoleUser::getRoleId, idList));
        return removeByIds(idList);
    }
}
