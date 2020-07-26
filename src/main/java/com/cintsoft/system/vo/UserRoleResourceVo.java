package com.cintsoft.system.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author 胡昊
 * Description: 用户角色资源参数
 * Date: 2020/7/26
 * Time: 14:44
 * Mail: huhao9277@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserRoleResourceVo {

    private String roleId;
    private List<String> resourceIdList;

    private String resourceId;
    private List<String> roleIdList;

    private String userId;
    private List<String> userIdList;
}
