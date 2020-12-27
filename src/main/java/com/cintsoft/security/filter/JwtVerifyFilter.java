package com.cintsoft.security.filter;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cintsoft.common.enums.ErrorCodeInfo;
import com.cintsoft.common.utils.jwt.JwtTokenUtil;
import com.cintsoft.common.vo.ResultBean;
import com.cintsoft.system.model.SysResource;
import com.cintsoft.system.model.SysRole;
import com.cintsoft.system.model.SysRoleResource;
import com.cintsoft.system.model.SysUser;
import com.cintsoft.system.service.SysResourceService;
import com.cintsoft.system.service.SysRoleResourceService;
import com.cintsoft.system.service.SysRoleUserService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.CollectionUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 胡昊
 * Description:
 * Date: 2020/7/22
 * Time: 10:39
 * Mail: huhao9277@gmail.com
 */
public class JwtVerifyFilter extends OncePerRequestFilter {

    private final JwtTokenUtil<SysUser> jwtTokenUtil;
    private final SysRoleUserService sysRoleUserService;
    private final SysRoleResourceService sysRoleResourceService;
    private final SysResourceService sysResourceService;

    public JwtVerifyFilter(JwtTokenUtil<SysUser> jwtTokenUtil, SysRoleUserService sysRoleUserService, SysRoleResourceService sysRoleResourceService, SysResourceService sysResourceService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.sysRoleUserService = sysRoleUserService;
        this.sysRoleResourceService = sysRoleResourceService;
        this.sysResourceService = sysResourceService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        final String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer")) {
            try {
                //从token转用户
                final SysUser sysUser = jwtTokenUtil.getPayloadFromToken(header.split(" ")[1], SysUser.class);
                final List<SysRole> sysRoleList = sysRoleUserService.listUserRole(sysUser.getId());
                if (!CollectionUtils.isEmpty(sysRoleList)) {
                    final List<SysRoleResource> sysRoleResourceList = sysRoleResourceService.list(Wrappers.<SysRoleResource>lambdaQuery()
                            .in(SysRoleResource::getRoleId, sysRoleList.stream()
                                    .map(SysRole::getId)
                                    .collect(Collectors.toList())));
                    if (!CollectionUtils.isEmpty(sysRoleResourceList)) {
                        final List<SysResource> sysResourceList = sysResourceService.listByIds(sysRoleResourceList.stream().map(SysRoleResource::getResourceId).collect(Collectors.toList()));
                        sysUser.setSysResourceList(sysResourceList);
                    }
                }
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(sysUser, null, sysUser.getAuthorities()));
            } catch (ExpiredJwtException e) {
                //token过期
                response.setCharacterEncoding("UTF-8");
                response.setHeader("Content-Type", "application/json");
                final PrintWriter out = response.getWriter();
                out.write(JSONUtil.toJsonPrettyStr(ResultBean.restResult("未登录", ErrorCodeInfo.UNAUTHORIZED)));
                out.flush();
                out.close();
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
