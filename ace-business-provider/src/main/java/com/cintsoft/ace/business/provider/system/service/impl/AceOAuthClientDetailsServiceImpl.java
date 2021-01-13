package com.cintsoft.ace.business.provider.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cintsoft.ace.business.provider.system.model.*;
import com.cintsoft.ace.business.provider.system.service.*;
import com.cintsoft.mybatis.plus.tenant.TenantContextHolder;
import com.cintsoft.spring.security.model.AceUser;
import com.cintsoft.spring.security.oauth.model.AceOAuthClientDetails;
import com.cintsoft.spring.security.oauth.service.AceOAuthClientDetailsService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author 胡昊
 * Description:
 * Date: 2021/1/13
 * Time: 11:08
 * Mail: huhao9277@gmail.com
 */
@Service
public class AceOAuthClientDetailsServiceImpl implements AceOAuthClientDetailsService {

    @Resource
    private SysOauthClientDetailsService sysOauthClientDetailsService;
    @Resource
    private SysRoleOauthClientService sysRoleOauthClientService;
    @Resource
    private SysRoleResourceService sysRoleResourceService;
    @Resource
    private SysRoleService sysRoleService;
    @Resource
    private SysResourceService sysResourceService;

    @Override
    public AceUser clientCredentialsAuthorize(String clientId, String clientSecret) {
        final SysOauthClientDetails clientDetails = sysOauthClientDetailsService.getOne(Wrappers.<SysOauthClientDetails>lambdaQuery()
                .eq(SysOauthClientDetails::getClientId, clientId)
                .eq(SysOauthClientDetails::getClientSecret, clientSecret));
        if (clientDetails == null) {
            return null;
        }
        final AceUser aceUser = new AceUser();
        aceUser.setTenantId(TenantContextHolder.getTenantId());
        aceUser.setUsername(clientDetails.getClientId());
        aceUser.setName(clientDetails.getClientName());
        final Set<String> roleIdSet = sysRoleOauthClientService.list(Wrappers.<SysRoleOauthClient>lambdaQuery().eq(SysRoleOauthClient::getOauthClientId, clientId))
                .stream()
                .map(SysRoleOauthClient::getRoleId)
                .collect(Collectors.toSet());
        if (!CollectionUtils.isEmpty(roleIdSet)) {
            final List<SysRole> sysRoleList = sysRoleService.listByIds(roleIdSet);
            aceUser.setRoleKeyList(sysRoleList.stream().map(SysRole::getRoleKey).collect(Collectors.toList()));
            final List<SysRoleResource> sysRoleResourceList = sysRoleResourceService.list(Wrappers.<SysRoleResource>lambdaQuery()
                    .in(SysRoleResource::getRoleId, sysRoleList.stream().map(SysRole::getId).collect(Collectors.toSet())));
            if (!CollectionUtils.isEmpty(sysRoleResourceList)) {
                final List<SysResource> sysResourceList = sysResourceService.list(Wrappers.<SysResource>lambdaQuery()
                        .in(SysResource::getId, sysRoleResourceList.stream().map(SysRoleResource::getResourceId).collect(Collectors.toSet())));
                aceUser.setResourceKeyList(sysResourceList.stream().map(SysResource::getResourceKey).collect(Collectors.toList()));
                aceUser.setSysResourceList(sysResourceList);
            }
        }
        return aceUser;
    }

    @Override
    public AceOAuthClientDetails getAceOauthClientDetails(String clientId) {
        final SysOauthClientDetails clientDetails = sysOauthClientDetailsService.getOne(Wrappers.<SysOauthClientDetails>lambdaQuery().eq(SysOauthClientDetails::getClientId, clientId));
        if (clientDetails != null) {
            final AceOAuthClientDetails aceOAuthClientDetails = new AceOAuthClientDetails();
            BeanUtils.copyProperties(clientDetails, aceOAuthClientDetails);
            return aceOAuthClientDetails;
        }
        return null;
    }
}
