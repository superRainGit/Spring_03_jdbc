package cn.zhang.spring;

import java.util.Arrays;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.zhang.tx.BookShopService;
import cn.zhang.tx.BookshopDao;
import cn.zhang.tx.Cashier;

public class TransactionTest {
	
	private static final Logger logger = LogManager.getLogger(JdbcTest.class.getName());

	private ApplicationContext ctx;

	@Before
	public void init() {
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	/**
	 * 测试事务的传播行为
	 */
	@Test
	public void testTransactionPropagation() {
		Cashier cashier = ctx.getBean(Cashier.class);
		cashier.checkout(Arrays.asList("0001", "0002"), "Tom");
	}
	
	@Test
	public void testBookShopService() {
		BookShopService bookShopService = ctx.getBean(BookShopService.class);
		bookShopService.buy("Tom", "0001");
	}
	
	@Test
	public void test() {
		BookshopDao bookshopDao = ctx.getBean(BookshopDao.class);
		logger.info(bookshopDao.findPriceByIsbn("0001"));
	}
}
