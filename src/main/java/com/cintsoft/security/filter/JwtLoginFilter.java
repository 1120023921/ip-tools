package com.cintsoft.security.filter;

import cn.hutool.json.JSONUtil;
import com.cintsoft.common.enums.ErrorCodeInfo;
import com.cintsoft.common.utils.jwt.JwtTokenUtil;
import com.cintsoft.common.vo.ResultBean;
import com.cintsoft.system.model.SysUser;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
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
        BeanUtils.copyProperties(sysUser, payload);
        payload.setPassword(null);
        payload.setMail(null);
        payload.setPhone(null);
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
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "application/json");
        final PrintWriter out = response.getWriter();
        if (failed instanceof BadCredentialsException || failed instanceof ProviderNotFoundException) {
            out.write(JSONUtil.toJsonPrettyStr(ResultBean.restResult(failed.getMessage(), 500, "用户名密码错误")));
        } else if (failed instanceof LockedException) {
            out.write(JSONUtil.toJsonPrettyStr(ResultBean.restResult(failed.getMessage(), 500, "账户已锁定")));
        } else if (failed instanceof DisabledException) {
            out.write(JSONUtil.toJsonPrettyStr(ResultBean.restResult(failed.getMessage(), 500, "账户已禁用")));
        } else if (failed instanceof AccountExpiredException) {
            out.write(JSONUtil.toJsonPrettyStr(ResultBean.restResult(failed.getMessage(), 500, "用户名已过期")));
        } else if (failed instanceof CredentialsExpiredException) {
            out.write(JSONUtil.toJsonPrettyStr(ResultBean.restResult(failed.getMessage(), 500, "密码已过期")));
        }
        out.flush();
        out.close();
    }
}
