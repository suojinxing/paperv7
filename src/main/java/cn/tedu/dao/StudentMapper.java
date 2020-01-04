package cn.tedu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import cn.tedu.pojo.Student;

/**
 * 
 * @author HaiDi
 * @version 下午2:17:09
 * @email 2449365663@qq.com
 */
@Mapper
public interface StudentMapper {
	public Student selectByStuId(String stuId);

	public List<Student> selectAllByClassId(String classId);

	public List<Student> selectAll();

	public void insertStudent(Student student);

	public void updateStudent(Student student);
	
	@Delete("delete from student where stu_id=#{stuId}")
	public void deleteStudent(String stuId);

}
