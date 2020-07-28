package com.cintsoft.system.service.impl;

import com.cintsoft.system.model.SysRoleMenu;
import com.cintsoft.system.dao.SysRoleMenuMapper;
import com.cintsoft.system.service.SysRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
