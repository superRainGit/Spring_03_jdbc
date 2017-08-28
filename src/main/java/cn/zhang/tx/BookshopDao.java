package cn.zhang.tx;

public interface BookshopDao {

	public int findPriceByIsbn(String isbn);
	
	public void updateBookStock(String isbn);
	
	public void updateUserBalance(String username, int price);
}
