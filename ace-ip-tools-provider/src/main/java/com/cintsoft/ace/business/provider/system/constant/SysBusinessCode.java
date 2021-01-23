package com.cintsoft.ace.business.provider.system.constant;


/**
 * @author 胡昊
 * Description: 业务异常错误码
 * Date: 2019/7/3
 * Time: 10:40
 * Create: DoubleH
 */
public enum SysBusinessCode {


    ID_LIST_EMPTY_ERROR(30001, "id列表不能为空"),
    USERNAME_EMPTY_ERROR(30002, "id列表不能为空"),
    PASSWORD_EMPTY_ERROR(30003, "密码不能为空"),
    ID_EMPTY_ERROR(30004, "id不能为空"),
    ROLE_NAME_EMPTY_ERROR(30005, "角色名称不能为空"),
    ROLE_KEY_EMPTY_ERROR(30006, "角色标识符不能为空"),
    RESOURCE_NAME_EMPTY_ERROR(30007, "资源名称不能为空"),
    RESOURCE_KEY_EMPTY_ERROR(30008, "资源标识符不能为空"),
    PARENT_ID_EMPTY_ERROR(30009, "父id不能为空"),
    USER_ID_EMPTY_ERROR(30010, "用户id不能为空"),
    ROLE_ID_LIST_NOT_EXIST_ERROR(30011, "角色id列表不存在"),
    ROLE_ID_EMPTY_ERROR(30012, "角色id不能为空"),
    RESOURCE_ID_LIST_NOT_EXIST_ERROR(30013, "资源id列表不存在"),
    MENU_NAME_EMPTY_ERROR(30014, "菜单名称不能为空"),
    MENU_TYPE_EMPTY_ERROR(30015, "菜单类型不能为空"),
    MENU_PATH_EMPTY_ERROR(30016, "菜单路径不能为空"),
    MENU_ICON_EMPTY_ERROR(30017, "菜单图标不能为空"),
    PARENT_ID_NOT_EXIST_ERROR(30018, "父id不存在"),
    MENU_ID_EMPTY_ERROR(30019, "菜单id不能为空"),
    MENU_ID_LIST_NOT_EXIST_ERROR(30020, "菜单id列表不存在"),
    USER_ID_LIST_NOT_EXIST_ERROR(30021, "用户id列表不存在"),
    DIS_NAME_EMPTY_ERROR(30022, "菜单显示名称不能为空");

    private final com.cintsoft.common.exception.BusinessCode businessCode = new com.cintsoft.common.exception.BusinessCode();

    SysBusinessCode(Integer code, String msg) {
        this.businessCode.setCode(code);
        this.businessCode.setMsg(msg);
    }

    public com.cintsoft.common.exception.BusinessCode getBusinessCode() {
        return businessCode;
    }
}
