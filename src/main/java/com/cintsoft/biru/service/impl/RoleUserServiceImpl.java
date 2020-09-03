package com.cintsoft.biru.service.impl;

import com.cintsoft.biru.model.RoleUser;
import com.cintsoft.biru.dao.RoleUserMapper;
import com.cintsoft.biru.service.RoleUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色-用户关联 服务实现类
 * </p>
 *
 * @author 胡昊
 * @since 2020-09-03
 */
@Service
public class RoleUserServiceImpl extends ServiceImpl<RoleUserMapper, RoleUser> implements RoleUserService {

}
