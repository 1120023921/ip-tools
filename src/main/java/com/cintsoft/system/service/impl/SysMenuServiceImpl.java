package com.cintsoft.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cintsoft.system.model.SysMenu;
import com.cintsoft.system.dao.SysMenuMapper;
import com.cintsoft.system.model.SysRoleMenu;
import com.cintsoft.system.service.SysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cintsoft.system.service.SysRoleMenuService;
import com.cintsoft.system.vo.SysMenuView;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单信息 服务实现类
 * </p>
 *
 * @author 胡昊
 * @since 2020-07-27
 */
@Transactional
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Resource
    private SysRoleMenuService sysRoleMenuService;

    @Override
    public Boolean deleteBatch(List<String> idList) {
        //递归删除该菜单以及其下子菜单，以及对应角色关联
        deleteChildrenMenu(idList);
        return true;
    }

    @Override
    public List<SysMenuView> treeSysMenu() {
        final List<SysMenu> allSysMenuList = list();
        final List<SysMenuView> sysMenuViewList = allSysMenuList
                .stream()
                .filter(sysMenu -> sysMenu.getParentId().equals("0"))
                .sorted(Comparator.comparingLong(SysMenu::getCreateTime).reversed())
                .map(sysMenu -> {
                    final SysMenuView sysMenuView = new SysMenuView();
                    BeanUtils.copyProperties(sysMenu, sysMenuView);
                    return sysMenuView;
                })
                .collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(sysMenuViewList)) {
            treeChildSysMenu(sysMenuViewList, allSysMenuList);
        }
        return sysMenuViewList;
    }

    private void deleteChildrenMenu(List<String> idList) {
        //删除菜单
        removeByIds(idList);
        //删除菜单与角色关联
        sysRoleMenuService.remove(Wrappers.<SysRoleMenu>lambdaQuery().in(SysRoleMenu::getMenuId, idList));
        final List<String> childrenSysMenuIdList = list(Wrappers.<SysMenu>lambdaQuery().in(SysMenu::getParentId, idList))
                .stream()
                .map(SysMenu::getId)
                .collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(childrenSysMenuIdList)) {
            deleteChildrenMenu(childrenSysMenuIdList);
        }
    }

    private void treeChildSysMenu(List<SysMenuView> sysMenuViewList, List<SysMenu> allSysMenuList) {
        sysMenuViewList.forEach(sysMenuView -> {
            final List<SysMenuView> childSysMenuViewList = allSysMenuList
                    .stream()
                    .filter(sysMenu -> sysMenu.getParentId().equals(sysMenuView.getId()))
                    .sorted(Comparator.comparingLong(SysMenu::getWeight).reversed())
                    .map(sysMenu -> {
                        final SysMenuView childSysMenuView = new SysMenuView();
                        BeanUtils.copyProperties(sysMenu, childSysMenuView);
                        return childSysMenuView;
                    })
                    .collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(childSysMenuViewList)) {
                sysMenuView.setChildren(childSysMenuViewList);
                treeChildSysMenu(childSysMenuViewList, allSysMenuList);
            }
        });
    }
}
