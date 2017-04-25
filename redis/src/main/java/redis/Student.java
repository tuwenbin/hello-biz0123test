package redis;

import java.io.Serializable;
import java.util.Date;

public class Student implements Serializable{
	private String id;
	private String name;//姓名
	private Date birthday;//出生日期
	private String description;//备注
	private Integer avgscore;//平均分
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getAvgscore() {
		return avgscore;
	}
	public void setAvgscore(Integer avgscore) {
		this.avgscore = avgscore;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", birthday=" + birthday + ", description=" + description
				+ ", avgscore=" + avgscore + "]";
	}


}
