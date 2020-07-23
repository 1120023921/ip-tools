package com.cintsoft.security.filter;

import cn.hutool.json.JSONUtil;
import com.cintsoft.common.enums.ErrorCodeInfo;
import com.cintsoft.common.utils.jwt.JwtTokenUtil;
import com.cintsoft.common.vo.ResultBean;
import com.cintsoft.system.model.SysUser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 胡昊
 * Description: Jwt认证过滤器
 * Date: 2020/7/23
 * Time: 16:10
 * Mail: huhao9277@gmail.com
 */
public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil<SysUser> jwtTokenUtil;

    public JwtLoginFilter(AuthenticationManager authenticationManager, JwtTokenUtil<SysUser> jwtTokenUtil) {
        super(new AntPathRequestMatcher("/login", "POST"));
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        final String username = request.getParameter("username");
        final String password = request.getParameter("password");
        final Authentication authRequest = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(authRequest);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        final SysUser sysUser = (SysUser) authResult.getPrincipal();
        final SysUser payload = new SysUser();
        payload.setId(sysUser.getId());
        payload.setName(sysUser.getName());
        payload.setSysResourceList(sysUser.getSysResourceList());
        final String token = jwtTokenUtil.generateToken(payload);
        final Map<String, Object> result = new HashMap<>();
        result.put("token", token);
        result.put("expire", jwtTokenUtil.getExpiration());
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "application/json");
        final PrintWriter out = response.getWriter();
        out.write(JSONUtil.toJsonPrettyStr(ResultBean.restResult(result, ErrorCodeInfo.OK)));
        out.flush();
        out.close();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "application/json");
        final PrintWriter out = response.getWriter();
        if (failed instanceof BadCredentialsException) {
            out.write(JSONUtil.toJsonPrettyStr(ResultBean.restResult(failed.getMessage(), 500, "用户名密码错误")));
        }
        out.flush();
        out.close();
    }
}
