package cn.tedu.service;

import java.util.List;

import cn.tedu.pojo.Class;

/**
 * 
 * @author HaiDi
 * @version 上午9:31:02
 * @email 2449365663@qq.com
 */
public interface ClassService {
	/**
	 * 根据班级Id查询班级信息
	 * 
	 * @param classId
	 * @return
	 */
	public Class findById(String classId);

	/**
	 * 查询所有的班级信息
	 * 
	 * @return
	 */
	public List<Class> findAll();

	/**
	 * 根据班级id修改班级信息
	 * 
	 * @param classId 班级id
	 */
	public void updateById(String id, String headmasterId,
                           String headmasterName, String classSize, String liberalOrSciences);

	/**
	 * 插入新的班级信息
	 * 
	 * @param class_
	 */
	public void addClass(Class class_);

	/**
	 * 根据班级id删除班级信息
	 * 
	 * @param classId
	 */
	public void deleteById(String classId);
}
