package com.cintsoft.biru.validator;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.cintsoft.biru.model.ArticleType;
import com.cintsoft.common.exception.BusinessException;

import java.util.List;

/**
 * @Author WGY
 * @Date 2020/9/3
 * @Time 9:48
 * To change this template use File | Settings | File Templates.
 **/
public class ArticleTypeValidator {
    public static void insert(ArticleType articleType) {
        if (StringUtils.isBlank(articleType.getTypeZhongwen())) {
            throw new BusinessException(10000, "中文输入类型不可为空");
        }
        if (StringUtils.isBlank(articleType.getTypeZangwen())) {
            throw new BusinessException(10000, "藏文输入类型不可为空");
        }
    }

    public static void update(ArticleType articleType) {
        if(StringUtils.isBlank(articleType.getId())) {
            throw new BusinessException(10000, "主键不能为空");
        }
        if (StringUtils.isBlank(articleType.getTypeZhongwen())) {
            throw new BusinessException(10000, "中文输入类型不可为空");
        }
        if (StringUtils.isBlank(articleType.getTypeZangwen())) {
            throw new BusinessException(10000, "藏文输入类型不可为空");
        }
        if(ObjectUtil.isNull(articleType.getVersion())) {
            throw new BusinessException(10000, "version不可为空");
        }
    }

    public static void remove(List<String> idList) {
        if(CollectionUtils.isEmpty(idList)) {
            throw new BusinessException(10000, "请选择需要删除的信息");
        }
    }
}
