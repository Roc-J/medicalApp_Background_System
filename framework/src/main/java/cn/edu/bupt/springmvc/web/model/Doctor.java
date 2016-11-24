package cn.edu.bupt.springmvc.web.model;

import java.util.List;

public class Doctor {
    private String doctorid;

    private String outpatientid;

    private String pichead;

    private String doctorname;

    private String position;

    private String special;

    private String section;

    private String doctorintro;

    private Byte flag;

    private String sex;

    private String graduinstitution;

    private String tutorqualification;

    private String degree;

    private String marjor;

    private String parttimeacademic;

    private String certificatehold;

    private Byte isfamilydoc;

    private String telephone;

    private Byte relnubcount;

	 private List<Releasenum> releaseNumList;

    public List<Releasenum> getReleaseNumList() {
		return releaseNumList;
	}

	public void setReleaseNumList(List<Releasenum> releaseNumList) {
		this.releaseNumList = releaseNumList;
	}

    public String getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(String doctorid) {
        this.doctorid = doctorid == null ? null : doctorid.trim();
    }

    public String getOutpatientid() {
        return outpatientid;
    }

    public void setOutpatientid(String outpatientid) {
        this.outpatientid = outpatientid == null ? null : outpatientid.trim();
    }

    public String getPichead() {
        return pichead;
    }

    public void setPichead(String pichead) {
        this.pichead = pichead == null ? null : pichead.trim();
    }

    public String getDoctorname() {
        return doctorname;
    }

    public void setDoctorname(String doctorname) {
        this.doctorname = doctorname == null ? null : doctorname.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special == null ? null : special.trim();
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section == null ? null : section.trim();
    }

    public String getDoctorintro() {
        return doctorintro;
    }

    public void setDoctorintro(String doctorintro) {
        this.doctorintro = doctorintro == null ? null : doctorintro.trim();
    }

    public Byte getFlag() {
        return flag;
    }

    public void setFlag(Byte flag) {
        this.flag = flag;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getGraduinstitution() {
        return graduinstitution;
    }

    public void setGraduinstitution(String graduinstitution) {
        this.graduinstitution = graduinstitution == null ? null : graduinstitution.trim();
    }

    public String getTutorqualification() {
        return tutorqualification;
    }

    public void setTutorqualification(String tutorqualification) {
        this.tutorqualification = tutorqualification == null ? null : tutorqualification.trim();
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree == null ? null : degree.trim();
    }

    public String getMarjor() {
        return marjor;
    }

    public void setMarjor(String marjor) {
        this.marjor = marjor == null ? null : marjor.trim();
    }

    public String getParttimeacademic() {
        return parttimeacademic;
    }

    public void setParttimeacademic(String parttimeacademic) {
        this.parttimeacademic = parttimeacademic == null ? null : parttimeacademic.trim();
    }

    public String getCertificatehold() {
        return certificatehold;
    }

    public void setCertificatehold(String certificatehold) {
        this.certificatehold = certificatehold == null ? null : certificatehold.trim();
    }

    public Byte getIsfamilydoc() {
        return isfamilydoc;
    }

    public void setIsfamilydoc(Byte isfamilydoc) {
        this.isfamilydoc = isfamilydoc;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public Byte getRelnubcount() {
        return relnubcount;
    }

    public void setRelnubcount(Byte relnubcount) {
        this.relnubcount = relnubcount;
    }

	@Override
	public String toString() {
		return "Doctor [doctorid=" + doctorid + ", outpatientid=" + outpatientid + ", pichead=" + pichead
				+ ", doctorname=" + doctorname + ", position=" + position + ", special=" + special + ", section="
				+ section + ", doctorintro=" + doctorintro + ", flag=" + flag + ", sex=" + sex + ", graduinstitution="
				+ graduinstitution + ", tutorqualification=" + tutorqualification + ", degree=" + degree + ", marjor="
				+ marjor + ", parttimeacademic=" + parttimeacademic + ", certificatehold=" + certificatehold
				+ ", isfamilydoc=" + isfamilydoc + ", telephone=" + telephone + ", relnubcount=" + relnubcount
				+ ", releaseNumList=" + releaseNumList + "]";
	}

	@Override
	public int hashCode() {
		return 1;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Doctor)){
			return false;
		}
		Doctor doc = (Doctor)obj;
		if(this.doctorid == doc.getDoctorid()){
			return true;
		}
		return false;
	}
	
    
    
}