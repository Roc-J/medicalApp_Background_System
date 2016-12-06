package cn.edu.bupt.springmvc.web.dto;

import java.util.Date;

public class AppointmentRecordDTO {
	
	private String id;

    private String doctorid;

    private String customerid;

    private String realseid;

    private String idcard;

    private Byte type;

    private String sectionname;

    private String outpatientname;

    private String appointdate;

    private Double cost;

    private String telephone;

    private String customername;

    private String doctorname;

    private String sectionarea;
    
    private String week;
    
    private String ampm;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDoctorid() {
		return doctorid;
	}

	public void setDoctorid(String doctorid) {
		this.doctorid = doctorid;
	}

	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	public String getRealseid() {
		return realseid;
	}

	public void setRealseid(String realseid) {
		this.realseid = realseid;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	public String getSectionname() {
		return sectionname;
	}

	public void setSectionname(String sectionname) {
		this.sectionname = sectionname;
	}

	public String getOutpatientname() {
		return outpatientname;
	}

	public void setOutpatientname(String outpatientname) {
		this.outpatientname = outpatientname;
	}

	public String getAppointdate() {
		return appointdate;
	}

	public void setAppointdate(String appointdate) {
		this.appointdate = appointdate;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getDoctorname() {
		return doctorname;
	}

	public void setDoctorname(String doctorname) {
		this.doctorname = doctorname;
	}

	public String getSectionarea() {
		return sectionarea;
	}

	public void setSectionarea(String sectionarea) {
		this.sectionarea = sectionarea;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getAmpm() {
		return ampm;
	}

	public void setAmpm(String ampm) {
		this.ampm = ampm;
	}
    
    
    

}
