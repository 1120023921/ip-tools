package com.cintsoft.system.validator.sys.resource;

import com.cintsoft.common.exception.BusinessCode;
import com.cintsoft.common.exception.ParameterValidateException;
import com.cintsoft.system.model.SysResource;
import com.cintsoft.system.validator.BaseValidator;
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
        if (StringUtils.isEmpty(sysResource.getName())) {
            throw new ParameterValidateException(BusinessCode.RESOURCE_NAME_EMPTY_ERROR);
        }
        if (StringUtils.isEmpty(sysResource.getResourceKey())) {
            throw new ParameterValidateException(BusinessCode.RESOURCE_KEY_EMPTY_ERROR);
        }
        if (StringUtils.isEmpty(sysResource.getParentId())) {
            throw new ParameterValidateException(BusinessCode.PARENT_ID_EMPTY_ERROR);
        }
    }

    public static void updateValidate(SysResource sysResource) {
        if (StringUtils.isEmpty(sysResource.getId())) {
            throw new ParameterValidateException(BusinessCode.ID_EMPTY_ERROR);
        }
        insertValidate(sysResource);
    }
}