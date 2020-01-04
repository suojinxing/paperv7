package cn.tedu.pojo;

/**
 * 
 * @author HaiDi
 * @version 上午11:05:13
 * @email 2449365663@qq.com
 */
public class Score {
	private String scoreId;
	private String stuId;
	private String stuName;
	private Double chineseScore;
	private Double mathScore;
	private Double englishScore;
	private Double physicsScore;
	private Double chemistryScore;
	private Double biologyScore;
	private Double historyScore;
	private Double geographyScore;
	private Double politicsScore;
	private Double totalScore;

	public String getScoreId() {
		return scoreId;
	}

	public void setScoreId(String scoreId) {
		this.scoreId = scoreId;
	}

	public String getStuId() {
		return stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public Double getChineseScore() {
		return chineseScore;
	}

	public void setChineseScore(Double chineseScore) {
		this.chineseScore = chineseScore;
	}

	public Double getMathScore() {
		return mathScore;
	}

	public void setMathScore(Double mathScore) {
		this.mathScore = mathScore;
	}

	public Double getEnglishScore() {
		return englishScore;
	}

	public void setEnglishScore(Double englishScore) {
		this.englishScore = englishScore;
	}

	public Double getPhysicsScore() {
		return physicsScore;
	}

	public void setPhysicsScore(Double physicsScore) {
		this.physicsScore = physicsScore;
	}

	public Double getChemistryScore() {
		return chemistryScore;
	}

	public void setChemistryScore(Double chemistryScore) {
		this.chemistryScore = chemistryScore;
	}

	public Double getBiologyScore() {
		return biologyScore;
	}

	public void setBiologyScore(Double biologyScore) {
		this.biologyScore = biologyScore;
	}

	public Double getHistoryScore() {
		return historyScore;
	}

	public void setHistoryScore(Double historyScore) {
		this.historyScore = historyScore;
	}

	public Double getGeographyScore() {
		return geographyScore;
	}

	public void setGeographyScore(Double geographyScore) {
		this.geographyScore = geographyScore;
	}

	public Double getPoliticsScore() {
		return politicsScore;
	}

	public void setPoliticsScore(Double politicsScore) {
		this.politicsScore = politicsScore;
	}

	public Double getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Double totalScore) {
		this.totalScore = totalScore;
	}

	@Override
	public String toString() {
		return "Score [scoreId=" + scoreId + ", stuId=" + stuId + ", stuName="
				+ stuName + ", chineseScore=" + chineseScore + ", mathScore="
				+ mathScore + ", englishScore=" + englishScore
				+ ", physicsScore=" + physicsScore + ", chemistryScore="
				+ chemistryScore + ", biologyScore=" + biologyScore
				+ ", historyScore=" + historyScore + ", geographyScore="
				+ geographyScore + ", politicsScore=" + politicsScore
				+ ", totalScore=" + totalScore + "]";
	}

}
