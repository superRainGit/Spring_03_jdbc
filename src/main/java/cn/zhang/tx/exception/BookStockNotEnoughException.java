package cn.zhang.tx.exception;

public class BookStockNotEnoughException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public BookStockNotEnoughException() {
	}
	
	public BookStockNotEnoughException(String message) {
		super(message);
	}
}
