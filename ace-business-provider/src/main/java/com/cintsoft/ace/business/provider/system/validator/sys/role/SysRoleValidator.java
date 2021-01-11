package com.cintsoft.ace.business.provider.system.validator.sys.role;

import com.cintsoft.ace.business.provider.system.constant.SysBusinessCode;
import com.cintsoft.ace.business.provider.system.model.SysRole;
import com.cintsoft.ace.business.provider.system.validator.BaseValidator;
import com.cintsoft.common.exception.BusinessException;
import com.cintsoft.common.exception.ParameterValidateException;
import org.springframework.util.StringUtils;

public class SysRoleValidator extends BaseValidator {

    public static void insertValidate(SysRole sysRole) {
        if (!StringUtils.hasText(sysRole.getName())) {
            throw new ParameterValidateException(SysBusinessCode.ROLE_NAME_EMPTY_ERROR.getBusinessCode());
        }
        if (!StringUtils.hasText(sysRole.getRoleKey())) {
            throw new ParameterValidateException(SysBusinessCode.ROLE_KEY_EMPTY_ERROR.getBusinessCode());
        }
    }

    public static void updateValidate(SysRole sysRole) {
        if (!StringUtils.hasText(sysRole.getId())) {
            throw new BusinessException(SysBusinessCode.ID_EMPTY_ERROR.getBusinessCode());
        }
        insertValidate(sysRole);
    }
}
