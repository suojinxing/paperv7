package cn.tedu.pojo;

/**
 * 
 * @author HaiDi
 * @version 上午11:01:29
 * @email 2449365663@qq.com
 */
public class ClassToTeacher {
	private String classId;
	private String teacherId;
	private String teaching;

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeaching() {
		return teaching;
	}

	public void setTeaching(String teaching) {
		this.teaching = teaching;
	}

	@Override
	public String toString() {
		return "ClassToTeacher [classId=" + classId + ", teacherId=" + teacherId
				+ ", teaching=" + teaching + "]";
	}

}
