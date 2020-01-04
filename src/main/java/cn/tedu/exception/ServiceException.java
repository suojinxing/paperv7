package cn.tedu.exception;

/**
 * 
 * @author HaiDi
 * @version 下午7:27:23
 * @email 2449365663@qq.com
 */
public class ServiceException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}
}
