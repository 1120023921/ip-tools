package com.cintsoft.system.service;

import com.cintsoft.system.model.SysRole;
import com.cintsoft.system.model.SysRoleUser;
import com.baomidou.mybatisplus.extension.service.IService;
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
}
