package wbh.wilfred.ivege.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.mongodb.Mongo;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandler;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoFactoryBean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import wbh.wilfred.ivege.persistence.typehandler.DateTimeTypeHandler;
import wbh.wilfred.ivege.persistence.typehandler.OrderStatusTypeHandler;
import wbh.wilfred.ivege.persistence.typehandler.ProductUnitTypeHandler;
import wbh.wilfred.ivege.persistence.typehandler.RmbTypeHandler;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@MapperScan("wbh.wilfred.ivege.data.mybatis.mapper")
@ComponentScan("wbh.wilfred.ivege.data")
public class DataConfig {
    @Bean
    public MongoOperations mongoTemplate(Mongo mongo) {
        return new MongoTemplate(mongo, "test");
    }

    @Bean
    public MongoFactoryBean mongo() {
        MongoFactoryBean mongo = new MongoFactoryBean();
        mongo.setHost("localhost");
        return mongo;
    }

    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUsername("root");
        dataSource.setPassword("wangbinghao");
        dataSource.setMaxActive(20);
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
                new ProductUnitTypeHandler()
        };
        sqlSessionFactory.setTypeHandlers(typeHandlers);
        return sqlSessionFactory.getObject();
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }


}
