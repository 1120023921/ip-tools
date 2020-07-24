package com.cintsoft.common.exception;


/**
 * @author 胡昊
 * Description: 业务异常错误码
 * Date: 2019/7/3
 * Time: 10:40
 * Create: DoubleH
 */
public enum BusinessCode {


    ID_LIST_EMPTY_ERROR(30001, "ID列表不能为空"),
    USERNAME_EMPTY_ERROR(30002, "ID列表不能为空"),
    PASSWORD_EMPTY_ERROR(30003, "密码不能为空"),
    ID_EMPTY_ERROR(30004, "ID不能为空");

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
