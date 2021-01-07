package com.cintsoft.ace.business.provider.system.validator.sys.roleuser;

import com.cintsoft.ace.business.provider.common.exception.BusinessCode;
import com.cintsoft.ace.business.provider.common.exception.ParameterValidateException;
import com.cintsoft.ace.business.provider.system.validator.BaseValidator;
import com.cintsoft.ace.business.provider.system.vo.UserRoleResourceVo;
import org.springframework.util.StringUtils;

/**
 * @author 胡昊
 * Description: 角色用户参数校验器
 * Date: 2020/7/26
 * Time: 14:52
 * Mail: huhao9277@gmail.com
 */
public class SysRoleUserValidator extends BaseValidator {


    public static void saveUserRoleValidate(UserRoleResourceVo userRoleResourceVo) {
        if (!StringUtils.hasText(userRoleResourceVo.getUserId())) {
            throw new ParameterValidateException(BusinessCode.USER_ID_EMPTY_ERROR);
        }
        if (userRoleResourceVo.getRoleIdList() == null) {
            throw new ParameterValidateException(BusinessCode.ROLE_ID_LIST_NOT_EXIST_ERROR);
        }
    }

    public static void saveRoleUserValidate(UserRoleResourceVo userRoleResourceVo) {
        if (!StringUtils.hasText(userRoleResourceVo.getRoleId())) {
            throw new ParameterValidateException(BusinessCode.ROLE_ID_EMPTY_ERROR);
        }
        if (userRoleResourceVo.getUserIdList() == null) {
            throw new ParameterValidateException(BusinessCode.USER_ID_LIST_NOT_EXIST_ERROR);
        }
    }
}
