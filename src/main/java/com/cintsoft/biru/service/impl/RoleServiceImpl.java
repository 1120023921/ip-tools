package com.cintsoft.biru.service.impl;

import com.cintsoft.biru.model.Role;
import com.cintsoft.biru.dao.RoleMapper;
import com.cintsoft.biru.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色信息 服务实现类
 * </p>
 *
 * @author 胡昊
 * @since 2020-09-03
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
