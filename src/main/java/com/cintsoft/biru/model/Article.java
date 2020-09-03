package com.cintsoft.biru.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 文章信息
 * </p>
 *
 * @author 胡昊
 * @since 2020-09-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("info_article")
@ApiModel(value="Article对象", description="文章信息")
public class Article implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "标题-中文")
    private String titleZhongwen;

    @ApiModelProperty(value = "标题-藏文")
    private String titleZangwen;

    @ApiModelProperty(value = "分类id")
    private String typeId;

    @ApiModelProperty(value = "内容-中文")
    private String contentZhongwen;

    @ApiModelProperty(value = "内容-藏文")
    private String contentZangwen;

    @ApiModelProperty(value = "发布人id")
    private String userId;

    @ApiModelProperty(value = "发布者组织id（以发布者发布该文章时所在组织为准）")
    private String organizationId;

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


}
