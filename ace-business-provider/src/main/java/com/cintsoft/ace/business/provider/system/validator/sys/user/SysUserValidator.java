package com.cintsoft.ace.business.provider.system.validator.sys.user;

import com.cintsoft.ace.business.provider.system.constant.SysBusinessCode;
import com.cintsoft.ace.business.provider.system.model.SysUser;
import com.cintsoft.ace.business.provider.system.validator.BaseValidator;
import com.cintsoft.common.exception.ParameterValidateException;
import org.springframework.util.StringUtils;

/**
 * @author 胡昊
 * Description: 用户参数校验器
 * Date: 2020/7/24
 * Time: 13:38
 * Mail: huhao9277@gmail.com
 */
public class SysUserValidator extends BaseValidator {

    public static void insertValidate(SysUser sysUser) {
        if (!StringUtils.hasText(sysUser.getUsername())) {
            throw new ParameterValidateException(SysBusinessCode.USERNAME_EMPTY_ERROR.getBusinessCode());
        }
        if (!StringUtils.hasText(sysUser.getPassword())) {
            throw new ParameterValidateException(SysBusinessCode.PASSWORD_EMPTY_ERROR.getBusinessCode());
        }
    }

    public static void updateValidate(SysUser sysUser) {
        if (!StringUtils.hasText(sysUser.getId())) {
            throw new ParameterValidateException(SysBusinessCode.ID_EMPTY_ERROR.getBusinessCode());
        }
        if (!StringUtils.hasText(sysUser.getUsername())) {
            throw new ParameterValidateException(SysBusinessCode.USERNAME_EMPTY_ERROR.getBusinessCode());
        }
    }
}
