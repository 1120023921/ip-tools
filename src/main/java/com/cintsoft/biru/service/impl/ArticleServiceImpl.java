package com.cintsoft.biru.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.cintsoft.biru.model.Article;
import com.cintsoft.biru.dao.ArticleMapper;
import com.cintsoft.biru.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cintsoft.common.enums.ErrorCodeInfo;
import com.cintsoft.common.exception.BusinessException;
import com.cintsoft.common.vo.ResultBean;
import com.cintsoft.system.model.SysResource;
import com.cintsoft.system.model.SysUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

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


    @Override
    public ResultBean<Boolean> saveArticle(Article article) {
        //未完成 准备封装成Utils
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (ObjectUtil.isNull(authentication)) {
            throw new BusinessException(10000, "请先登录");
        }
        Object principal = authentication.getPrincipal();
        SysUser sysUser = new SysUser();
        if (principal instanceof SysUser) {
            sysUser = (SysUser) principal;
        }
        article.setUserId(sysUser.getId());
        //资源名称未定
        List<SysResource> sysResourceList = sysUser.getSysResourceList();
        article.setOrganizationId(null);
        return null;
    }

    @Override
    public ResultBean<Boolean> removeArticles(List<String> idList) {
        boolean remove = removeByIds(idList);
        if (remove) {
            return ResultBean.restResult(true, ErrorCodeInfo.OK);
        } else {
            return ResultBean.restResult(false, ErrorCodeInfo.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResultBean<Boolean> updateArticle(Article article) {
        //未完善：判断登录人和发布人是不是一样
        Article newArticle = new Article();
        BeanUtil.copyProperties(article, newArticle);
        newArticle.setUpdateBy(null);
        newArticle.setUpdateTime(null);
        boolean update = updateById(article);
        if (update) {
            return ResultBean.restResult(true, ErrorCodeInfo.OK);
        } else {
            return ResultBean.restResult(false, ErrorCodeInfo.INTERNAL_SERVER_ERROR);
        }
    }
}
