package pri.zjj.blog.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

@org.springframework.context.annotation.Configuration
@MapperScan(basePackages=masterDataSourceConfig.PACKAGE,sqlSessionFactoryRef=masterDataSourceConfig.SESSION_FACTORY_BEAN_NAME)
public class masterDataSourceConfig {
	
	static final String PACKAGE="pri.zjj.blog.dao";
	static final String MAPPER_LOCATION="classpath:mapper/blog/**/*.xml";
	static final String SESSION_FACTORY_BEAN_NAME="masterSqlSessionFactory";
	
	@Primary
	@Bean(name="masterDataSource",initMethod="init")
	@ConfigurationProperties(prefix="blog.master.datasource")
	public DataSource masterDataSource() {
		return new DruidDataSource();
	}
	
	@Primary
	@Bean(name="masterTransicationManager")
	public DataSourceTransactionManager masterDataSourceTransactionManager(@Qualifier("masterDataSource")DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
	
	@Primary
	@Bean(name="masterSqlSessionFactory")
	public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource")DataSource dataSource) throws Exception {
		Configuration configuration=new Configuration();
		configuration.setJdbcTypeForNull(JdbcType.VARCHAR);
		
		SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setConfiguration(configuration);
		sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(masterDataSourceConfig.MAPPER_LOCATION));
		sqlSessionFactoryBean.setDataSource(dataSource);
		return sqlSessionFactoryBean.getObject();
	}
}
