package spring.core.session06.entity;

import java.util.Date;
import java.util.List;

public class Emp {
	// 變數/欄位
	private Integer eid;
	private String ename;
	private Integer age;
	private Date createtime;
	
	//關聯關係
	private List<Job> jobs; // 1 對 多 的關係 (一個 emp 可以對應 0~多 個 job)
	
	public Emp() {
		
	}

	public Emp(String ename, Integer age) {
		this.ename = ename;
		this.age = age;
	}

	@Override
	public String toString() {
		return "Emp [eid=" + eid + ", ename=" + ename + ", age=" + age + ", createtime=" + createtime + ", jobs=" + jobs
				+ "]";
	}

	public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}
	
	
}
