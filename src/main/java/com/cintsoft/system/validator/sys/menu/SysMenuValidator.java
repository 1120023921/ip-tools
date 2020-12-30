package com.cintsoft.system.validator.sys.menu;

import com.cintsoft.common.exception.BusinessCode;
import com.cintsoft.common.exception.ParameterValidateException;
import com.cintsoft.system.model.SysMenu;
import com.cintsoft.system.service.SysMenuService;
import com.cintsoft.system.validator.BaseValidator;
import org.springframework.util.StringUtils;

/**
 * @author 胡昊
 * Description: 菜单校验器
 * Date: 2020/7/27
 * Time: 17:35
 * Mail: huhao9277@gmail.com
 */
public class SysMenuValidator extends BaseValidator {

    public static void insert(SysMenu sysMenu, SysMenuService sysMenuService) {
        if (!StringUtils.hasText(sysMenu.getName())) {
            throw new ParameterValidateException(BusinessCode.MENU_NAME_EMPTY_ERROR);
        }
        if (sysMenu.getType() == null) {
            throw new ParameterValidateException(BusinessCode.MENU_TYPE_EMPTY_ERROR);
        }
        if (!StringUtils.hasText(sysMenu.getPath())) {
            throw new ParameterValidateException(BusinessCode.MENU_PATH_EMPTY_ERROR);
        }
        if (!StringUtils.hasText(sysMenu.getIcon())) {
            throw new ParameterValidateException(BusinessCode.MENU_ICON_EMPTY_ERROR);
        }
        if (!StringUtils.hasText(sysMenu.getParentId())) {
            throw new ParameterValidateException(BusinessCode.PARENT_ID_EMPTY_ERROR);
        }
        if (!StringUtils.hasText(sysMenu.getDisName())) {
            throw new ParameterValidateException(BusinessCode.DIS_NAME_EMPTY_ERROR);
        }
        if (!"0".equals(sysMenu.getParentId()) && sysMenuService.getById(sysMenu.getParentId()) == null) {
            throw new ParameterValidateException(BusinessCode.PARENT_ID_NOT_EXIST_ERROR);
        }
    }

    public static void updateValidate(SysMenu sysMenu, SysMenuService sysMenuService) {
        if (!StringUtils.hasText(sysMenu.getId())) {
            throw new ParameterValidateException(BusinessCode.MENU_ID_EMPTY_ERROR);
        }
        insert(sysMenu, sysMenuService);
    }
}
