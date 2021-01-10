package com.cintsoft.ace.business.provider.config.mybaits;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 胡昊
 * Description: Mybatis配置
 * Date: 2020/7/23
 * Time: 10:57
 * Mail: huhao9277@gmail.com
 */
@Configuration
@MapperScan("com.cintsoft.**.dao")
public class MyMybatisPlusConfig {

    /**
     * @param aceTenantConfigProperties 租户配置信息
     * @description 租户默认配置
     * @author 胡昊
     * @email huhao9277@gmail.com
     * @date 2021/1/10 11:42
     */
    @Bean
    @ConditionalOnBean(AceTenantConfigProperties.class)
    @ConditionalOnMissingBean
    public TenantLineHandler tenantLineHandler(@Autowired AceTenantConfigProperties aceTenantConfigProperties) {
        return new AceTenantLineHandler(aceTenantConfigProperties);
    }

    /**
     * @description 自动填充默认配置
     * @author 胡昊
     * @email huhao9277@gmail.com
     * @date 2021/1/10 11:41
     */
    @Bean
    @ConditionalOnMissingBean(MetaObjectHandler.class)
    public AceMetaObjectHandler aceMetaObjectHandler() {
        return new AceMetaObjectHandler();
    }

    /**
     * @param tenantLineHandler 租户处理器
     * @description Mybaits插件配置
     * @author 胡昊
     * @email huhao9277@gmail.com
     * @date 2021/1/10 11:42
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(@Autowired(required = false) TenantLineHandler tenantLineHandler) {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        if (tenantLineHandler != null) {
            interceptor.addInnerInterceptor(new TenantLineInnerInterceptor(tenantLineHandler));
        }
        return interceptor;
    }
}
