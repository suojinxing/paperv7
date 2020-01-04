package cn.tedu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.dao.ScoreListMapper;
import cn.tedu.exception.ServiceException;
import cn.tedu.pojo.ScoreList;
import cn.tedu.pojo.Student;

/**
 * 
 * @author HaiDi
 * @version 下午1:08:48
 * @email 2449365663@qq.com
 */
@Service
public class ScoreListServiceImpl implements ScoreListService {
	@Autowired
	private ScoreListMapper scoreListMapper;
	@Autowired
	private StudentService studentService;
	@Autowired
	private ScoreService scoreService;

	@Override
	public List<ScoreList> findAll() {
		List<ScoreList> scoreLList = scoreListMapper.selectAll();
		return scoreLList;
	}

	@Override
	public void addScoreId(String scoreId) {
		// 判断scoreId是否为空
		if (scoreId == null || "".equals(scoreId)) {
			throw new ServiceException("scoreId 不能为空!");
		}
		if (!scoreId.matches("\\d{1,10}")) {
			throw new ServiceException("scoreId必须为数字和字符的组合,且最多不能超过10位");
		}
		scoreListMapper.insertScoreId(scoreId);
		// 添加完成后,要向成绩表添加所有学生的信息
		List<Student> studentList = studentService.findAll();
		for(Student stu : studentList) {
			scoreService.insertObjects(stu.getStuId(), scoreId, stu.getName());
		}
	}

	@Override
	public void removeScoreId(String scoreId) {
		scoreListMapper.deleteScoreId(scoreId);
		// 删除成功后再删除成绩表中的这次考试的成绩
		scoreService.deleteObjectsByScoreId(scoreId);
	}
}
