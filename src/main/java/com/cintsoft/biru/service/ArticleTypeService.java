package com.cintsoft.biru.service;

import com.cintsoft.biru.model.ArticleType;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cintsoft.common.vo.ResultBean;

import java.util.List;

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
     * @param articleType 文章类型信息
     * @description 插入文章分类信息
     * @author 吴高耀
     * @email 709581924@qq.com
     * @date 2020/9/3 15:27
     */
    ResultBean<Boolean> saveArticleType(ArticleType articleType);

    /**
     * @param articleType 文章类型信息
     * @description 更新文章分类信息
     * @author 吴高耀
     * @email 709581924@qq.com
     * @date 2020/9/3 15:27
     */
    ResultBean<Boolean> updateArticleType(ArticleType articleType);

    /**
     * @param idList 待删除的文章类型集合
     * @description 批量或单个删除文章类型
     * @author 吴高耀
     * @email 709581924@qq.com
     * @date 2020/9/3 16:58
     */
    ResultBean<Boolean> removeArticleTypes(List<String> idList);
}
