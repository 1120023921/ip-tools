package com.cintsoft.system.vo;

import com.cintsoft.system.model.SysResource;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collections;
import java.util.List;

/**
 * @author 胡昊
 * Description: 资源视图
 * Date: 2020/7/25
 * Time: 9:22 下午
 * Mail: huhao9277@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysResourceView extends SysResource {
    //子资源列表
    private List<SysResourceView> children = Collections.emptyList();
}
