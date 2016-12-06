package cn.edu.bupt.springmvc.web.model;

import java.util.Date;

public class Releasenum {
    private String realseid;

    private String doctorid;

    private Double price;

    private Date date;

    private String remark;

    private Byte issuccess;//1表示预约成功 0表示预约失败

    private Integer isfamilynum;

    private String week;

    private String ampm;

    public String getRealseid() {
        return realseid;
    }

    public void setRealseid(String realseid) {
        this.realseid = realseid == null ? null : realseid.trim();
    }

    public String getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(String doctorid) {
        this.doctorid = doctorid == null ? null : doctorid.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Byte getIssuccess() {
        return issuccess;
    }

    public void setIssuccess(Byte issuccess) {
        this.issuccess = issuccess;
    }

    public Integer getIsfamilynum() {
        return isfamilynum;
    }

    public void setIsfamilynum(Integer isfamilynum) {
        this.isfamilynum = isfamilynum;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week == null ? null : week.trim();
    }

    public String getAmpm() {
        return ampm;
    }

    public void setAmpm(String ampm) {
        this.ampm = ampm == null ? null : ampm.trim();
    }
}