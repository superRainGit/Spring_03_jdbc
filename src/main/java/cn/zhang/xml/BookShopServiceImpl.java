package cn.zhang.xml;

public class BookShopServiceImpl implements BookShopService {

	private BookshopDao bookShopDao;
	
	public void setBookShopDao(BookshopDao bookShopDao) {
		this.bookShopDao = bookShopDao;
	}

	@Override
	public void buy(String username, String isbn) {
		int price = bookShopDao.findPriceByIsbn(isbn);
		bookShopDao.updateBookStock(isbn);
		bookShopDao.updateUserBalance(username, price);
	}

}
