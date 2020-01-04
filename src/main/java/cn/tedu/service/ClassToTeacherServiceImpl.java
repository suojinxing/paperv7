package cn.tedu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.dao.ClassToTeacherMapper;
import cn.tedu.pojo.ClassToTeacher;

/**
 * 
 * @author HaiDi
 * @version 下午12:40:14
 * @email 2449365663@qq.com
 */
@Service
public class ClassToTeacherServiceImpl implements ClassToTeacherService {

	@Autowired
	private ClassToTeacherMapper classToTeacherMapper;
	
	@Override
	public List<ClassToTeacher> findByTeacherId(String teacherId) {
		List<ClassToTeacher> classToTeacherList = classToTeacherMapper.selectByTeacherId(teacherId);
		return classToTeacherList;
	}
}
