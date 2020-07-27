package com.cintsoft.common.exception;


/**
 * @author 胡昊
 * Description: 业务异常错误码
 * Date: 2019/7/3
 * Time: 10:40
 * Create: DoubleH
 */
public enum BusinessCode {


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
    PARENT_ID_NOT_EXIST_ERROR(30018, "父id不存在");

    private final Integer code;
    private final String msg;

    BusinessCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
