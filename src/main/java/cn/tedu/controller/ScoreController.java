package cn.tedu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.pojo.Score;
import cn.tedu.pojo.ScoreList;
import cn.tedu.pojo.StudentAndScore;
import cn.tedu.service.ScoreListService;
import cn.tedu.service.ScoreService;
import cn.tedu.vo.JsonResult;

/**
 * 
 * @author HaiDi
 * @version 下午3:13:47
 * @email 2449365663@qq.com
 */
@RestController
public class ScoreController {
	@Autowired
	private ScoreService scoreService;
	@Autowired
	private ScoreListService scoreListService;

	/**
	 * 返回学生成绩信息
	 */
	@RequestMapping("/getStudentScoresList")
	public List<Score> getStudentScoresList() {
		// 获取学生成绩
		List<Score> list = scoreService.findAll();
		return list;
	}

	/**
	 * 返回学生成绩信息
	 */
	@RequestMapping("/getStudentScores")
	public List<StudentAndScore> getStudentScores(String scoreId,
			String stuId) {
		System.out
				.println("ScoreController.getStudentScores() ===> " + scoreId);
		System.out.println("ScoreController.getStudentScores() ===> " + stuId);
		// 获取学生成绩
		Score score = scoreService.findByScoreIdAndStuId(scoreId, stuId);
		System.out.println(
				"ScoreController.getStudentScores() ===> 成绩是 ==> " + score);
		// 获取学生信息
		return null;
	}

	@RequestMapping("/getScoresByScoreId")
	public List<Score> getScoresByScoreId(String scoreId) {
//		scoreService.findByScoreId(scoreId);
		List<Score> list = scoreService.findByScoreId("13151101");
		return list;
	}

	@RequestMapping("/findScoreList")
	public JsonResult findScoreList() {
		List<ScoreList> list = scoreListService.findAll();
		JsonResult result = new JsonResult(list);
		return result;
	}

	@RequestMapping("/addScoreList")
	public JsonResult addScoreList(String scoreId) {
		scoreListService.addScoreId(scoreId);
		JsonResult result = new JsonResult("update ok");
		return result;
	}

	@RequestMapping("/deleteScoreId")
	public JsonResult deleteScoreId(String scoreId) {
		scoreListService.removeScoreId(scoreId);
		JsonResult result = new JsonResult("delete ok");
		return result;
	}

}
