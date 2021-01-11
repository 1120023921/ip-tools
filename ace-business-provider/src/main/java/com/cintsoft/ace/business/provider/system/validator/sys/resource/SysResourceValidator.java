package com.cintsoft.ace.business.provider.system.validator.sys.resource;

import com.cintsoft.ace.business.provider.system.constant.SysBusinessCode;
import com.cintsoft.ace.business.provider.system.model.SysResource;
import com.cintsoft.ace.business.provider.system.validator.BaseValidator;
import com.cintsoft.common.exception.ParameterValidateException;
import org.springframework.util.StringUtils;

/**
 * @author 胡昊
 * Description:
 * Date: 2020/7/25
 * Time: 20:21
 * Mail: huhao9277@gmail.com
 */
public class SysResourceValidator extends BaseValidator {


    public static void insertValidate(SysResource sysResource) {
        if (!StringUtils.hasText(sysResource.getName())) {
            throw new ParameterValidateException(SysBusinessCode.RESOURCE_NAME_EMPTY_ERROR.getBusinessCode());
        }
        if (!StringUtils.hasText(sysResource.getResourceKey())) {
            throw new ParameterValidateException(SysBusinessCode.RESOURCE_KEY_EMPTY_ERROR.getBusinessCode());
        }
        if (!StringUtils.hasText(sysResource.getParentId())) {
            throw new ParameterValidateException(SysBusinessCode.PARENT_ID_EMPTY_ERROR.getBusinessCode());
        }
    }

    public static void updateValidate(SysResource sysResource) {
        if (!StringUtils.hasText(sysResource.getId())) {
            throw new ParameterValidateException(SysBusinessCode.ID_EMPTY_ERROR.getBusinessCode());
        }
        insertValidate(sysResource);
    }
}
