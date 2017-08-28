package cn.zhang.xml;

import java.util.List;

public class CashierImpl implements Cashier {

	private BookShopService bookShopService;
	
	public void setBookShopService(BookShopService bookShopService) {
		this.bookShopService = bookShopService;
	}
	
	@Override
	public void checkout(List<String> isbns, String username) {
		for(String isbn : isbns) {
			bookShopService.buy(username, isbn);
		}
	}

}
