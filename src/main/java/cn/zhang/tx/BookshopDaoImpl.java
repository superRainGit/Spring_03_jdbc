package cn.zhang.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.zhang.tx.exception.BookStockNotEnoughException;
import cn.zhang.tx.exception.UserAccountNotEnoughException;

@Repository("bookshopDao")
public class BookshopDaoImpl implements BookshopDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int findPriceByIsbn(String isbn) {
		String sql = "SELECT price FROM book WHERE isbn = ?";
		Integer result = jdbcTemplate.queryForObject(sql, int.class, isbn);
		return result;
	}

	@Override
	public void updateBookStock(String isbn) {
		String sql = "SELECT stock FROM book_stock WHERE isbn = ?";
		int stock = jdbcTemplate.queryForObject(sql, int.class, isbn);
		if(stock <= 0) {
			throw new BookStockNotEnoughException("no enough book stock");
		}
		sql = "UPDATE book_stock SET stock = stock - 1 WHERE isbn = ?";
		jdbcTemplate.update(sql, isbn);
	}

	@Override
	public void updateUserBalance(String username, int price) {
		String sql = "SELECT balance FROM account WHERE username = ?";
		int balance = jdbcTemplate.queryForObject(sql, int.class, username);
		if(balance < price) {
			throw new UserAccountNotEnoughException("no enough user balance");
		}
		sql = "UPDATE account SET balance = balance - ? WHERE username = ?";
		jdbcTemplate.update(sql, price, username);
	}

}
