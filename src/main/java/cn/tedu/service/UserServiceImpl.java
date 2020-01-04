package cn.tedu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.tedu.constant.UserStatus;
import cn.tedu.dao.UserMapper;
import cn.tedu.exception.ServiceException;
import cn.tedu.pojo.User;
import cn.tedu.util.ParamsUtil;
import cn.tedu.vo.PageObject;

/**
 * 
 * @author HaiDi
 * @version 下午7:26:00
 * @email 2449365663@qq.com
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public PageObject<User> findPageObjects(String username,
			Integer pageCurrent) {
		// 1. 判断参数是否合法
		if (pageCurrent == null || pageCurrent < 1) {
			pageCurrent = 1;
		}
		// 2. 查询结果
		int rows = userMapper.getRowCount();
		if (rows == 0) {
			throw new RuntimeException("没有学生记录");
		}

		int pageSize = 14;
		int startIndex = (pageCurrent - 1) * pageSize;
		List<User> userList = userMapper.findPageObjects(username, pageCurrent,
				startIndex, pageSize);
		// 3. 包装查询到的结果
		PageObject<User> pageObject = new PageObject<>();
		pageObject.setPageCount((rows - 1) / pageSize + 1);
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setRowCount(rows);
		pageObject.setRecords(userList);
		return pageObject;
	}

	@Override
	public Integer checkUsernameAndPassword(String username, String password) {
		if (username == null || password == null || username == ""
				|| password == "") {
			return UserStatus.ERROR;
		}
		User user = new User();
		user.setUsername(username);
		user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
		User u = userMapper.selectByUsernameAndPassword(user);
		if (u != null) {
			Integer status = u.getStatus();
			// 验证成功,获取身份状态
			if (status == UserStatus.STUDENT) {
				// 学生
				return UserStatus.STUDENT;
			} else if (status == UserStatus.TEACHER) {
				// 老师
				return UserStatus.TEACHER;
			} else if (status == UserStatus.ADMIN) {
				// 管理员
				return UserStatus.ADMIN;
			}
		}
		// 验证失败,用户名或密码错误
		return UserStatus.ERROR;
	}

	@Override
	public int updatePassword(String username, String oldPassword,
			String newPassword) {
		// 1. 查询数据库
		Integer status = checkUsernameAndPassword(username, oldPassword);
		if (status != UserStatus.ERROR) {
			// 2. 若原密码符合则修改密码
			// 查询成功,允许修改密码
			status = userMapper.updatePassword(username,
					DigestUtils.md5DigestAsHex(newPassword.getBytes()));
		} else {
			// 用户名或密码错误
			throw new ServiceException("用户名或密码错误");
		}
		return status;
	}

	@Override
	public void deleteByUsername(String username) {
		if (ParamsUtil.regexParamsIsNullOrEmpty(username)) {
			throw new ServiceException("删除的数据不存在");
		}
		userMapper.deleteByUsername(username);
	}

	@Override
	public void updateUser(String username, String password, String status) {
		// 验证参数是否为空
		if (ParamsUtil.regexParamsIsNullOrEmpty(username, password, status)) {
			throw new ServiceException("名字/密码/身份 不可以有空值,请检查后再更新数据");
		}
		// 1. 查询数据库,若用户不存在,则添加用户,若用户存在则修改用户信息
		User user = findByUsername(username);
		String regex = "^([A-Z]|[a-z]|[0-9]){6,20}$";
		if (user == null) {
			// 用户不存在,创建新用户
			// 1. 判断用户名格式
//			String regex = "^([A-Z]|[a-z]|[0-9]){6,20}$";
			boolean matches = username.matches(regex);
			if (matches) {
				// 合法
				// 2. 判断密码格式
				boolean m = password.matches(regex);
				boolean matches2 = m;
				if (matches2) {
					// 合法
					// 3. 用户状态只能为1,2,3
					String statusRegex = "[123]";
					boolean matches3 = status.matches(statusRegex);
					if (matches3) {
						// 合法
						int uStatus = Integer.parseInt(status);
						user = new User();
						user.setUsername(username);
						String md5Pwd = DigestUtils
								.md5DigestAsHex(password.getBytes());
						user.setPassword(md5Pwd);
						user.setStatus(uStatus);
						userMapper.insertUser(user);
					} else {
						throw new ServiceException("身份不合法,身份只能为1,2,3其中的一种");
					}
				} else {
					throw new ServiceException("密码不合法,密码必须为数字字符组合最少6位最多12位");
				}
			} else {
				throw new ServiceException("用户名不合法");
			}
		} else {
			// 用户已存在,修改用户信息
			// 1. 判断密码是否合法
			boolean m = password.matches(regex);
			if (m) {
				// 合法
				// 2. 判断状态合法
				// 3. 用户状态只能为1,2,3
				String statusRegex = "[123]";
				boolean matches3 = status.matches(statusRegex);
				if (matches3) {
					// 合法
					int uStatus = Integer.parseInt(status);
					String md5Pwd = DigestUtils
							.md5DigestAsHex(password.getBytes());
					user.setPassword(md5Pwd);
					user.setStatus(uStatus);
					userMapper.updateUser(user);
				} else {
					throw new ServiceException("身份不合法,身份只能为1,2,3其中的一种");
				}
			} else {
				throw new ServiceException("密码不合法");
			}
		}

	}

	@Override
	public User findByUsername(String username) {
		// 1. 判断用户名是否合法
		User user = userMapper.selectByUsername(username);
		return user;
	}
}
