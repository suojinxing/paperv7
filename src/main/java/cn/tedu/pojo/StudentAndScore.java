package cn.tedu.pojo;

/**
 * 
 * @author HaiDi
 * @version 下午5:30:07
 * @email 2449365663@qq.com
 */
public class StudentAndScore {
	private String name;
	private String stuId;
	private Double chineseScore;
	private Double mathScore;
	private Double englishScore;
	private Double physicsScore;
	private Double chemistryScore;
	private Double biologyScore;
	private Double politicsScore;
	private Double geographyScore;
	private Double historyScore;
	private Double totalScore;

	public StudentAndScore() {
		super();
	}

	public StudentAndScore(String name, String stuId, Double chineseScore,
			Double mathScore, Double englishScore, Double physicsScore,
			Double chemistryScore, Double biologyScore, Double politicsScore,
			Double geographyScore, Double historyScore, Double totalScore) {
		super();
		this.name = name;
		this.stuId = stuId;
		this.chineseScore = chineseScore;
		this.mathScore = mathScore;
		this.englishScore = englishScore;
		this.physicsScore = physicsScore;
		this.chemistryScore = chemistryScore;
		this.biologyScore = biologyScore;
		this.politicsScore = politicsScore;
		this.geographyScore = geographyScore;
		this.historyScore = historyScore;
		this.totalScore = totalScore;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStuId() {
		return stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
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

	public Double getPoliticsScore() {
		return politicsScore;
	}

	public void setPoliticsScore(Double politicsScore) {
		this.politicsScore = politicsScore;
	}

	public Double getGeographyScore() {
		return geographyScore;
	}

	public void setGeographyScore(Double geographyScore) {
		this.geographyScore = geographyScore;
	}

	public Double getHistoryScore() {
		return historyScore;
	}

	public void setHistoryScore(Double historyScore) {
		this.historyScore = historyScore;
	}

	public Double getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Double totalScore) {
		this.totalScore = totalScore;
	}

	@Override
	public String toString() {
		return "StudentAndScore [name=" + name + ", stuId=" + stuId
				+ ", chineseScore=" + chineseScore + ", mathScore=" + mathScore
				+ ", englishScore=" + englishScore + ", physicsScore="
				+ physicsScore + ", chemistryScore=" + chemistryScore
				+ ", biologyScore=" + biologyScore + ", politicsScore="
				+ politicsScore + ", geographyScore=" + geographyScore
				+ ", historyScore=" + historyScore + ", totalScore="
				+ totalScore + "]";
	}

}
