package com.cintsoft.ace.business.provider.config.mybaits;

import cn.hutool.core.util.StrUtil;
import com.cintsoft.ace.business.provider.system.model.SysUser;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : wangzy
 * <p> 创建时间: 2019-04-02 </p>
 * <p> 描述: </p>
 */
@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TenantContextHolderFilter extends GenericFilterBean {

    @Override
    @SneakyThrows
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        final SysUser sysUser = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (sysUser != null && !StringUtils.isEmpty(sysUser.getTenantId())) {
            log.debug("当前登录用户租户id=" + sysUser.getTenantId());
            TenantContextHolder.setTenantId(sysUser.getTenantId());
        } else {
            final String tenantId = request.getHeader("TENANT_ID");
            log.debug("当前header中的租户id=" + tenantId);

            if (StrUtil.isNotBlank(tenantId)) {
                TenantContextHolder.setTenantId(tenantId);
            } else {
                TenantContextHolder.setTenantId("0");
            }
        }
        filterChain.doFilter(request, response);
        TenantContextHolder.clear();
    }
}
