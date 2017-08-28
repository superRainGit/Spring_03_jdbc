package cn.zhang.spring;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import cn.zhang.beans.Employee;

public class JdbcTest {

	private static final Logger logger = LogManager.getLogger(JdbcTest.class.getName());
	
	private JdbcTemplate jdbcTemplate;
	private ApplicationContext ctx;

	@Before
	public void init() {
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
	}
	
	/**
	 * 测试查询一个List集合
	 */
	@Test
	public void testQueryList() {
		String sql = "SELECT id,last_name lastName,gender,email FROM employee";
		RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
		List<Employee> emps = jdbcTemplate.query(sql, rowMapper);
		logger.info(emps);
	}
	
	/**
	 * 测试将一条数据库映射为一个POJO
	 */
	@Test
	public void testQuery() {
		String sql = "SELECT id,last_name lastName,gender,email FROM employee WHERE id = ?";
		RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
		Employee emp = jdbcTemplate.queryForObject(sql, rowMapper, 1);
		logger.info(emp);
	}
	
	/**
	 * 测试简单的批量插入
	 */
	@Test
	public void testBatchInsert() {
		String sql = "INSERT INTO employee(last_name,gender,email) VALUES(?,?,?)";
		List<Object[]> batchArgs = new ArrayList<>();
		batchArgs.add(new Object[]{"AA", "1", "Aa@sina.com.cn"});
		batchArgs.add(new Object[]{"BB", "0", "Bb@sina.com.cn"});
		jdbcTemplate.batchUpdate(sql, batchArgs);
	}
	
	/**
	 * 测试简单的更新UPDATE INSERT DELETE
	 */
	@Test
	public void testUpdate() {
		String sql = "UPDATE employee SET last_name = ? WHERE id = ?";
		jdbcTemplate.update(sql, "Jack02", 4);
	}
	
	@Test
	public void testDataSource() throws SQLException {
		DataSource dataSource = (DataSource) ctx.getBean("dataSource");
		logger.info(dataSource.getConnection());
	}
	
}
