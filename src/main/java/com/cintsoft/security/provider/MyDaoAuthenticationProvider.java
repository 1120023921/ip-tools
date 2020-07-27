package com.cintsoft.security.provider;

import com.cintsoft.system.model.SysUser;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author 胡昊
 * Description: 数据库认证
 * Date: 2020/7/27
 * Time: 10:43
 * Mail: huhao9277@gmail.com
 */
public class MyDaoAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public MyDaoAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final String username = (String) authentication.getPrincipal();
        final String password = (String) authentication.getCredentials();
        final SysUser sysUser = (SysUser) userDetailsService.loadUserByUsername(username);
        if (sysUser != null) {
            if (!sysUser.isAccountNonLocked()) {
                throw new LockedException("User account is locked");
            }
            if (!sysUser.isEnabled()) {
                throw new DisabledException("User is disabled");
            }
            if (!sysUser.isAccountNonExpired()) {
                throw new AccountExpiredException("User account has expired");
            }
            if (!sysUser.isCredentialsNonExpired()) {
                throw new CredentialsExpiredException("User credentials has expired");
            }
            if (!passwordEncoder.matches(password, sysUser.getPassword())) {
                throw new BadCredentialsException("Bad credentials");
            }
            return new UsernamePasswordAuthenticationToken(sysUser, sysUser.getPassword(), sysUser.getAuthorities());
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class == authentication;
    }

}
