package com.cintsoft.ace.business.provider.security.provider;

import com.cintsoft.ace.business.provider.system.model.SysResource;
import com.cintsoft.ace.business.provider.system.model.SysUser;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

/**
 * @author 胡昊
 * Description: 内存认证
 * Date: 2020/7/27
 * Time: 11:33
 * Mail: huhao9277@gmail.com
 */
public class MyInMemoryAuthenticationProvider implements AuthenticationProvider {

    private final PasswordEncoder passwordEncoder;

    public MyInMemoryAuthenticationProvider(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    private final static SysUser innerAdmin;

    static {
        final SysResource sysResource = new SysResource();
        sysResource.setResourceKey("INNER_USER");
        innerAdmin = new SysUser();
        innerAdmin.setUsername("user");
        innerAdmin.setPassword("{bcrypt}$2a$10$cJ9dXBXdHl7DuMQt.pcyAulUiiUqoaNJKmhAFyunybuht4uWlINBG");
        innerAdmin.setIsAccountNonExpired(true);
        innerAdmin.setIsAccountNonLocked(true);
        innerAdmin.setIsCredentialsNonExpired(true);
        innerAdmin.setIsEnabled(true);
        innerAdmin.setSysResourceList(Collections.singletonList(sysResource));
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final String username = (String) authentication.getPrincipal();
        final String password = (String) authentication.getCredentials();
        if (innerAdmin.getUsername().equals(username) && passwordEncoder.matches(password, innerAdmin.getPassword())) {
            return new UsernamePasswordAuthenticationToken(innerAdmin, innerAdmin.getPassword(), innerAdmin.getAuthorities());
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class == authentication;
    }
}
