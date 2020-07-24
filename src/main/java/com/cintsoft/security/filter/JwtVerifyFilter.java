package com.cintsoft.security.filter;

import cn.hutool.json.JSONUtil;
import com.cintsoft.common.enums.ErrorCodeInfo;
import com.cintsoft.common.utils.jwt.JwtTokenUtil;
import com.cintsoft.common.vo.ResultBean;
import com.cintsoft.system.model.SysUser;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 胡昊
 * Description:
 * Date: 2020/7/22
 * Time: 10:39
 * Mail: huhao9277@gmail.com
 */
public class JwtVerifyFilter extends OncePerRequestFilter {

    private final JwtTokenUtil<SysUser> jwtTokenUtil;

    public JwtVerifyFilter(JwtTokenUtil<SysUser> jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        final String header = request.getHeader("Authorzation");
        if (header != null && header.startsWith("Bearer")) {
            try {
                //从token转用户
                final SysUser sysUser = jwtTokenUtil.getPayloadFromToken(header.split(" ")[1], SysUser.class);
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(sysUser.getUsername(), null, sysUser.getAuthorities()));
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
