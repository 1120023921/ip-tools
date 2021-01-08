package com.cintsoft.ace.business.provider.business.model;

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
 * 菜单信息
 * </p>
 *
 * @author 胡昊
 * @since 2021-01-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="VisitSysMenu对象", description="菜单信息")
public class VisitSysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "所属模块 M_PHONE:手机 M_PC:PC")
    private String module;

    @ApiModelProperty(value = "菜单类型 M_TYPE_MENU:菜单 M_TYPE_BTN:按钮")
    private String menuType;

    @ApiModelProperty(value = "菜单名称（组件用）")
    private String name;

    @ApiModelProperty(value = "资源标记")
    private String resourceKey;

    @ApiModelProperty(value = "类型 1 子组件，2 外链，3 iframe，4 父组件 其中一个值 路由类型 ")
    private Integer type;

    @ApiModelProperty(value = "路径")
    private String path;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "组件路径")
    private String component;

    @ApiModelProperty(value = "url，用户iframe和外链")
    private String url;

    @ApiModelProperty(value = "显示名称")
    private String disName;

    @ApiModelProperty(value = "父菜单id")
    private String parentId;

    @ApiModelProperty(value = "权重")
    private Integer weight;

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

    @ApiModelProperty(value = "是否有效 0-未删除 1-已删除")
    private Integer deleted;

    @ApiModelProperty(value = "额外信息")
    private String extra;

    @ApiModelProperty(value = "租户id")
    private String tenantId;


}
