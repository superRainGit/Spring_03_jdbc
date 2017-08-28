package cn.zhang.tx.exception;

public class UserAccountNotEnoughException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserAccountNotEnoughException() {
	}

	public UserAccountNotEnoughException(String message) {
		super(message);
	}
	
}
