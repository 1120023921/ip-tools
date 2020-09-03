package com.cintsoft.biru.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cintsoft.biru.model.ArticleType;
import com.cintsoft.biru.service.ArticleTypeService;
import com.cintsoft.biru.validator.ArticleTypeValidator;
import com.cintsoft.common.enums.ErrorCodeInfo;
import com.cintsoft.common.vo.ResultBean;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 文章分类信息 前端控制器
 * </p>
 *
 * @author 胡昊
 * @since 2020-09-03
 */
@RestController
@RequestMapping("/article-type")
public class ArticleTypeController {

    @Resource
    private ArticleTypeService articleTypeService;

    /**
     * 吴高耀
     * 后台插入文章类型信息
     *
     * @param articleType
     * @return
     */
    @PostMapping(value = "/insert")
    public ResultBean<Boolean> insert(@RequestBody ArticleType articleType) {
        ArticleTypeValidator.insert(articleType);
        return articleTypeService.saveArticleType(articleType);
    }

    /**
     * 吴高耀
     * 后台更新文章类型信息
     *
     * @param articleType
     * @return
     */
    @PatchMapping(value = "/update")
    public ResultBean<Boolean> update(@RequestBody ArticleType articleType) {
        ArticleTypeValidator.update(articleType);
        return articleTypeService.updateArticleType(articleType);
    }

    /**
     * 后台查询所有文章类型信息
     * 吴高耀
     *
     * @return
     */
    @GetMapping(value = "/list")
    public ResultBean<List<ArticleType>> list() {
        return ResultBean.restResult(articleTypeService.list(), ErrorCodeInfo.OK);
    }

    /**
     * 后台批量或单个删除文章类型信息
     * 吴高耀
     *
     * @param idList
     * @return
     */
    @DeleteMapping(value = "/remove")
    public ResultBean<Boolean> remove(@RequestBody List<String> idList) {
        ArticleTypeValidator.remove(idList);
        return ResultBean.restResult(articleTypeService.removeByIds(idList), ErrorCodeInfo.OK);
    }

}

