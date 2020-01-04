package cn.tedu.service;

import cn.tedu.pojo.User;
import cn.tedu.vo.PageObject;

/**
 * 
 * @author HaiDi
 * @version 下午7:24:10
 * @email 2449365663@qq.com
 */
public interface UserService {
	/**
	 * 获取所有的对象
	 * 
	 * @return
	 */
	public PageObject<User> findPageObjects(String username,
                                            Integer pageCurrent);

	/**
	 * 根据用户名和密码去数据库验证,并返回身份
	 * 
	 * @param username
	 * @param password
	 * @return 1=>学生/2=>老师/3=>管理员/4=>账号或密码错误
	 */
	public Integer checkUsernameAndPassword(String username, String password);

	/**
	 * 根据用户名修改密码
	 * 
	 * @param username
	 * @return
	 */
	public int updatePassword(String username, String oldPassword,
                              String newPassword);

	/**
	 * 删除指定用户信息
	 * 
	 * @param username
	 */
	public void deleteByUsername(String username);

	/**
	 * 插入用户信息
	 * 
	 * @param user
	 */
	public void updateUser(String username, String password, String status);

	/**
	 * 根据用户名查询用户信息
	 * 
	 * @param username
	 * @return
	 */
	public User findByUsername(String username);
}
