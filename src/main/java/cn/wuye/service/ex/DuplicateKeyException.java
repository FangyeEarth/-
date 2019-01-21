package cn.wuye.service.ex;

public class DuplicateKeyException extends ServiceException{
	private static final long serialVersionUID = 1358115491592703126L;

	public DuplicateKeyException() {
		super();
	}

	public DuplicateKeyException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DuplicateKeyException(String message, Throwable cause) {
		super(message, cause);
	}

	public DuplicateKeyException(String message) {
		super(message);
	}

	public DuplicateKeyException(Throwable cause) {
		super(cause);
	}
	
	
	
}
