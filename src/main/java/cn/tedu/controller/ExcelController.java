package cn.tedu.controller;

import cn.tedu.pojo.Score;
import cn.tedu.pojo.Student;
import cn.tedu.pojo.StudentAndScore;
import cn.tedu.service.ClassService;
import cn.tedu.service.ScoreService;
import cn.tedu.service.StudentService;
import cn.tedu.util.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/excel/")
public class ExcelController {
	@Autowired
	private ScoreService scoreService;
	@SuppressWarnings("unused")
	@Autowired
	private ClassService classService;
	@Autowired
	private StudentService studentService;

	@RequestMapping("export")
	@ResponseBody
	public void export(String classId, String scoreId, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println(classId + scoreId);
		// 获取数据
		// excel标题
		String[] title = { "考试编号", "学号", "姓名", "语文", "数学", "英语", "物理", "化学", "生物", "历史", "地理", "政治", "总分", "排名", "班级" };
		// excel文件名
		String fileName = classId + "班学生成绩表" + System.currentTimeMillis() + ".xls";
		// sheet名
		String sheetName = classId + "班学生成绩表";
		// 根据班级信息查询成绩
		// 1. 根据班级id查询学生表
		List<StudentAndScore> studentAndScoreList = new ArrayList<StudentAndScore>();
		// 根据班级id查询学生表
		List<Student> studentList = studentService.findAllByClassId(classId);
		for (Student student : studentList) {
			// 查询每一个学生的成绩信息
			String stuId = student.getStuId(); // 学号
			// 根据学号和考试号查询成绩信息
			Score score = scoreService.findByScoreIdAndStuId(scoreId, stuId);
			System.out.println("score是null? ===> " + score);
			if (score == null) {
				System.out.println("score都可以查得到");
				continue;
			}
			StudentAndScore studentAndScore = new StudentAndScore(student.getName(), student.getStuId(),
					score.getChineseScore(), score.getMathScore(), score.getEnglishScore(), score.getPhysicsScore(),
					score.getChemistryScore(), score.getBiologyScore(), score.getPoliticsScore(),
					score.getGeographyScore(), score.getHistoryScore(), score.getTotalScore());
			studentAndScoreList.add(studentAndScore);
		}
		System.out.println(studentAndScoreList);

		// 为学生成绩排序
		studentAndScoreList = sortScoreList(studentAndScoreList);
		// 为Excel的单元格赋值
		String[][] content = new String[studentAndScoreList.size()][title.length];
		for (int i = 0; i < studentAndScoreList.size(); i++) {
			StudentAndScore s = studentAndScoreList.get(i);
			content[i][0] = scoreId + "";
			content[i][1] = s.getStuId() + "";
			content[i][2] = s.getName() + "";
			content[i][3] = s.getChineseScore() + "";
			content[i][4] = s.getMathScore() + "";
			content[i][5] = s.getEnglishScore() + "";
			content[i][6] = s.getPhysicsScore() + "";
			content[i][7] = s.getChemistryScore() + "";
			content[i][8] = s.getBiologyScore() + "";
			content[i][9] = s.getHistoryScore() + "";
			content[i][10] = s.getGeographyScore() + "";
			content[i][11] = s.getPoliticsScore() + "";
			content[i][12] = s.getTotalScore() + "";
			content[i][13] = (1 + i) + "";
			content[i][14] = classId + "班";

		}
		// 创建HSSFWorkbook
		HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);
		// 响应到客户端
		try {
			this.setResponseHeader(response, fileName);
			OutputStream os = response.getOutputStream();
			wb.write(os);
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 集合中有很多学生对象，根据学生的总分排序
	 */
	private List<StudentAndScore> sortScoreList(List<StudentAndScore> studentAndScoreList) {
		List<StudentAndScore> result = new ArrayList<StudentAndScore>(studentAndScoreList.size());
		// 创建一个对象保存总成绩
		Double[] totalScores = new Double[studentAndScoreList.size()];
		for (int i = 0; i < studentAndScoreList.size(); i++) {
			totalScores[i] = studentAndScoreList.get(i).getTotalScore();
		}
		Arrays.sort(totalScores,Collections.reverseOrder());
		System.out.println("ExcelController.sortScoreList()" + totalScores);
		for (Double d : totalScores) {
			for (StudentAndScore s : studentAndScoreList) {
				if(d == s.getTotalScore()) {
					// 成绩相同，直接放入
					result.add(s);
				}
			}
		}
		return result;
	}

	// 发送响应流方法
	public void setResponseHeader(HttpServletResponse response, String fileName) {
		try {
			try {
				fileName = new String(fileName.getBytes(), "ISO8859-1");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.setContentType("application/octet-stream;charset=ISO8859-1");
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.addHeader("Pargam", "no-cache");
			response.addHeader("Cache-Control", "no-cache");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
