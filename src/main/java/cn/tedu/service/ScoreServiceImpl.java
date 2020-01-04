package cn.tedu.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.constant.Teaching;
import cn.tedu.dao.ScoreMapper;
import cn.tedu.exception.ServiceException;
import cn.tedu.pojo.Score;
import cn.tedu.util.ParamsUtil;

/**
 * 
 * @author HaiDi
 * @version 下午3:28:03
 * @email 2449365663@qq.com
 */
@Service
public class ScoreServiceImpl implements ScoreService {
	@Autowired
	private ScoreMapper scoreMapper;

	public List<Score> findAll() {
		List<Score> scoreList = scoreMapper.selectAll();
		return scoreList;
	}

	public Score findByScoreIdAndStuId(String scoreId, String stuId) {
		Score score = new Score();
		score.setScoreId(scoreId);
		score.setStuId(stuId);
		Score s = scoreMapper.selectByScoreIdAndStuId(score);
		return s;
	}

	@Override
	public List<Score> findByScoreId(String scoreId) {
		List<Score> scoreList = scoreMapper.selectByScoreId(scoreId);
		return scoreList;
	}

	@Override
	public int updateStuScores(String scoreId, String stuIds, String scores, String teaching, String oldTotalScore) {
		System.out.println("ScoreServiceImpl.updateStuScores()" + oldTotalScore);
		// 判断成绩是否符合标准,语数英不可以超过150分,其他100分
		/*
		 * 由于只修改一门成绩,所以只用更改总分数和对应科目的分数
		 */

		// 1. 获得该科目对应的pojo字段
		String field = Teaching.getTeachingToField(teaching);
		// 初始化数组,是的可以修改成绩
		Score[] score = initScore(scoreId, stuIds, scores, teaching, oldTotalScore, field); // 初始化数组
		int stuNums = score.length;
		for (int i = 0; i < stuNums; i++) {
			scoreMapper.updateStuScores(score[i]);
			System.out.println("怎么回事呢? ====> " + score[i]);
		}
		return stuNums;
	}

