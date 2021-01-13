package com.cintsoft.ace.business.provider.system.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 客户端信息表
 * </p>
 *
 * @author 胡昊
 * @since 2021-01-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysOauthClientDetails对象", description="客户端信息表")
public class SysOauthClientDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "客户端ID")
    private String clientId;

    @ApiModelProperty(value = "资源列表")
    private String resourceIds;

    @ApiModelProperty(value = "客户端secret")
    private String clientSecret;

    @ApiModelProperty(value = "域")
    private String scope;

    @ApiModelProperty(value = "授权类型")
    private String authorizedGrantTypes;

    @ApiModelProperty(value = "回调地址")
    private String webServerRedirectUri;

    @ApiModelProperty(value = "授权")
    private String authorities;

    @ApiModelProperty(value = "token失效时间")
    private Integer accessTokenValidity;

    @ApiModelProperty(value = "refresh_token失效时间")
    private Integer refreshTokenValidity;

    @ApiModelProperty(value = "附加信息")
    private String additionalInformation;

    @ApiModelProperty(value = "自动接受")
    private String autoapprove;

    @ApiModelProperty(value = "客户端名称")
    private String clientName;

    @ApiModelProperty(value = "前缀")
    private String prefix;

    @ApiModelProperty(value = "创建时间")
    private Long createTime;

    @ApiModelProperty(value = "更新时间")
    private Long updateTime;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "更新者")
    private String updateBy;

    @ApiModelProperty(value = "版本")
    @Version
    private Integer version;

    @ApiModelProperty(value = "是否有效 0无效 1有效")
    private Integer deleted;

    @ApiModelProperty(value = "额外信息")
    private String extra;

    @ApiModelProperty(value = "租户id")
    private String tenantId;


}
