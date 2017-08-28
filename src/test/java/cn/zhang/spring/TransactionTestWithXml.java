package cn.zhang.spring;

import java.util.Arrays;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.zhang.xml.BookShopService;
import cn.zhang.xml.Cashier;

public class TransactionTestWithXml {

	private ApplicationContext ctx;
	
	{
		ctx = new ClassPathXmlApplicationContext("bean-tx-xml.xml");
	}
	
	@Test
	public void testCashierService() {
		Cashier cashier = ctx.getBean(Cashier.class);
		cashier.checkout(Arrays.asList("0001", "0002"), "Tom");
	}
	
	@Test
	public void testBookShopDao() {
		BookShopService bookShopService = ctx.getBean(BookShopService.class);
		bookShopService.buy("Tom", "0001");
	}
}