	/**
	 * 初始化对象数组
	 * 
	 * @param field
	 * @param stuNums
	 * @param scoreArr
	 * @param score
	 */
	private Score[] initScore(String scoreId, String stuIds, String scores, String teaching, String oldTotalScore,
			String field) {
		// 1. 拆分学号id 格式: `stuIds:161400000001,161200000002,161400000003`
		stuIds = stuIds.substring("stuIds:".length());
		String[] stuIdStr = stuIds.split(",");
		// 2. 拆分成绩 格式 `scoresId:33,33,55`
		scores = scores.substring("scores:".length());
		String[] scoreStr = scores.split(",");
		// 3. 拆分总成绩 格式 `oldTotalScore: 33,44,55`
		oldTotalScore = oldTotalScore.substring("oldTotalScore:".length());
		System.out.println("ScoreServiceImpl.initScore()" + oldTotalScore);
		String[] oldTotalScoreStr = oldTotalScore.split(",");
		// 4. 成绩和学号一一对应
		int stuNums = stuIdStr.length; // 一共修改多少个学生的成绩
		// 4.1 将成绩字符串转换为Double类型
		Double[] scoreArr = new Double[stuNums];
		for (int i = 0; i < stuNums; i++) {
			scoreArr[i] = Double.parseDouble(scoreStr[i]);
		}
		// 4.2 将总成绩转换为Double类型
		Double[] oldTotalScoreArr = new Double[stuNums];
		for (int i = 0; i < stuNums; i++) {
			System.out.println(Arrays.toString(oldTotalScoreStr));
			oldTotalScoreArr[i] = Double.parseDouble(oldTotalScoreStr[i]);
		}
		Score[] score = new Score[stuNums]; // 多个学生对象
		for (int i = 0; i < stuNums; i++) {
			score[i] = new Score();
			System.out.println("设置id");
			score[i].setScoreId(scoreId); // 设置考试id
			System.out.println(scoreId + "设置成功");
			System.out.println("设置学号");
			score[i].setStuId(stuIdStr[i]); // 设置学号
			System.out.println("设置成功" + stuIdStr[i]);
			System.out.println("设置老的总成绩");
			score[i].setTotalScore(oldTotalScoreArr[i]);
			System.out.println("设置成功 " + oldTotalScoreArr[i]);
		}
		if (field == "chineseScore") {
			for (int i = 0; i < stuNums; i++) {
				score[i].setChineseScore(scoreArr[i]); // 给每个对象设置成绩
				double totalScore = score[i].getTotalScore() + score[i].getChineseScore();
				score[i].setTotalScore(totalScore); // 设置总成绩
			}
		} else if (field == "mathScore") {
			for (int i = 0; i < stuNums; i++) {
				score[i].setMathScore(scoreArr[i]); // 给每个对象设置成绩
				double totalScore = score[i].getTotalScore() + score[i].getMathScore();
				score[i].setTotalScore(totalScore); // 设置总成绩
			}
		} else if (field == "englishScore") {
			for (int i = 0; i < stuNums; i++) {
				score[i].setEnglishScore(scoreArr[i]); // 给每个对象设置成绩
				double totalScore = score[i].getTotalScore() + score[i].getEnglishScore();
				score[i].setTotalScore(totalScore); // 设置总成绩
			}
		} else if (field == "physicsScore") {
			for (int i = 0; i < stuNums; i++) {
				score[i].setPhysicsScore(scoreArr[i]); // 给每个对象设置成绩
				double totalScore = score[i].getTotalScore() + score[i].getPhysicsScore();
				score[i].setTotalScore(totalScore); // 设置总成绩
			}
		} else if (field == "chemistryScore") {
			for (int i = 0; i < stuNums; i++) {
				score[i].setChemistryScore(scoreArr[i]); // 给每个对象设置成绩
				double totalScore = score[i].getTotalScore() + score[i].getChemistryScore() - scoreArr[i];
				score[i].setTotalScore(totalScore); // 设置总成绩
			}
		} else if (field == "biologyScore") {
			for (int i = 0; i < stuNums; i++) {
				score[i].setBiologyScore(scoreArr[i]); // 给每个对象设置成绩
				double totalScore = score[i].getTotalScore() + score[i].getBiologyScore();
				score[i].setTotalScore(totalScore); // 设置总成绩
			}
		} else if (field == "politicsScore") {
			for (int i = 0; i < stuNums; i++) {
				score[i].setPoliticsScore(scoreArr[i]); // 给每个对象设置成绩
				double totalScore = score[i].getTotalScore() + score[i].getPoliticsScore();
				score[i].setTotalScore(totalScore); // 设置总成绩
			}
		} else if (field == "geographyScore") {
			for (int i = 0; i < stuNums; i++) {
				score[i].setGeographyScore(scoreArr[i]); // 给每个对象设置成绩
				double totalScore = score[i].getTotalScore() + score[i].getGeographyScore();
				score[i].setTotalScore(totalScore); // 设置总成绩
			}
		} else if (field == "historyScore") {
			for (int i = 0; i < stuNums; i++) {
				score[i].setHistoryScore(scoreArr[i]); // 给每个对象设置成绩
				double totalScore = score[i].getTotalScore() + score[i].getHistoryScore();
				score[i].setTotalScore(totalScore); // 设置总成绩
			}
		}
		return score;
	}

	@Override
	public List<Score> findByStuId(String stuId) {
		return scoreMapper.selectByStuid(stuId);
	}

	@Override
	public int insertObjects(String stuId, String scoreId, String stuName) {
		if (ParamsUtil.regexParamsIsNullOrEmpty(stuId, scoreId, stuName)) {
			throw new ServiceException("学号或考试编号或姓名不可以为空");
		}
		int rows = scoreMapper.insertObjects(stuId, scoreId, stuName);
		if (rows == 0) {
			throw new ServiceException("插入失败,请重新操作");
		}
		return rows;
	}

	@Override
	public int deleteObjects(String stuId) {
		if (ParamsUtil.regexParamsIsNullOrEmpty(stuId)) {
			throw new ServiceException("学号不可以为空");
		}
		int rows = scoreMapper.deleteObjects(stuId);
		return rows;
	}

