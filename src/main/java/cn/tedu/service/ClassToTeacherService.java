package cn.tedu.service;

import java.util.List;

import cn.tedu.pojo.ClassToTeacher;

/**
 * 
 * @author HaiDi
 * @version 下午12:40:14
 * @email 2449365663@qq.com
 */
public interface ClassToTeacherService {
	/**
	 * 根据教师id查询教师授课的班级有哪些
	 * @param teacherid
	 * @return
	 */
	public List<ClassToTeacher> findByTeacherId(String teacherId);
}
