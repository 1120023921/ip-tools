package com.cintsoft.ace.business.provider.system.validator;

import com.cintsoft.ace.business.provider.system.constant.SysBusinessCode;
import com.cintsoft.common.exception.ParameterValidateException;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author 胡昊
 * Description: 基础参数校验器
 * Date: 2020/7/24
 * Time: 13:31
 * Mail: huhao9277@gmail.com
 */
public class BaseValidator {

    public static void deleteValidate(List<String> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            throw new ParameterValidateException(SysBusinessCode.ID_LIST_EMPTY_ERROR.getBusinessCode());
        }
    }
}
