package cn.tedu.pojo;

/**
 * 
 * @author HaiDi
 * @version 下午7:55:55
 * @email 2449365663@qq.com
 */
public class Student {
	private Integer id;
	private Integer age;
	private String stuId;
	private String name;
	private String classId;
	private String gender;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getStuId() {
		return stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", age=" + age + ", stuId=" + stuId
				+ ", name=" + name + ", classId=" + classId + ", gender="
				+ gender + "]";
	}

}
