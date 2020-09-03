package com.cintsoft.biru.service;

import com.cintsoft.biru.model.ArticleType;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cintsoft.common.vo.ResultBean;

/**
 * <p>
 * 文章分类信息 服务类
 * </p>
 *
 * @author 胡昊
 * @since 2020-09-03
 */
public interface ArticleTypeService extends IService<ArticleType> {

    /**
     * 吴高耀
     * 插入文章分类信息
     *
     * @param articleType
     * @return
     */
    ResultBean<Boolean> saveArticleType(ArticleType articleType);

    /**
     * 吴高耀
     * 更新文章分类信息
     *
     * @param articleType
     * @return
     */
    ResultBean<Boolean> updateArticleType(ArticleType articleType);
}
