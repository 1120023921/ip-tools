package com.cintsoft.system.service;

import com.cintsoft.system.model.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * <p>
 * 用户信息 服务类
 * </p>
 *
 * @author 胡昊
 * @since 2020-07-23
 */
public interface SysUserService extends IService<SysUser>, UserDetailsService {

}
