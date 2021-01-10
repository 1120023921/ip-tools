package com.cintsoft.ace.business.provider.system.model;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

/**
 * <p>
 * 资源信息
 * </p>
 *
 * @author 胡昊
 * @since 2020-07-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysResource对象", description="资源信息")
public class SysResource implements Serializable, GrantedAuthority {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "资源名称")
    private String name;

    @ApiModelProperty(value = "资源标识符")
    private String resourceKey;

    @ApiModelProperty(value = "父资源id")
    private String parentId;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "路由缓冲 0-关闭 1-开启")
    private Integer keepAlive;

    @ApiModelProperty(value = "路径类型 1-菜单 2-按钮")
    private Integer type;

    @ApiModelProperty(value = "路径")
    private String path;

    @ApiModelProperty(value = "模块 1-移动端 2-PC端")
    private String moudle;

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

    @ApiModelProperty(value = "是否有效 0-未删除 1-已删除")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "额外信息")
    private String extra;

    @ApiModelProperty(value = "租户id")
    private String tenantId;

    @Override
    public String getAuthority() {
        return resourceKey;
    }
}
