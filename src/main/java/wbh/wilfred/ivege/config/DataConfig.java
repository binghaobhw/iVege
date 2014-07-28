package wbh.wilfred.ivege.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandler;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import wbh.wilfred.ivege.data.mybatis.typehandler.DateTimeTypeHandler;
import wbh.wilfred.ivege.data.mybatis.typehandler.DiscountTypeHandler;
import wbh.wilfred.ivege.data.mybatis.typehandler.OrderStatusTypeHandler;
import wbh.wilfred.ivege.data.mybatis.typehandler.ProductStatusTypeHandler;
import wbh.wilfred.ivege.data.mybatis.typehandler.PromotionStatusTypeHandler;
import wbh.wilfred.ivege.data.mybatis.typehandler.RmbTypeHandler;
import wbh.wilfred.ivege.data.mybatis.typehandler.UnitTypeHandler;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@MapperScan("wbh.wilfred.ivege.data.mybatis.mapper")
@ComponentScan("wbh.wilfred.ivege.data")
@ImportResource("classpath:/wbh/wilfred/ivege/config/properties-config.xml")
public class DataConfig {
/*    @Bean
    public MongoOperations mongoTemplate(Mongo mongo) {
        return new MongoTemplate(mongo, "test");
    }

    @Bean
    public MongoFactoryBean mongo() {
        MongoFactoryBean mongo = new MongoFactoryBean();
        mongo.setHost("localhost");
        return mongo;
    }*/

    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;
    @Value("${jdbc.maxActive}")
    private int maxActive;
    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setMaxActive(maxActive);
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource());
        sqlSessionFactory.setTypeAliasesPackage("wbh.wilfred.ivege.model");
        TypeHandler[] typeHandlers = {
                new DateTimeTypeHandler(),
                new RmbTypeHandler(),
                new OrderStatusTypeHandler(),
                new UnitTypeHandler(),
                new PromotionStatusTypeHandler(),
                new ProductStatusTypeHandler(),
                new DiscountTypeHandler()
        };
        sqlSessionFactory.setTypeHandlers(typeHandlers);
        return sqlSessionFactory.getObject();
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }


}
