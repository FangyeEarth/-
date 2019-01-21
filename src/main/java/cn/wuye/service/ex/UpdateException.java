package cn.wuye.service.ex;

import cn.wuye.service.ex.ServiceException;

public class UpdateException extends ServiceException {

	private static final long serialVersionUID = 8355788318022365338L;

	public UpdateException() {
		super();
	}

	public UpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UpdateException(String message, Throwable cause) {
		super(message, cause);
	}

	public UpdateException(String message) {
		super(message);
	}

	public UpdateException(Throwable cause) {
		super(cause);
	}
	
	
	

}
