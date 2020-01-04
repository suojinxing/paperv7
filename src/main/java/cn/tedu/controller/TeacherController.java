package cn.tedu.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.pojo.Teacher;
import cn.tedu.service.ScoreService;
import cn.tedu.service.TeacherService;
import cn.tedu.vo.JsonResult;

/**
 * 
 * @author HaiDi
 * @version 下午5:28:44
 * @email 2449365663@qq.com
 */
@Controller
public class TeacherController {
	@Autowired
	private ScoreService scoreService;
	@Autowired
	private TeacherService teacherService;

	@ResponseBody
	@RequestMapping("/saveStuScore")
	public JsonResult saveStuScore(String scoreId, String[] stuIds,
			String[] scores, String teaching) {
		System.out.println("TeacherController.saveStuScore()" + (scoreId));
		System.out.println("TeacherController.saveStuScore()" + Arrays.toString(stuIds));
		System.out.println("TeacherController.saveStuScore()" + Arrays.toString(scores));
		System.out.println("TeacherController.saveStuScore()" + teaching);
		scoreService.updateScore(scoreId,stuIds,scores,teaching);
		
		return new JsonResult("保存成功");
	}
	
	
//	@ResponseBody
//	@RequestMapping("/saveStuScore")
//	public String saveStuScore(String[] scores,String teaching) {
//		System.out.println("从前端获取的成绩是: " + teaching + "," + Arrays.toString(scores));
//		// 获取到前端传送的数据,保存到数据库
//		return "";
//	}
	
	/*
	@ResponseBody
	@RequestMapping("/saveStuScore")
	public Map<String, String> saveStuScore(String scoreId, String stuIds,
			String scores, String teaching, String oldTotalScore) {
		Map<String, String> msg = new HashMap<String, String>();
		System.out.println("TeacherController.saveStuScore()" + oldTotalScore);
		System.out.println("从前端获取的科目是:" + teaching);
		System.out.println(scoreId);
		System.out.println(scores);
		System.out.println(stuIds);
		try {
			scoreService.updateStuScores(scoreId, stuIds, scores, teaching,
					oldTotalScore);
			msg.put("msg", "成功保存");
		} catch (Exception e) {
			msg.put("msg", e.getMessage());
			System.out.println(msg);
			return msg;
		}
		// 获取到前端传送的数据,保存到数据库
		return msg;
	}
	*/

	@ResponseBody
	@RequestMapping("/findTeacher")
	public JsonResult findTeacher() {
		List<Teacher> teacherList = teacherService.findAll();
		JsonResult result = new JsonResult(teacherList);
		return result;
	}

	@ResponseBody
	@RequestMapping("/updateTeacherInfo")
	public JsonResult updateTeacherInfo(String teacherName, String teacherId,
			String teacherAge, String teacherSalary, String teacherGender,
			String teacherTeaching) {
		teacherService.updateTeacherInfo(teacherName, teacherId, teacherAge,
				teacherSalary, teacherGender, teacherTeaching);
		JsonResult result = new JsonResult("update ok!");
		return result;
	}

	@ResponseBody
	@RequestMapping("/deleteTeacherFromTeacherModel")
	public JsonResult deleteTeacherFromTeacherModel(String teacherId) {
		teacherService.deleteTeacher(teacherId);
		JsonResult result = new JsonResult("delete ok!");
		return result;
	}

}
