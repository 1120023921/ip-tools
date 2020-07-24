package com.cintsoft.system.service;

import com.cintsoft.system.model.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * <p>
 * 用户信息 服务类
 * </p>
 *
 * @author 胡昊
 * @since 2020-07-23
 */
public interface SysUserService extends IService<SysUser>, UserDetailsService {

    /**
     * @param sysUser 用户信息
     * @description 新增用户
     * @author 胡昊
     * @email huhao9277@gmail.com
     * @date 2020/7/24 13:28
     */
    Boolean insert(SysUser sysUser);

    /**
     * @param idList 待删除用户id列表
     * @description 批量删除用户
     * @author 胡昊
     * @email huhao9277@gmail.com
     * @date 2020/7/24 13:51
     */
    Boolean deleteBatch(List<String> idList);

    /**
     * @param sysUser 用户信息
     * @description 更新用户信息
     * @author 胡昊
     * @email huhao9277@gmail.com
     * @date 2020/7/24 15:25
     */
    Boolean update(SysUser sysUser);
}
