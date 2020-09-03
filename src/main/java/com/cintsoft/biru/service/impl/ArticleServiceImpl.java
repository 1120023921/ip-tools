package com.cintsoft.biru.service.impl;

import com.cintsoft.biru.model.Article;
import com.cintsoft.biru.dao.ArticleMapper;
import com.cintsoft.biru.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章信息 服务实现类
 * </p>
 *
 * @author 胡昊
 * @since 2020-09-03
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

}
