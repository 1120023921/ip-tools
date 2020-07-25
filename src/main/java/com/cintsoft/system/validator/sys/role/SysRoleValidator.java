package com.cintsoft.system.validator.sys.role;

import com.cintsoft.common.exception.BusinessCode;
import com.cintsoft.common.exception.BusinessException;
import com.cintsoft.common.exception.ParameterValidateException;
import com.cintsoft.system.model.SysRole;
import com.cintsoft.system.validator.BaseValidator;
import org.springframework.util.StringUtils;

public class SysRoleValidator extends BaseValidator {

    public static void insertValidate(SysRole sysRole) {
        if (StringUtils.isEmpty(sysRole.getName())) {
            throw new ParameterValidateException(BusinessCode.ROLE_NAME_EMPTY_ERROR);
        }
        if (StringUtils.isEmpty(sysRole.getRoleKey())) {
            throw new ParameterValidateException(BusinessCode.ROLE_KEY_EMPTY_ERROR);
        }
    }

    public static void updateValidate(SysRole sysRole) {
        if (StringUtils.isEmpty(sysRole.getId())) {
            throw new BusinessException(BusinessCode.ID_EMPTY_ERROR);
        }
        insertValidate(sysRole);
    }
}
