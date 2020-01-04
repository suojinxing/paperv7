package cn.tedu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.tedu.pojo.ClassToTeacher;

/**
 * 
 * @author HaiDi
 * @version 下午12:33:55
 * @email 2449365663@qq.com
 */
@Mapper
public interface ClassToTeacherMapper {
	/**
	 * 根据教师id查询教师授课的班级有哪些
	 * @param teacherid
	 * @return
	 */
	public List<ClassToTeacher> selectByTeacherId(String teacherId);
}
