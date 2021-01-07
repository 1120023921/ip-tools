package com.cintsoft.ace.business.provider.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cintsoft.ace.business.provider.system.model.SysRole;

import java.util.List;

/**
 * <p>
 * 表基础信息 服务类
 * </p>
 *
 * @author 胡昊
 * @since 2020-07-23
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * @param idList 待删除角色id列表
     * @description 批量删除角色
     * @author 胡昊
     * @email huhao9277@gmail.com
     * @date 2020/7/25 16:15:39
     */
    Boolean deleteBatch(List<String> idList);
}
