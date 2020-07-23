package com.cintsoft.system.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <p>
 * 用户信息
 * </p>
 *
 * @author 胡昊
 * @since 2020-07-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "SysUser对象", description = "用户信息")
public class SysUser implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "性别 0-未知 1-男 2-女")
    private Integer gender;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    private String mail;

    @ApiModelProperty(value = "用户头像")
    private String headerUrl;

    @ApiModelProperty(value = "用户类型 本地用户-LOCAL 授权中心-AUTH_CENTER")
    private String userSource;

    @ApiModelProperty(value = "账户名是否过期 0-未过期 1-已过期")
    private Integer accountNonExpired;

    @ApiModelProperty(value = "账户是否锁定 0-未锁定 1-已锁定")
    private Integer accountNonLocked;

    @ApiModelProperty(value = "凭据是否过期 0-未过期 1-已过期")
    private Integer credentialsNonExpired;

    @ApiModelProperty(value = "状态 0-禁用 1-启用")
    private Integer enabled;

    @ApiModelProperty(value = "权重")
    private Integer weight;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Long createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateTime;

    @ApiModelProperty(value = "创建者")
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "更新者")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "版本")
    @Version
    private Integer version;

    @ApiModelProperty(value = "是否有效 0无效 1有效")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "额外信息")
    private String extra;

    @TableField(exist = false)
    private List<SysResource> sysResourceList = Collections.emptyList();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return sysResourceList;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired.equals(1);
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked.equals(1);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired.equals(1);
    }

    @Override
    public boolean isEnabled() {
        return enabled.equals(1);
    }
}
