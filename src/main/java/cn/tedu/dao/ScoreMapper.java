package cn.tedu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.tedu.pojo.Score;

/**
 * 
 * @author HaiDi
 * @version 下午1:00:10
 * @email 2449365663@qq.com
 */
@Mapper
public interface ScoreMapper {
	/**
	 * 获取所有的成绩表
	 * 
	 * @return
	 */
	public List<Score> selectAll();

	/**
	 * 通过考试id和学生id查出指定学生的成绩信息
	 * 
	 * @param scoreId 考试id
	 * @param stuId   学号
	 * @return 返回一个学生成绩信息
	 */
	public Score selectByScoreIdAndStuId(Score score);

	/** 根据考试id获取学生成绩集合 */
	public List<Score> selectByScoreId(String scoreId);

	/**
	 * 修改学生的成绩
	 * 
	 * @param score
	 * @return
	 */
	public int updateStuScores(Score score);

	public List<Score> selectByStuid(String stuId);

	public int insertObjects(@Param("stuId") String stuId,
                             @Param("scoreId") String scoreId, @Param("stuName") String stuName);

	public int updateObjects(@Param("stuId") String stuId,
                             @Param("scoreId") String scoreId, @Param("stuName") String stuName);

	public int deleteObjects(@Param("stuId") String stuId);

	public int deleteObjectsByScoreId(@Param("scoreId") String scoreId);
}
