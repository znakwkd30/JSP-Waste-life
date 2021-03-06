package db.bean;

import java.util.Date;

public class ProjectBean implements Comparable<ProjectBean> {
	private int id;
	private String name;
	private String subject;
	private Date due;
	private String creator;
	private int heartCnt;
	
	public int getHeartCnt()
	{
		return heartCnt;
	}
	public void setHeartCnt(int heartCnt)
	{
		this.heartCnt = heartCnt;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Date getDue() {
		return due;
	}
	public void setDue(Date due) {
		this.due = due;
	}

	@Override
	public int compareTo(ProjectBean pb){
		Date today = new Date();

		if((long)this.due.getTime() < (long)pb.due.getTime()){
			return 1;
		}
		else if((long)this.due.getTime() > (long)pb.due.getTime()){
			return -1;
		}
		else{
			return 0;
		}
	}
}
