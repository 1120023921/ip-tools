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
     * @description 前端获取所有文章类型信息
     * @author 吴高耀
     * @email 709581924@qq.com
     * @date 2020/9/3 15:24
     */
    @GetMapping("/front-list")
    public ResultBean<List<ArticleType>> frontList() {
        return ResultBean.restResult(articleTypeService.list(), ErrorCodeInfo.OK);
    }

    /**
     * @param articleType 文章类型信息
     * @description 后台插入文章类型信息
     * @author 吴高耀
     * @email 709581924@qq.com
     * @date 2020/9/3 15:26
     */
    @PostMapping("/insert")
    public ResultBean<Boolean> insert(@RequestBody ArticleType articleType) {
        ArticleTypeValidator.insert(articleType);
        return articleTypeService.saveArticleType(articleType);
    }

    /**
     * @param articleType 文章类型信息
     * @description 后台更新文章类型信息
     * @author 吴高耀
     * @email 709581924@qq.com
     * @date 2020/9/3 15:25
     */
    @PatchMapping("/update")
    public ResultBean<Boolean> update(@RequestBody ArticleType articleType) {
        ArticleTypeValidator.update(articleType);
        return articleTypeService.updateArticleType(articleType);
    }

    /**
     * @description 后台获取所有文章类型信息
     * @author 吴高耀
     * @email 709581924@qq.com
     * @date 2020/9/3 15:24
     */
    @GetMapping("/list")
    public ResultBean<List<ArticleType>> list() {
        return ResultBean.restResult(articleTypeService.list(), ErrorCodeInfo.OK);
    }

    /**
     * @param idList 文章类型主键集合
     * @description 后台批量或单个删除文章类型
     * @author 吴高耀
     * @email 709581924@qq.com
     * @date 2020/9/3 15:24
     */
    @DeleteMapping("/remove")
    public ResultBean<Boolean> remove(@RequestBody List<String> idList) {
        ArticleTypeValidator.remove(idList);
        return articleTypeService.removeArticleTypes(idList);
    }

}

