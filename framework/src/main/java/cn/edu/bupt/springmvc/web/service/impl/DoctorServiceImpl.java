package cn.edu.bupt.springmvc.web.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.edu.bupt.springmvc.web.dao.DoctorMapper;
import cn.edu.bupt.springmvc.web.dao.ReleasenumMapper;
import cn.edu.bupt.springmvc.web.model.Doctor;
import cn.edu.bupt.springmvc.web.model.DoctorExample;
import cn.edu.bupt.springmvc.web.model.Releasenum;
import cn.edu.bupt.springmvc.web.model.ReleasenumExample;
import cn.edu.bupt.springmvc.web.service.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService {

	@Resource
	private DoctorMapper doctorMapper;
	
	@Resource
	private ReleasenumMapper releasenumMapper;
	
	private DoctorExample doctorExample;
	
	private ReleasenumExample releasenumExample; 
	
	
	@Override
	public int insert(Doctor record) {
		String id = UUID.randomUUID().toString();
		record.setDoctorid(id);
		record.setOutpatientid("f87fd060-e1eb-45c3-b863-8dbc8fba0765"); //门诊表主键（外键）
		record.setDoctorname("霍俊平");
		record.setPichead("");
		record.setPosition("教授");
		record.setSpecial("擅长神经内科");
		record.setSection("神经内科");
		record.setDoctorintro("医生简介信息：专家坐诊");
		record.setFlag(new Byte((byte) 1));
		record.setSex("男");
		record.setGraduinstitution("XX医院");
		record.setTutorqualification(null);
		record.setDegree("博士");
		record.setMarjor("神经内科");
		record.setParttimeacademic(null);
		record.setCertificatehold(null);
		record.setIsfamilydoc(null);
		record.setTelephone("13789547873");
		record.setRelnubcount((byte)50);
		return doctorMapper.insert(record);
	}

	@Override
	public List<Doctor> selectByExample() {
		doctorExample = new DoctorExample();
		doctorExample.createCriteria().andDoctornameEqualTo("张平");
		List<Doctor> list = doctorMapper.selectByExample(doctorExample);
		return list;
	}

	@Override
	public List<Doctor> selectBySection(String sectionName) {
		doctorExample = new DoctorExample();
		doctorExample.createCriteria().andSectionEqualTo(sectionName);
		List<Doctor> list = doctorMapper.selectByExample(doctorExample);
		return list;
	}

	@Override
	public Doctor searchDoctorInfo(String doctorId) {
		Doctor record = doctorMapper.selectByPrimaryKey(doctorId);
		return record;
	}

	@Override
	public List<Doctor> selectByPage(int page, int rows) {
		doctorExample = new DoctorExample();
		PageHelper.startPage(page,rows);
		List<Doctor> list = doctorMapper.selectByExample(doctorExample);
		return list;
	}

	@Override
	public int updateByPrimaryKeySelective(Doctor record) {
		record.setDoctorid("2f869097-31fc-44c8-ac92-ff516faa3bf3");
		record.setOutpatientid("22222"); //门诊表主键（外键）
		record.setDoctorname("张平");
		record.setPichead("");
		record.setPosition("主治医生");
		record.setSpecial("擅长外科手术");
		record.setSection("外科");
		record.setDoctorintro("医生简介");
		record.setFlag(new Byte((byte) 2));
		record.setSex("男");
		record.setGraduinstitution("医科大");
		record.setTutorqualification("有从业导师资格证");
		record.setDegree("硕士");
		record.setMarjor("外科");
		record.setParttimeacademic(null);
		record.setCertificatehold("国家一级证书");
		record.setIsfamilydoc(null);
		record.setTelephone(null);
		record.setRelnubcount((byte)100);
		int i = doctorMapper.updateByPrimaryKeySelective(record);
		return i;
	}

	@Override
	public int deleteByExample() {
		doctorExample = new DoctorExample();
		doctorExample.createCriteria().andDoctornameEqualTo("王明");
		int i = doctorMapper.deleteByExample(doctorExample);
		return i;
	}
	
	public Doctor getDoctorDetailInfo(String doctorId) throws Exception {
		// TODO Auto-generated method stub
		return doctorMapper.selectByPrimaryKey(doctorId);
	}
	
	
	public List<Doctor> getDoctorReleaseNumByOutPatientId(String outpatientId) throws Exception {
		doctorExample = new DoctorExample();
		doctorExample.createCriteria().andOutpatientidEqualTo(outpatientId);
		List<Doctor> doctorReleasList = new ArrayList<>();
		List<Doctor> doctorList = doctorMapper.selectByExample(doctorExample);
		if (doctorList != null) {
			for (Doctor doctor : doctorList) {
				String doctorId = doctor.getDoctorid();
				releasenumExample = new ReleasenumExample();
				// 得到7天之内的判断条件
				Calendar calendar = Calendar.getInstance();
				String fromdays = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
				Date date1 = (new SimpleDateFormat("yyyy-MM-dd")).parse(fromdays);
				calendar.add(calendar.DATE, 7);
				String todays = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
				Date date2 = (new SimpleDateFormat("yyyy-MM-dd")).parse(todays);

				releasenumExample.createCriteria().andDoctoridEqualTo(doctorId).andDateBetween(date1, date2).andIssuccessEqualTo((byte)1);
				List<Releasenum> releasenumList = releasenumMapper.selectByExample(releasenumExample);
				if(releasenumList!=null){
				doctor.setReleaseNumList(releasenumList);
				doctorReleasList.add(doctor);
				}
			}
		}
		return doctorReleasList;
	}

}
