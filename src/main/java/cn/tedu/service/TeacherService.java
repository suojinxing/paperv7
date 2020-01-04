package cn.tedu.service;

import java.util.List;

import cn.tedu.pojo.Teacher;

/**
 * 
 * @author HaiDi
 * @version 上午9:31:05
 * @email 2449365663@qq.com
 */
public interface TeacherService {
	/**
	 * 查询所有教师的信息
	 * 
	 * @return 所有教师封装成的集合
	 */
	public List<Teacher> findAll();

	/**
	 * 根据教师id查询教师的信息,教师id就是用户名
	 * 
	 * @param teacherId 教师id
	 * @return 返回教师的信息
	 */
	public Teacher findByTeacherId(String teacherId);

	public void updateTeacherInfo(String teacherName, String teacherId,
                                  String teacherAge, String teacherSalary, String teacherGender,
                                  String teacherTeaching);

	public void deleteTeacher(String teacherId);
}
