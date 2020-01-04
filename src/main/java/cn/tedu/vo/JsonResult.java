package cn.tedu.vo;

import java.io.Serializable;

/**
 * 
 * @author HaiDi
 * @version 下午7:30:27
 * @email 2449365663@qq.com
 */
public class JsonResult implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer state = 1; // 默认ok
	private String message = ""; // 消息
	private Object data;

	public JsonResult() {
	}

	public JsonResult(String message) {
		this.message = message;
	}

	/** 一般查询时调用，封装查询结果 */
	public JsonResult(Object data) {
		this.data = data;
	}

	/** 出现异常时时调用 */
	public JsonResult(Throwable t) {
		this.state = 0;
		this.message = t.getMessage();
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "JsonResult [status=" + state + ", message=" + message
				+ ", data=" + data + "]";
	}

}
