package cn.tedu.pojo;

import java.util.Date;

/**
 * 
 * @author HaiDi
 * @version 上午9:24:39
 * @email 2449365663@qq.com
 */
public class Teacher {
	private Integer id;
	private String name;
	private Integer age;
	private Double salary;
	private Date hiredate;
	private String gender;
	private String teaching;
	private String teacherId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Date getHiredate() {
		return hiredate;
	}

	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getTeaching() {
		return teaching;
	}

	public void setTeaching(String teaching) {
		this.teaching = teaching;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + ", age=" + age
				+ ", salary=" + salary + ", hiredate=" + hiredate + ", gender="
				+ gender + ", teaching=" + teaching + ", teacherId=" + teacherId
				+ "]";
	}

}
