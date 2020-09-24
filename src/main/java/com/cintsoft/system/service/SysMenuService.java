package com.cintsoft.system.service;

import com.cintsoft.system.model.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cintsoft.system.vo.SysMenuView;

import java.util.List;

/**
 * <p>
 * 菜单信息 服务类
 * </p>
 *
 * @author 胡昊
 * @since 2020-07-27
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * @param idList 带删除菜单id列表
     * @description 批量删除菜单
     * @author 胡昊
     * @email huhao9277@gmail.com
     * @date 2020/7/28 9:44
     */
    Boolean deleteBatch(List<String> idList);

    /**
     * @description 查询菜单树形结构
     * @author 胡昊
     * @email huhao9277@gmail.com
     * @date 2020/7/28 10:41
     */
    List<SysMenuView> treeSysMenu();

    /**
     * @description 查询用户菜单树形结构
     * @author 胡昊
     * @email huhao9277@gmail.com
     * @date 2020/9/14 11:09
     */
    List<SysMenuView> treeUserSysMenu();
}