	@Override
	public int deleteObjectsByScoreId(String scoreId) {
		if (ParamsUtil.regexParamsIsNullOrEmpty(scoreId)) {
			throw new ServiceException("学号不可以为空");
		}
		int rows = scoreMapper.deleteObjectsByScoreId(scoreId);
		return rows;
	}

	@Override
	public int updateObjects(String stuId, String scoreId, String stuName) {
		if (ParamsUtil.regexParamsIsNullOrEmpty(stuId, scoreId, stuName)) {
			throw new ServiceException("学号或考试编号或姓名不可以为空");
		}
		return scoreMapper.updateObjects(stuId, scoreId, stuName);
	}

	/**
	 * 判断成绩是否合法，不可以大于120或为负数
	 * @param score
	 */
	public void scoreOutOfBounds(double score) {
		if(score > 120 || score < 0) {
			throw new ServiceException("物化政史地生成绩不可以大于120.或为负数,无法保存");
		}
	}
	
	@Override
	public void updateScore(String scoreId, String[] stuIds, String[] scores, String teaching) {
		// 1. 把字符串转换成成绩
		stuIds[0] = stuIds[0].substring("stuIds:".length());
		scores[0] = scores[0].substring("scores:".length());
		for (int i = 0; i < scores.length; i++) {
			System.out.println("ScoreServiceImpl.updateScore()" + stuIds[i]);
			System.out.println("ScoreServiceImpl.updateScore()" + scores[i]);
		}
		Double[] stuScores = new Double[scores.length];
		for (int i = 0; i < scores.length; i++) {
			stuScores[i] = Double.parseDouble(scores[i]);
			if(stuScores[i]<0||stuScores[i]>150) {
				throw new ServiceException("成绩不合法,无法保存");
			}
		}

		// 2. 根据学号和考试号更新学成成绩
		for (int i = 0; i < stuScores.length; i++) {
			Score score = new Score();
			score.setStuId(stuIds[i]); // 设置学号
			score.setScoreId(scoreId); // 设置考试编号
			String field = Teaching.getTeachingToField(teaching);
			if (field == "chineseScore") {
				score.setChineseScore(stuScores[i]);
			} else if (field == "mathScore") {
				score.setMathScore(stuScores[i]);
			} else if (field == "englishScore") {
				score.setEnglishScore(stuScores[i]);
			} else if (field == "physicsScore") {
				scoreOutOfBounds(stuScores[i]);
				score.setPhysicsScore(stuScores[i]);
			} else if (field == "chemistryScore") {
				score.setChemistryScore(stuScores[i]);
				scoreOutOfBounds(stuScores[i]);
			} else if (field == "biologyScore") {
				score.setBiologyScore(stuScores[i]);
				scoreOutOfBounds(stuScores[i]);
			} else if (field == "politicsScore") {
				score.setPhysicsScore(stuScores[i]);
				scoreOutOfBounds(stuScores[i]);
			} else if (field == "geographyScore") {
				score.setGeographyScore(stuScores[i]);
				scoreOutOfBounds(stuScores[i]);
			} else if (field == "historyScore") {
				scoreOutOfBounds(stuScores[i]);
				score.setHistoryScore(stuScores[i]);
			}
			scoreMapper.updateStuScores(score);
		}
		// 3. 更新总成绩
		for (int i = 0; i < scores.length; i++) {
			// 获取学生的所有成绩
			Score score = new Score();
			score.setStuId(stuIds[i]);
			score.setScoreId(scoreId);
			score = scoreMapper.selectByScoreIdAndStuId(score);
			Double totalScore = score.getChineseScore() + score.getMathScore() + score.getEnglishScore()
					+ score.getPhysicsScore() + score.getChineseScore() + score.getBiologyScore()
					+ score.getHistoryScore() + score.getGeographyScore() + score.getPoliticsScore();
			score.setTotalScore(totalScore);
			scoreMapper.updateStuScores(score);
		}
	}
}
