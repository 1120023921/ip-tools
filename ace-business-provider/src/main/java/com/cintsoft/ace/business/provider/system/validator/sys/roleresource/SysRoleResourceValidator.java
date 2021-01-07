package com.cintsoft.ace.business.provider.system.validator.sys.roleresource;

import com.cintsoft.ace.business.provider.common.exception.BusinessCode;
import com.cintsoft.ace.business.provider.common.exception.ParameterValidateException;
import com.cintsoft.ace.business.provider.system.validator.BaseValidator;
import com.cintsoft.ace.business.provider.system.vo.UserRoleResourceVo;
import org.springframework.util.StringUtils;

/**
 * @author 胡昊
 * Description: 角色资源校验器
 * Date: 2020/7/27
 * Time: 9:17
 * Mail: huhao9277@gmail.com
 */
public class SysRoleResourceValidator extends BaseValidator {
    public static void saveRoleResource(UserRoleResourceVo userRoleResourceVo) {
        if (!StringUtils.hasText(userRoleResourceVo.getRoleId())) {
            throw new ParameterValidateException(BusinessCode.ROLE_ID_EMPTY_ERROR);
        }
        if (userRoleResourceVo.getResourceIdList() == null) {
            throw new ParameterValidateException(BusinessCode.RESOURCE_ID_LIST_NOT_EXIST_ERROR);
        }
    }
}
