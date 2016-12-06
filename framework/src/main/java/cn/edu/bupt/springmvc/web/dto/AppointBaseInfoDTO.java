package cn.edu.bupt.springmvc.web.dto;

public class AppointBaseInfoDTO {
	String date; //预约日期与releasenum中的date字段对应
	String ampm;
	String week; 
	String doctorId; //医生id
	String releasenumid;//上午的一条可预约号
	double price;
	String outpatientId;//门诊id
	String outpatientName;//门诊名
	String sectionId; //科室id
	String sectionName; //科室名
/*	int countAM; //可预约上午的总数量
	int countPM; //可预约下午的总数量
	int appointendCountAM; //已经预约上午的总数量
	int appointendCountPM; //已经预约下午的总数量
*/	
	public String getDate() {
		return date;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAmpm() {
		return ampm;
	}
	public void setAmpm(String ampm) {
		this.ampm = ampm;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	public String getReleasenumid() {
		return releasenumid;
	}
	public void setReleasenumid(String releasenumid) {
		this.releasenumid = releasenumid;
	}
	public String getOutpatientId() {
		return outpatientId;
	}
	public void setOutpatientId(String outpatientId) {
		this.outpatientId = outpatientId;
	}
	public String getOutpatientName() {
		return outpatientName;
	}
	public void setOutpatientName(String outpatientName) {
		this.outpatientName = outpatientName;
	}
	public String getSectionId() {
		return sectionId;
	}
	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	
	

}
