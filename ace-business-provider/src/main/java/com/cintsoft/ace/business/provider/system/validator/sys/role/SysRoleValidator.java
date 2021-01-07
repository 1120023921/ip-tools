package com.cintsoft.ace.business.provider.system.validator.sys.role;

import com.cintsoft.ace.business.provider.common.exception.BusinessCode;
import com.cintsoft.ace.business.provider.common.exception.BusinessException;
import com.cintsoft.ace.business.provider.common.exception.ParameterValidateException;
import com.cintsoft.ace.business.provider.system.model.SysRole;
import com.cintsoft.ace.business.provider.system.validator.BaseValidator;
import org.springframework.util.StringUtils;

public class SysRoleValidator extends BaseValidator {

    public static void insertValidate(SysRole sysRole) {
        if (!StringUtils.hasText(sysRole.getName())) {
            throw new ParameterValidateException(BusinessCode.ROLE_NAME_EMPTY_ERROR);
        }
        if (!StringUtils.hasText(sysRole.getRoleKey())) {
            throw new ParameterValidateException(BusinessCode.ROLE_KEY_EMPTY_ERROR);
        }
    }

    public static void updateValidate(SysRole sysRole) {
        if (!StringUtils.hasText(sysRole.getId())) {
            throw new BusinessException(BusinessCode.ID_EMPTY_ERROR);
        }
        insertValidate(sysRole);
    }
}
