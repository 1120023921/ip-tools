package com.cintsoft.biru.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cintsoft.biru.model.Article;
import com.cintsoft.biru.model.ArticleType;
import com.cintsoft.biru.dao.ArticleTypeMapper;
import com.cintsoft.biru.service.ArticleTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cintsoft.common.enums.ErrorCodeInfo;
import com.cintsoft.common.exception.BusinessException;
import com.cintsoft.common.vo.ResultBean;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章分类信息 服务实现类
 * </p>
 *
 * @author 胡昊
 * @since 2020-09-03
 */
@Service
public class ArticleTypeServiceImpl extends ServiceImpl<ArticleTypeMapper, ArticleType> implements ArticleTypeService {

    @Override
    public ResultBean<Boolean> saveArticleType(ArticleType articleType) {
        ArticleType type = new ArticleType();
        type.setTypeZangwen(articleType.getTypeZangwen());
        type.setTypeZhongwen(articleType.getTypeZhongwen());
        type.setVersion(1);
        boolean save = save(type);
        if (save) {
            return ResultBean.restResult(true, ErrorCodeInfo.OK);
        } else {
            return ResultBean.restResult(false, ErrorCodeInfo.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResultBean<Boolean> updateArticleType(ArticleType articleType) {
        ArticleType one = getOne(Wrappers.<ArticleType>lambdaQuery()
                .eq(ArticleType::getTypeZangwen, articleType.getTypeZangwen())
                .eq(ArticleType::getTypeZhongwen, articleType.getTypeZhongwen())
                .ne(ArticleType::getId, articleType.getId()));
        if (ObjectUtil.isNotNull(one)) {
            throw new BusinessException(10000, "当前类型已存在");
        }
        ArticleType type = new ArticleType();
        type.setId(articleType.getId());
        type.setTypeZangwen(articleType.getTypeZangwen());
        type.setTypeZhongwen(articleType.getTypeZhongwen());
        type.setVersion(articleType.getVersion());
        boolean update = updateById(type);
        if (update) {
            return ResultBean.restResult(true, ErrorCodeInfo.OK);
        } else {
            return ResultBean.restResult(false, ErrorCodeInfo.INTERNAL_SERVER_ERROR);
        }
    }
}
