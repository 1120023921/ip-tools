package com.cintsoft.system.service;

import com.cintsoft.system.model.SysResource;
import com.cintsoft.system.model.SysRoleResource;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cintsoft.system.vo.UserRoleResourceVo;

import java.util.List;

/**
 * <p>
 * 角色-资源关联 服务类
 * </p>
 *
 * @author 胡昊
 * @since 2020-07-23
 */
public interface SysRoleResourceService extends IService<SysRoleResource> {

    /**
     * @param userRoleResourceVo 角色资源信息
     * @description 保存角色的资源
     * @author 胡昊
     * @email huhao9277@gmail.com
     * @date 2020/7/27 9:23
     */
    Boolean saveRoleResource(UserRoleResourceVo userRoleResourceVo);

    /**
     * @param roleId 角色id
     * @description 获取角色下的资源
     * @author 胡昊
     * @email huhao9277@gmail.com
     * @date 2020/7/27 9:44
     */
    List<SysResource> listRoleResource(String roleId);
}
