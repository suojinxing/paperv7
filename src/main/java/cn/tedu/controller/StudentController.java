package cn.tedu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.pojo.Class;
import cn.tedu.pojo.Score;
import cn.tedu.pojo.Student;
import cn.tedu.pojo.StudentAndScore;
import cn.tedu.service.ClassService;
import cn.tedu.service.ScoreService;
import cn.tedu.service.StudentService;
import cn.tedu.vo.JsonResult;

/**
 * 
 * @author HaiDi
 * @version 下午2:02:44
 * @email 2449365663@qq.com
 */
@Controller
public class StudentController {
	@Autowired
	private StudentService studentService;
	@Autowired
	private ScoreService scoreService;
	@Autowired
	private ClassService classService;

	@ResponseBody
	@RequestMapping("/getStudentsNameAndScore")
	public Map<String, Object> getStuNames(String classId, String scoreId) {
		System.out.println("StudentController.getStuNames() ===> " + classId);
		System.out.println("StudentController.getStuNames() ===> " + scoreId);
		List<StudentAndScore> studentAndScoreList = new ArrayList<StudentAndScore>();
		// 根据班级id查询学生表
		List<Student> studentList = studentService.findAllByClassId(classId);
		for (Student student : studentList) {
			// 查询每一个学生的成绩信息
			String stuId = student.getStuId(); // 学号
			// 根据学号和考试号查询成绩信息
			Score score = scoreService.findByScoreIdAndStuId(scoreId, stuId);
			System.out.println("score是null? ===> " + score);
			if(score==null) {
				continue;
			}
			StudentAndScore studentAndScore = new StudentAndScore(
					student.getName(), student.getStuId(),
					score.getChineseScore(), score.getMathScore(),
					score.getEnglishScore(), score.getPhysicsScore(),
					score.getChemistryScore(), score.getBiologyScore(),
					score.getPoliticsScore(), score.getGeographyScore(),
					score.getHistoryScore(), score.getTotalScore());
			studentAndScoreList.add(studentAndScore);
		}
		// 创造一个结合体来传递json数据
		System.out.println("===> " + studentAndScoreList);

		// 2. 出了返回学生成绩数据之外,还要返回班级是文科班还是理科班的信息
		// 2.1 所以返回值要改为一个map
		Map<String, Object> infoMap = new HashMap<String, Object>();
		infoMap.put("stuAndScoreInfo", studentAndScoreList); // 封装学生信息和成绩信息(结合体数据类型)

		// 2.2 根据classId查询班级信息
		Class class_ = classService.findById(classId);
		String classNature = class_.getLiberalOrSciences();
		System.out.println("这个班级是: ===> " + classNature + " 班");
		infoMap.put("classNature", classNature);
		return infoMap;
	}

	@ResponseBody
	@RequestMapping("/findStudent")
	public JsonResult findStudent() {
		List<Student> studentList = studentService.findAll();
		JsonResult result = new JsonResult(studentList);
		return result;
	}

	@ResponseBody
	@RequestMapping("/updateStudentInfo")
	public JsonResult updateStudentInfo(String stuId, String name,
			String classId, String age, String gender) {
		studentService.updateStudent(stuId, name, classId, age, gender);
		JsonResult result = new JsonResult("update ok");
		return result;
	}

	@ResponseBody
	@RequestMapping("/deleteStudentFromStudentModel")
	public JsonResult deleteStudentFromStudentModel(String stuId) {
		studentService.deleteStudent(stuId);
		JsonResult result = new JsonResult("delete ok");
		return result;
	}

	@ResponseBody
	@RequestMapping("queryScore")
	public Score queryScore(String stuId, String scoreId) {
		Score score = scoreService.findByScoreIdAndStuId(scoreId, stuId);
		System.out.println(
				"StudentController.queryScore()根据id查出的学生成绩是 ===> " + score);
		return score;
	}

	@ResponseBody
	@RequestMapping("queryAllScore")
	public List<Score> queryAllScore(String stuId) {
		List<Score> scoreList = scoreService.findByStuId(stuId);
		return scoreList;
	}
}
