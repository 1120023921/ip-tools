package com.cintsoft.system.validator.sys.rolemenu;

import com.cintsoft.common.exception.BusinessCode;
import com.cintsoft.common.exception.ParameterValidateException;
import com.cintsoft.system.validator.BaseValidator;
import com.cintsoft.system.vo.UserRoleResourceVo;
import org.springframework.util.StringUtils;

/**
 * @author 胡昊
 * Description: 角色菜单管理
 * Date: 2020/7/28
 * Time: 11:19
 * Mail: huhao9277@gmail.com
 */
public class SysRoleMenuValidator extends BaseValidator {


    public static void saveRoleMenu(UserRoleResourceVo userRoleResourceVo) {
        if (!StringUtils.hasText(userRoleResourceVo.getRoleId())) {
            throw new ParameterValidateException(BusinessCode.ROLE_ID_EMPTY_ERROR);
        }
        if (userRoleResourceVo.getMenuIdList() == null) {
            throw new ParameterValidateException(BusinessCode.MENU_ID_LIST_NOT_EXIST_ERROR);
        }
    }
}
