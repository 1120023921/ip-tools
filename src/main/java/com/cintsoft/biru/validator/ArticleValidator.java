package com.cintsoft.biru.validator;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.cintsoft.biru.model.Article;
import com.cintsoft.common.exception.BusinessException;

import java.util.List;

/**
 * @Author WGY
 * @Date 2020/9/3
 * @Time 16:17
 * To change this template use File | Settings | File Templates.
 **/
public class ArticleValidator {
    public static void insert(Article article) {
        if (StringUtils.isBlank(article.getTypeId())) {
            throw new BusinessException(10000, "文章分类ID不能为空");
        }
        if (StringUtils.isBlank(article.getTitleZhongwen()) || StringUtils.isBlank(article.getContentZhongwen())) {
            throw new BusinessException(10000, "文章中文标题或内容不能为空");
        }
        if (StringUtils.isBlank(article.getContentZangwen()) || StringUtils.isBlank(article.getContentZangwen())) {
            throw new BusinessException(10000, "文章藏文标题或内容不能为空");
        }
    }

    public static void remove(List<String> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            throw new BusinessException(10000, "请选择需要删除的文章");
        }
    }

    public static void update(Article article) {
        if (StringUtils.isBlank(article.getId())) {
            throw new BusinessException(10000, "主键不能为空");
        }
        if (ObjectUtil.isNull(article.getVersion())) {
            throw new BusinessException(10000, "version不可为空");
        }
        insert(article);
    }
}
