package com.cintsoft.biru.service;

import com.cintsoft.biru.model.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cintsoft.common.vo.ResultBean;

import java.util.List;

/**
 * <p>
 * 文章信息 服务类
 * </p>
 *
 * @author 胡昊
 * @since 2020-09-03
 */
public interface ArticleService extends IService<Article> {

    /**
     * @param article 文章信息
     * @description 新增文章
     * @author 吴高耀
     * @email 709581924@qq.com
     * @date 2020/9/3 16:32
     */
    ResultBean<Boolean> saveArticle(Article article);

    /**
     * @param idList 待删除的文章主键集合
     * @description 批量或单个删除文章
     * @author 吴高耀
     * @email 709581924@qq.com
     * @date 2020/9/3 16:53
     */
    ResultBean<Boolean> removeArticles(List<String> idList);

    /**
     * @param article 要修改的文章信息
     * @description 修改文章信息
     * @author 吴高耀
     * @email 709581924@qq.com
     * @date 2020/9/3 17:08
     */
    ResultBean<Boolean> updateArticle(Article article);
}
