package com.cintsoft.biru.controller;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cintsoft.biru.model.Article;
import com.cintsoft.biru.service.ArticleService;
import com.cintsoft.biru.validator.ArticleValidator;
import com.cintsoft.common.enums.ErrorCodeInfo;
import com.cintsoft.common.vo.ResultBean;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 文章信息 前端控制器
 * </p>
 *
 * @author 胡昊
 * @since 2020-09-03
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    /**
     * @param pageNum  当前页
     * @param pageSize 每页大小
     * @param article  查询的信息
     * @description 前端分页条件查询文章
     * @author 吴高耀
     * @email 709581924@qq.com
     * @date 2020/9/3 15:38
     */
    @GetMapping("/front-page")
    public ResultBean<Page<Article>> frontPage(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                               @RequestParam(value = "pageSize", defaultValue = "2") Integer pageSize,
                                               @RequestBody Article article) {
        return ResultBean.restResult(articleService.page(new Page<Article>(pageNum, pageSize),
                Wrappers.<Article>lambdaQuery()
                        .eq(StringUtils.isNotBlank(article.getTypeId()), Article::getTypeId, article.getTypeId())
                        .like(StringUtils.isNotBlank(article.getTitleZhongwen()), Article::getContentZhongwen, article.getContentZhongwen())), ErrorCodeInfo.OK);
    }

    /**
     * @param article 文章信息
     * @description 后台新增文章
     * @author 吴高耀
     * @email 709581924@qq.com
     * @date 2020/9/3 16:16
     */
    @PostMapping("/insert")
    public ResultBean<Boolean> insert(@RequestBody Article article) {
        ArticleValidator.insert(article);
        return articleService.saveArticle(article);
    }

    /**
     * @param idList 待删除的文章主键集合
     * @description 后台批量或单个删除文章
     * @author 吴高耀
     * @email 709581924@qq.com
     * @date 2020/9/3 17:00
     */
    @DeleteMapping("/remove")
    public ResultBean<Boolean> remove(@RequestBody List<String> idList) {
        ArticleValidator.remove(idList);
        return articleService.removeArticles(idList);
    }

    /**
     * @param article 要修改的文章信息
     * @description 修改文章
     * @author 吴高耀
     * @email 709581924@qq.com
     * @date 2020/9/3 17:21
     */
    @PatchMapping("/update")
    public ResultBean<Boolean> update(@RequestBody Article article) {
        ArticleValidator.update(article);
        return articleService.updateArticle(article);
    }
}

