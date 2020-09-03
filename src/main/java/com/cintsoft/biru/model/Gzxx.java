package com.cintsoft.biru.model;

import java.math.BigDecimal;
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
 * 工资信息
 * </p>
 *
 * @author 胡昊
 * @since 2020-09-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("info_gzxx")
@ApiModel(value="Gzxx对象", description="工资信息")
public class Gzxx implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "身份证号")
    private String sfzh;

    @ApiModelProperty(value = "年度")
    private Integer nd;

    @ApiModelProperty(value = "月份")
    private Integer yf;

    @ApiModelProperty(value = "姓名")
    private String xm;

    @ApiModelProperty(value = "单位名称")
    private String dwmc;

    @ApiModelProperty(value = "内设处（科）室名称")
    private String nscmc;

    @ApiModelProperty(value = "工资类别名称")
    private String gzlbmc;

    @ApiModelProperty(value = "职务工资")
    private BigDecimal zwgz;

    @ApiModelProperty(value = "技术等级工资")
    private BigDecimal jsdjgz;

    @ApiModelProperty(value = "援藏工资")
    private BigDecimal yzgz;

    @ApiModelProperty(value = "级别工资")
    private BigDecimal jbgz;

    @ApiModelProperty(value = "岗位工资")
    private BigDecimal gwgz;

    @ApiModelProperty(value = "薪级工资")
    private BigDecimal xjgz;

    @ApiModelProperty(value = "十三个月奖励工资")
    private BigDecimal ssgyjlgz;

    @ApiModelProperty(value = "高套工资")
    private BigDecimal gtgz;

    @ApiModelProperty(value = "大中专学历固定")
    private BigDecimal dzzxlgd;

    @ApiModelProperty(value = "农林水一线科技人员固定")
    private BigDecimal nlsyxkjrygd;

    @ApiModelProperty(value = "在藏工作20（15）年固定")
    private BigDecimal zzgzgdA;

    @ApiModelProperty(value = "在藏工作40（30）年固定")
    private BigDecimal zzgzgdB;

    @ApiModelProperty(value = "大中专学历浮动")
    private BigDecimal dzzxlfd;

    @ApiModelProperty(value = "农林水一线科技人员浮动")
    private BigDecimal nlsyxkjryfd;

    @ApiModelProperty(value = "五年浮动")
    private BigDecimal wnfd;

    @ApiModelProperty(value = "基本工资小计")
    private BigDecimal jbgzxj;

    @ApiModelProperty(value = "警衔津贴")
    private BigDecimal jxjt;

    @ApiModelProperty(value = "西藏特殊津贴")
    private BigDecimal xztsjt;

    @ApiModelProperty(value = "教、护10％工资")
    private BigDecimal jhgz;

    @ApiModelProperty(value = "工改保留工资")
    private BigDecimal ggblgz;

    @ApiModelProperty(value = "津贴补贴")
    private BigDecimal jtbt;

    @ApiModelProperty(value = "特殊行业保留津贴")
    private BigDecimal tshybljt;

    @ApiModelProperty(value = "工改保留津贴")
    private BigDecimal ggbljt;

    @ApiModelProperty(value = "住房补贴")
    private BigDecimal zfbt;

    @ApiModelProperty(value = "工龄折算")
    private BigDecimal glzs;

    @ApiModelProperty(value = "补发金额")
    private BigDecimal bfje;

    @ApiModelProperty(value = "煤油补贴")
    private BigDecimal mybt;

    @ApiModelProperty(value = "取暖、降温费")
    private BigDecimal qnjwf;

    @ApiModelProperty(value = "其他补贴")
    private BigDecimal qtbt;

    @ApiModelProperty(value = "应发工资")
    private BigDecimal yfgz;

    @ApiModelProperty(value = "住房公积金")
    private BigDecimal zfgjj;

    @ApiModelProperty(value = "养老保险")
    private BigDecimal yanglaobx;

    @ApiModelProperty(value = "医疗保险")
    private BigDecimal yiliaobx;

    @ApiModelProperty(value = "失业保险")
    private BigDecimal sybx;

    @ApiModelProperty(value = "所得税")
    private BigDecimal sds;

    @ApiModelProperty(value = "水费")
    private BigDecimal sf;

    @ApiModelProperty(value = "电费")
    private BigDecimal df;

    @ApiModelProperty(value = "房租费")
    private BigDecimal fzf;

    @ApiModelProperty(value = "有线电视收视费")
    private BigDecimal yxdsssf;

    @ApiModelProperty(value = "扣借支款")
    private BigDecimal kjzk;

    @ApiModelProperty(value = "扣建房贷款")
    private BigDecimal kjfdk;

    @ApiModelProperty(value = "职业年金")
    private BigDecimal zynj;

    @ApiModelProperty(value = "其他扣款")
    private BigDecimal qtkk;

    @ApiModelProperty(value = "扣发工资")
    private BigDecimal kfgz;

    @ApiModelProperty(value = "实发工资")
    private BigDecimal sfgz;

    @ApiModelProperty(value = "单位代扣小计")
    private BigDecimal dwdkxj;

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
