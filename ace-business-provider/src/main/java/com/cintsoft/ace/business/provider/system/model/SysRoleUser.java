package com.cintsoft.ace.business.provider.system.model;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 角色-用户关联
 * </p>
 *
 * @author 胡昊
 * @since 2020-07-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysRoleUser对象", description="角色-用户关联")
public class SysRoleUser implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "角色id")
    private String roleId;

    @ApiModelProperty(value = "用户id")
    private String userId;

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

    @ApiModelProperty(value = "修改者")
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
}
