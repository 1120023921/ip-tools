package com.cintsoft.security.handler;

import cn.hutool.json.JSONUtil;
import com.cintsoft.common.enums.ErrorCodeInfo;
import com.cintsoft.common.vo.ResultBean;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 胡昊
 * Description:
 * Date: 2020/7/23
 * Time: 18:54
 * Mail: huhao9277@gmail.com
 */
public class MyAuthenticationFailureHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        //未认证返回401
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "application/json");
        final PrintWriter out = response.getWriter();
        if (authException instanceof InsufficientAuthenticationException) {
            out.write(JSONUtil.toJsonPrettyStr(ResultBean.restResult("未登录", ErrorCodeInfo.UNAUTHORIZED)));
        }
        out.flush();
        out.close();
    }
}
