package com.cintsoft.system.service;

import com.cintsoft.system.model.SysMenu;
import com.cintsoft.system.model.SysRoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cintsoft.system.vo.UserRoleResourceVo;

import java.util.List;

/**
 * <p>
 * 角色-菜单关联 服务类
 * </p>
 *
 * @author 胡昊
 * @since 2020-07-28
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {

    /**
     * @param userRoleResourceVo 角色菜单信息
     * @description 保存角色的菜单
     * @author 胡昊
     * @email huhao9277@gmail.com
     * @date 2020/7/28 11:22
     */
    Boolean saveRoleMenu(UserRoleResourceVo userRoleResourceVo);

    /**
     * @param roleId 角色id
     * @description 获取角色下的菜单
     * @author 胡昊
     * @email huhao9277@gmail.com
     * @date 2020/7/28 11:56
     */
    List<SysMenu> listRoleMenu(String roleId);
}
