package cn.tedu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.annotation.RedisCacheFind;
import cn.tedu.annotation.RequestLog;
import cn.tedu.dao.StudentMapper;
import cn.tedu.exception.ServiceException;
import cn.tedu.pojo.ScoreList;
import cn.tedu.pojo.Student;
import cn.tedu.util.ParamsUtil;

/**
 * 
 * @author HaiDi
 * @version 下午2:18:50
 * @email 2449365663@qq.com
 */
@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentMapper studentMapper;
	@Autowired
	private ScoreService scoreService;
	@Autowired
	private ScoreListService scoreListService;

	@Override
	public List<Student> findAllByClassId(String classId) {
		List<Student> studentList = studentMapper.selectAllByClassId(classId);
		return studentList;
	}

	@RequestLog("查询学生信息...")
	@RedisCacheFind(key = "student")
	@Override
	public List<Student> findAll() {
		return studentMapper.selectAll();
	}

	@Override
	public void addStudent(Student student) {
		if (student == null) {
			throw new ServiceException("学生不可以是空的");
		}
		studentMapper.insertStudent(student);
	}

	@Override
	public void updateStudent(String stuId, String name, String classId,
			String age, String gender) {
		if (ParamsUtil.regexParamsIsNullOrEmpty(name, stuId, classId, age,
				gender)) {
			throw new ServiceException("请检查输入的信息是否有误!");
		}
		// 判断学生信息是否存在
		Student student = findByStuId(stuId);
		if (student != null) {
			try {
				// 1. 若存在,则修改学生信息
				student.setName(name);
				student.setClassId(classId);
				student.setGender(gender);
				if (age != null) {
					student.setAge(Integer.parseInt(age));
				}
			} catch (Exception e) {
				throw new ServiceException("数据不合法");
			} finally {
				studentMapper.updateStudent(student);
				// 更改成绩表中的信息
				// 获取所有的考试编号
				List<ScoreList> scoreList = scoreListService.findAll();
				for (ScoreList list : scoreList) {
					scoreService.updateObjects(stuId, list.getScoreId(),
							student.getName());
				}
			}
		} else {
			// 2. 若不存在,则创建学生
			student = new Student();
			// 2.1 添加学生信息
			if (stuId == null) {
				throw new ServiceException("学号不能为空");
			}
			student.setStuId(stuId);
			student.setName(name);
			student.setClassId(classId);
			try {
				student.setAge(Integer.parseInt(age));
			} catch (Exception e) {
				throw new ServiceException("数据非法");
			}
			student.setGender(gender);
			addStudent(student);
			// 更新学生成绩表中的信息
			// 添加完学生信息后,要在对应的考试表中添加学生的成绩信息
			String stuName = student.getName();
			// 获取所有的考试编号
			List<ScoreList> scoreList = scoreListService.findAll();
			for (ScoreList list : scoreList) {
				scoreService.insertObjects(stuId, list.getScoreId(), stuName);
			}
		}

	}

	@Override
	public Student findByStuId(String stuId) {
		return studentMapper.selectByStuId(stuId);
	}

	@Override
	public void deleteStudent(String stuId) {
		if (ParamsUtil.regexParamsIsNullOrEmpty(stuId)) {
			throw new ServiceException("数据不存在");
		}
		studentMapper.deleteStudent(stuId);
		// 删除后,把对应的成绩删除掉
		scoreService.deleteObjects(stuId);
	}
}
