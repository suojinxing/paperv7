package cn.tedu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.tedu.pojo.Class;

/**
 * 
 * @author HaiDi
 * @version 上午9:28:56
 * @email 2449365663@qq.com
 */
@Mapper
public interface ClassMapper {
	/**
	 * 根据班级Id查询班级信息
	 * @param classId
	 * @return
	 */
	public Class selectById(String classId);
	
	/**
	 * 查询所有班级的信息
	 * @return
	 */
	public List<Class> selectAll();
	
	/**
	 * 根据班级id修改班级信息
	 * @param class_ 班级信息
	 */
	public void updateById(Class class_);
	
	/**
	 * 插入新的班级信息
	 * @param class_
	 */
	public void insertClass(Class class_);
	
	/**
	 * 根据班级id删除班级信息
	 * @param classId
	 */
	public void deleteById(String classId);
}
