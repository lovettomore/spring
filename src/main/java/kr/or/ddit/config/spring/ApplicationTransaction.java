package kr.or.ddit.config.spring;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Configuration
public class ApplicationTransaction {

	//다른파일에 있는 datasource 주입
	@Resource(name = "datasource")
	private DataSource datasource;
	
	@Bean
	public DataSourceTransactionManager transactionManager() {
		
		DataSourceTransactionManager dstm = new DataSourceTransactionManager();
		dstm.setDataSource(datasource); //이녀석이 다른파일에 있는 datasource 메서드에요
		return null;
	}
}
