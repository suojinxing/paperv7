package cn.tedu.pojo;

/**
 * 存放用户名账号密码的表
 * @author HaiDi
 * @version 下午7:09:22
 * @email 2449365663@qq.com
 */
public class User {
	private String username;
	private String password;
	private Integer status;

	public User() {
		super();
	}

	public User(String username, String password, Integer status) {
		super();
		this.username = username;
		this.password = password;
		this.status = status;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password
				+ ", status=" + status + "]";
	}

}
