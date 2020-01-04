package cn.tedu.pojo;

/**
 * 这是班级表
 * 
 * @author HaiDi
 * @version 上午11:02:33
 * @email 2449365663@qq.com
 */
public class Class {
	private String classId;
	private String headmasterName;
	private String headmasterId;
	private String classSize;
	private String liberalOrSciences;

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getHeadmasterName() {
		return headmasterName;
	}

	public void setHeadmasterName(String headmasterName) {
		this.headmasterName = headmasterName;
	}

	public String getHeadmasterId() {
		return headmasterId;
	}

	public void setHeadmasterId(String headmasterId) {
		this.headmasterId = headmasterId;
	}

	public String getClassSize() {
		return classSize;
	}

	public void setClassSize(String classSize) {
		this.classSize = classSize;
	}

	public String getLiberalOrSciences() {
		return liberalOrSciences;
	}

	public void setLiberalOrSciences(String liberalOrSciences) {
		this.liberalOrSciences = liberalOrSciences;
	}

	@Override
	public String toString() {
		return "Class [classId=" + classId + ", headmasterName="
				+ headmasterName + ", headmasterId=" + headmasterId
				+ ", classSize=" + classSize + ", liberalOrSciences="
				+ liberalOrSciences + "]";
	}

}
