package com.cintsoft.ace.business.provider.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cintsoft.ace.business.provider.system.model.SysResource;
import com.cintsoft.ace.business.provider.system.vo.SysResourceView;

import java.util.List;

/**
 * <p>
 * 资源信息 服务类
 * </p>
 *
 * @author 胡昊
 * @since 2020-07-23
 */
public interface SysResourceService extends IService<SysResource> {

    /**
     * @param idList 待删除资源id列表
     * @description 批量删除资源
     * @author 胡昊
     * @email huhao9277@gmail.com
     * @date 2020/7/25 20:47:10
     */
    Boolean deleteBatch(List<String> idList);

    /**
     * @description 获取资源树形结构
     * @author 胡昊
     * @email huhao9277@gmail.com
     * @date 2020/7/25 21:28:02
     */
    List<SysResourceView> treeSysResource();

    /**
     * @description 用户获取菜单
     * @author 胡昊
     * @email huhao9277@gmail.com
     * @date 2021/1/7 22:38
     */
    List<SysResourceView> userTreeSysResource();
}
