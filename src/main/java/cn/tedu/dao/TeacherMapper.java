package cn.tedu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.tedu.pojo.Teacher;

/**
 * 
 * @author HaiDi
 * @version 上午9:30:48
 * @email 2449365663@qq.com
 */
@Mapper
public interface TeacherMapper {
	/**
	 * 查询所有教师的信息
	 * 
	 * @return 所有教师封装成的集合
	 */
	public List<Teacher> selectAll();

	/**
	 * 根据教师id查询教师的信息,教师id就是用户名
	 * 
	 * @param teacherId 教师id
	 * @return 返回教师的信息
	 */
	public Teacher selectByTeacherId(String teacherId);

	public void updateTeacher(Teacher teacher);

	public void insertTeacher(Teacher teacher);

	public void deleteTeacher(String teacherId);
}
