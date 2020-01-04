package cn.tedu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import cn.tedu.pojo.ScoreList;

/**
 * 
 * @author HaiDi
 * @version 下午1:07:52
 * @email 2449365663@qq.com
 */
@Mapper
public interface ScoreListMapper {
	public List<ScoreList> selectAll();
	
	public void insertScoreId(String scoreId);
	
	@Delete("delete from score_list where score_id=#{scoreId}")
	public void deleteScoreId(String scoreId);
}
