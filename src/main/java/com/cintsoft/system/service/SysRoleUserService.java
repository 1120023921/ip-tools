package com.cintsoft.system.service;

import com.cintsoft.system.model.SysRole;
import com.cintsoft.system.model.SysRoleUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cintsoft.system.model.SysUser;
import com.cintsoft.system.vo.UserRoleResourceVo;

import java.util.List;

/**
 * <p>
 * 角色-用户关联 服务类
 * </p>
 *
 * @author 胡昊
 * @since 2020-07-23
 */
public interface SysRoleUserService extends IService<SysRoleUser> {

    /**
     * @param userRoleResourceVo 用户角色参数
     * @description 保存用户的角色
     * @author 胡昊
     * @email huhao9277@gmail.com
     * @date 2020/7/26 15:21:03
     */
    Boolean saveUserRoleValidate(UserRoleResourceVo userRoleResourceVo);

    /**
     * @param userId 用户id
     * @description 获取用户下角色
     * @author 胡昊
     * @email huhao9277@gmail.com
     * @date 2020/7/27 9:56
     */
    List<SysRole> listUserRole(String userId);

    /**
     * @param userRoleResourceVo 角色用户参数
     * @description 保存角色用户
     * @author 胡昊
     * @email huhao9277@gmail.com
     * @date 2020/8/7 16:43:05
     */
    Boolean saveRoleUser(UserRoleResourceVo userRoleResourceVo);

    /**
     * @param roleId 角色id
     * @description 获取角色下用户
     * @author 胡昊
     * @email huhao9277@gmail.com
     * @date 2020/8/7 16:32:47
     */
    List<SysUser> listRoleUser(String roleId);
}
