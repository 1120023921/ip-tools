package com.cintsoft.ace.business.provider.config.mybaits;

import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.NullValue;
import net.sf.jsqlparser.expression.StringValue;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @author 胡昊
 * Description:
 * Date: 2021/1/8
 * Time: 19:59
 * Mail: huhao9277@gmail.com
 */
public class AceTenantLineHandler implements TenantLineHandler {

    @Resource
    private AceTenantConfigProperties aceTenantConfigProperties;

    @Override
    public Expression getTenantId() {
        final String tenantId = TenantContextHolder.getTenantId();

        if (tenantId == null) {
            return new NullValue();
        }
        return new StringValue(tenantId);
    }

    @Override
    public String getTenantIdColumn() {
        return aceTenantConfigProperties.getColumn();
    }

    @Override
    public boolean ignoreTable(String tableName) {
        final String tenantId = TenantContextHolder.getTenantId();
        // 租户中ID 为空，查询全部，不进行过滤
        if (StringUtils.isEmpty(tenantId)) {
            return true;
        }
        return aceTenantConfigProperties.getTables().contains(tableName);
    }
}
