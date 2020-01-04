package cn.tedu.service;

import java.util.List;

import cn.tedu.pojo.Student;

/**
 * 
 * @author HaiDi
 * @version 下午2:18:50
 * @email 2449365663@qq.com
 */
public interface StudentService {
	/**
	 * 根据班级id获取所有学生的信息
	 * 
	 * @param classId 班级id
	 * @return 返回指定班级内所有学生信息的集合
	 */
	public List<Student> findAllByClassId(String classId);

	public List<Student> findAll();

	public Student findByStuId(String stuId);

	public void addStudent(Student student);

	public void updateStudent(String stuId, String name, String classId,
                              String age, String gender);

	public void deleteStudent(String stuId);
}
