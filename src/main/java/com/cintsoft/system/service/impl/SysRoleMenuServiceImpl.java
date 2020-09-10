package com.cintsoft.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cintsoft.system.model.SysMenu;
import com.cintsoft.system.model.SysRoleMenu;
import com.cintsoft.system.dao.SysRoleMenuMapper;
import com.cintsoft.system.service.SysMenuService;
import com.cintsoft.system.service.SysRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
 * 角色-菜单关联 服务实现类
 * </p>
 *
 * @author 胡昊
 * @since 2020-07-28
 */
@Transactional
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {

    @Resource
    private SysMenuService sysMenuService;

    @Override
    public Boolean saveRoleMenu(UserRoleResourceVo userRoleResourceVo) {
        //找出现有关联
        final List<SysRoleMenu> sysRoleMenuList = list(Wrappers.<SysRoleMenu>lambdaQuery().eq(SysRoleMenu::getRoleId, userRoleResourceVo.getRoleId()));
        //处理删除的
        final List<String> deleteIdList = sysRoleMenuList.stream()
                .filter(sysRoleMenu -> !userRoleResourceVo.getMenuIdList().contains(sysRoleMenu.getMenuId()))
                .map(SysRoleMenu::getId)
                .collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(deleteIdList)) {
            removeByIds(deleteIdList);
        }
        //处理新增的
        final List<String> menuIdList = sysRoleMenuList.stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList());
        final List<String> menuIdListNew = userRoleResourceVo.getMenuIdList().stream()
                .filter(id -> !menuIdList.contains(id))
                .collect(Collectors.toList());
        final List<SysRoleMenu> sysRoleMenuListNew = new ArrayList<>();
        menuIdListNew.forEach(id -> {
            final SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setRoleId(userRoleResourceVo.getRoleId());
            sysRoleMenu.setMenuId(id);
            sysRoleMenuListNew.add(sysRoleMenu);
        });
        return saveBatch(sysRoleMenuListNew);
    }

    @Override
    public List<SysMenu> listRoleMenu(String roleId) {
        final List<String> menuIdList = list(Wrappers.<SysRoleMenu>lambdaQuery().eq(SysRoleMenu::getRoleId, roleId))
                .stream()
                .map(SysRoleMenu::getMenuId)
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(menuIdList)) {
            return Collections.emptyList();
        }
        return sysMenuService.listByIds(menuIdList);
    }
}
