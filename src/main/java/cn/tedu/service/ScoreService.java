package cn.tedu.service;

import java.util.List;

import cn.tedu.pojo.Score;

/**
 * 
 * @author HaiDi
 * @version 下午3:28:03
 * @email 2449365663@qq.com
 */
public interface ScoreService {
	/**
	 * 获取所有的成绩表
	 * 
	 * @return
	 */
	public List<Score> findAll();

	/**
	 * 通过考试id和学生id查出指定学生的成绩信息
	 * 
	 * @param scoreId 考试id
	 * @param stuId   学号
	 * @return 返回一个学生成绩信息
	 */
	public Score findByScoreIdAndStuId(String scoreId, String stuId);

	public List<Score> findByStuId(String stuId);

	/** 根据考试id获取学生成绩集合 */
	public List<Score> findByScoreId(String scoreId);

	/**
	 * 1.修改学生的成绩,底层是动态sql,这个sql是服务于教师存储成绩的 2.拆分成绩,拆分学号,分装成多个score对象,再执行sql语句
	 * 
	 * @param score
	 * @param teaching 只修改一门成绩
	 * @return
	 */
	public int updateStuScores(String scoreId, String stuIds, String scores,
                               String teaching, String oldTotalScore);

	public int insertObjects(String stuId, String scoreId, String stuName);

	public int updateObjects(String stuId, String scoreId, String stuName);

	public int deleteObjects(String stuId);

	public int deleteObjectsByScoreId(String scoreId);

	public void updateScore(String scoreId, String[] stuIds, String[] scores, String teaching);
}
