package com.cintsoft.system.service.impl;

import com.cintsoft.system.model.SysRoleUser;
import com.cintsoft.system.dao.SysRoleUserMapper;
import com.cintsoft.system.service.SysRoleUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色-用户关联 服务实现类
 * </p>
 *
 * @author 胡昊
 * @since 2020-07-23
 */
@Service
public class SysRoleUserServiceImpl extends ServiceImpl<SysRoleUserMapper, SysRoleUser> implements SysRoleUserService {

}
