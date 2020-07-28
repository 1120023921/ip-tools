package com.cintsoft.system.vo;

import com.cintsoft.system.model.SysMenu;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collections;
import java.util.List;

/**
 * @author 胡昊
 * Description: 菜单视图
 * Date: 2020/7/28
 * Time: 10:36
 * Mail: huhao9277@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysMenuView extends SysMenu {

    //子菜单列表
    private List<SysMenuView> children = Collections.emptyList();
}
