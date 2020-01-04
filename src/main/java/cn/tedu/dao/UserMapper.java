package cn.tedu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.tedu.pojo.User;

/**
 * 
 * @author HaiDi
 * @version 下午7:22:07
 * @email 2449365663@qq.com
 */
@Mapper
public interface UserMapper {
	/**
	 * 获取所有用户的信息
	 * 
	 * @return 所有用户的信息
	 */
	public List<User> findPageObjects(@Param("username") String username,
                                      @Param("pageCurrent") Integer pageCurrent,
                                      @Param("startIndex") Integer startIndex,
                                      @Param("pageSize") Integer pageSize);

	/**
	 * 根据用户名查询用户信息
	 * 
	 * @param username
	 * @return
	 */
	public User selectByUsername(String username);

	/**
	 * 根据用户名和密码获取用户信息
	 * 
	 * @param user 用户信息
	 * @return
	 */
	public User selectByUsernameAndPassword(User user);

	/**
	 * 根据用户名修改密码
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public int updatePassword(@Param("username") String username,
                              @Param("password") String password);

	/**
	 * 根据用户名删除用户信息
	 * 
	 * @param username
	 */
	public void deleteByUsername(String username);

	/**
	 * 插入用户信息
	 * 
	 * @param user
	 */
	public void insertUser(User user);

	public void updateUser(User user);

	/**
	 * 查取页面中的总记录数
	 * 
	 * @return
	 */
	public int getRowCount();

}
