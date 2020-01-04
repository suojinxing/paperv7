package cn.tedu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.dao.TeacherMapper;
import cn.tedu.exception.ServiceException;
import cn.tedu.pojo.Teacher;
import cn.tedu.util.ParamsUtil;

/**
 * 
 * @author HaiDi
 * @version 上午9:31:34
 * @email 2449365663@qq.com
 */
@Service
public class TeacherServiceImpl implements TeacherService {
	@Autowired
	private TeacherMapper teacherMapper;

	@Override
	public List<Teacher> findAll() {
		List<Teacher> teacherList = teacherMapper.selectAll();
		return teacherList;
	}

	@Override
	public Teacher findByTeacherId(String teacherId) {
		if (ParamsUtil.regexParamsIsNullOrEmpty(teacherId)) {
			throw new ServiceException("请检查输入的教师编号是否有误,改正后再提交");
		}
		Teacher teacher = teacherMapper.selectByTeacherId(teacherId);
		return teacher;
	}

	@Override
	public void updateTeacherInfo(String teacherName, String teacherId,
			String teacherAge, String teacherSalary, String teacherGender,
			String teacherTeaching) {
		if (ParamsUtil.regexParamsIsNullOrEmpty(teacherName, teacherId,
				teacherAge, teacherSalary, teacherGender, teacherTeaching)) {
			throw new ServiceException("请检查输入的教师信息是否有误,改正后再提交");
		}
		// 根据teacherId判断数据是否存在
		Teacher teacher = findByTeacherId(teacherId);
		if (teacher != null) {
			try {
				// 1. 若存在,则更新数据,
				teacher.setAge(Integer.parseInt(teacherAge));
				teacher.setGender(teacherGender);
				teacher.setSalary(Double.parseDouble(teacherSalary));
				teacher.setName(teacherName);
				teacher.setTeaching(teacherTeaching);
				teacherMapper.updateTeacher(teacher);
			} catch (Exception e) {
				throw new ServiceException("数据非法!");
			}
		} else {
			try {
				// 2. 若不存在,则添加数据
				teacher = new Teacher();
				teacher.setTeacherId(teacherId);
				teacher.setAge(Integer.parseInt(teacherAge));
				teacher.setGender(teacherGender);
				teacher.setSalary(Double.parseDouble(teacherSalary));
				teacher.setName(teacherName);
				teacher.setTeaching(teacherTeaching);
				teacherMapper.insertTeacher(teacher);
			} catch (Exception e) {
				throw new ServiceException("数据非法!");
			}
		}
	}

	@Override
	public void deleteTeacher(String teacherId) {
		if (ParamsUtil.regexParamsIsNullOrEmpty(teacherId)) {
			throw new ServiceException("请检查输入的教师编号是否有误,改正后再提交");
		}
		teacherMapper.deleteTeacher(teacherId);
	}

}
