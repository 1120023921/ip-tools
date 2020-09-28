package com.cintsoft.security;

import com.cintsoft.common.utils.jwt.JwtTokenUtil;
import com.cintsoft.security.filter.JwtLoginFilter;
import com.cintsoft.security.filter.JwtVerifyFilter;
import com.cintsoft.security.handler.MyAccessDeniedHandler;
import com.cintsoft.security.handler.MyAuthenticationFailureHandler;
import com.cintsoft.security.provider.MyDaoAuthenticationProvider;
import com.cintsoft.security.provider.MyInMemoryAuthenticationProvider;
import com.cintsoft.system.model.SysUser;
import com.cintsoft.system.service.SysUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @author 胡昊
 * Description:
 * Date: 2020/7/23
 * Time: 15:25
 * Mail: huhao9277@gmail.com
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private SysUserService sysUserService;
    @Resource
    private JwtTokenUtil<SysUser> jwtTokenUtil;

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .cors()
                .and()
                .authorizeRequests()
                .antMatchers("/login")
                .permitAll()
                .anyRequest()
                .permitAll()
                .and()
                .addFilterBefore(new JwtLoginFilter(authenticationManager(), jwtTokenUtil), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtVerifyFilter(jwtTokenUtil), BasicAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(new MyAuthenticationFailureHandler())
                .accessDeniedHandler(new MyAccessDeniedHandler())
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(Arrays.asList(new MyDaoAuthenticationProvider(sysUserService, bCryptPasswordEncoder()), new MyInMemoryAuthenticationProvider(bCryptPasswordEncoder())));
    }
}
