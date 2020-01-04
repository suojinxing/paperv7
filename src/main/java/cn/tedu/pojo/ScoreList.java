package cn.tedu.pojo;

/**
 * 每次考试都对应一个唯一的考试id,将所有的考试场次,对应的id信息都存入该类中
 * @author HaiDi
 * @version 下午1:03:26
 * @email 2449365663@qq.com
 */
public class ScoreList {
	private Integer id;
	private String scoreId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getScoreId() {
		return scoreId;
	}

	public void setScoreId(String scoreId) {
		this.scoreId = scoreId;
	}

	@Override
	public String toString() {
		return "ScoreList [id=" + id + ", scoreId=" + scoreId + "]";
	}

}
