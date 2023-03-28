package com.github.yakami.library.infrastructure.mybatis;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author alan
 * @version 1.0.0
 * @ClassName MybatisPlusConfig.java
 * @Description actable  配置自动装配类
 * @createTime 2021年03月04日 10:58:00
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * mybatis-plus自动填充属性, 如表业务ID，创建时间，更新时间。
     *
     * @return
     */
    @Bean
    public BaseMetaObjectFillHandler metaObjectHandler() {
        return new BaseMetaObjectFillHandler();
    }

    /**
     * 分页插件
     * @return
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor(DbType.MYSQL);
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(paginationInnerInterceptor);
        return interceptor;
    }

}
