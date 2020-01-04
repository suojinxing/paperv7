package cn.tedu.service;

import java.util.List;

import cn.tedu.pojo.ScoreList;

/**
 * 
 * @author HaiDi
 * @version 下午1:08:48
 * @email 2449365663@qq.com
 */
public interface ScoreListService {
	/**
	 * 获取所有考试场次的id
	 * 
	 * @return
	 */
	public List<ScoreList> findAll();

	public void addScoreId(String scoreId);
	
	public void removeScoreId(String scoreId);
}
