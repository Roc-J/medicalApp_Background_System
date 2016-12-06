package cn.edu.bupt.springmvc.web.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.edu.bupt.springmvc.web.dao.DoctorMapper;
import cn.edu.bupt.springmvc.web.dao.ReleasenumMapper;
import cn.edu.bupt.springmvc.web.dto.AppointBaseInfoDTO;
import cn.edu.bupt.springmvc.web.model.Doctor;
import cn.edu.bupt.springmvc.web.model.DoctorExample;
import cn.edu.bupt.springmvc.web.model.Outpatient;
import cn.edu.bupt.springmvc.web.model.Releasenum;
import cn.edu.bupt.springmvc.web.model.ReleasenumExample;
import cn.edu.bupt.springmvc.web.service.DoctorService;
import cn.edu.bupt.springmvc.web.service.OutpatientService;
import cn.edu.bupt.springmvc.web.service.ReleasenumService;

@Service
public class ReleasenumServiceImpl implements ReleasenumService {

	@Resource
	private ReleasenumMapper releasenumMapper;
	@Resource
	private DoctorMapper doctorMapper;
	
	@Resource
	private DoctorService doctorService;
	@Resource
	private OutpatientService outpatientService;
	private ReleasenumExample releasenumExample;
	private DoctorExample doctorExample;
	
	@Override
	public int insert(Releasenum record) {
		String id = UUID.randomUUID().toString();
		record.setRealseid(id);
		record.setDoctorid("651dcb6c-76c8-4dde-9ae8-2a0b99885535"); //医生主键
		record.setPrice(10.0);
		record.setDate(null);
		record.setRemark("备注信息:杨红专家医生");
		record.setIssuccess(null);
		record.setIsfamilynum(null);
		int i = releasenumMapper.insert(record);
		return i;
	}

	@Override
	public List<Releasenum> selectByExample() {
		releasenumExample = new ReleasenumExample();
		releasenumExample.createCriteria().andDoctoridEqualTo(null);
		List<Releasenum> list = releasenumMapper.selectByExample(releasenumExample);
 		return list;
	}

	@Override
	public List<Releasenum> selectByPage(int page, int rows) {
		releasenumExample = new ReleasenumExample();
		PageHelper.startPage(page, rows);
		List<Releasenum> list = releasenumMapper.selectByExample(releasenumExample);
		return list;
	}

	@Override
	public int updateByPrimaryKeySelective(Releasenum record) {
		int i = releasenumMapper.updateByPrimaryKeySelective(record);
		return i;
	}

	@Override
	public int deleteByExample() {
		releasenumExample = new ReleasenumExample();
		releasenumExample.createCriteria().andRealseidEqualTo("");
		int i = releasenumMapper.deleteByExample(releasenumExample);
		return i;
	}

	@Override
	public Releasenum getReleasenumDetailsById(String releasenumId) throws Exception {
		// TODO Auto-generated method stub
		releasenumExample = new ReleasenumExample();
		releasenumExample.createCriteria().andRealseidEqualTo(releasenumId);
		return releasenumMapper.selectByExample(releasenumExample).get(0);
	}
	
	@Override
	public Releasenum getAlreadyReleasednumDetailsById(String releasenumId) throws Exception {
		// TODO Auto-generated method stub
		releasenumExample = new ReleasenumExample();
		releasenumExample.createCriteria().andRealseidEqualTo(releasenumId).andIssuccessEqualTo((byte)1);
		 List<Releasenum> releaseList = releasenumMapper.selectByExample(releasenumExample);
		 if(releaseList!=null&&!releaseList.isEmpty()){
			 return releaseList.get(0);
		 }
		return null;
	}
	@Override
	public List<AppointBaseInfoDTO> getAppointBaseInfoDTOByDoctorId(String doctorId, String week) throws Exception{
		doctorExample = new DoctorExample();
		doctorExample.createCriteria().andDoctoridEqualTo(doctorId);
		Doctor doc = new Doctor();
		doc = doctorService.getDoctorDetailInfo(doctorId);
		Outpatient outpatient = outpatientService.getOutpatientDetailsById(doc.getOutpatientid());
		
		releasenumExample = new ReleasenumExample();
		releasenumExample.setOrderByClause("date asc");
		releasenumExample.createCriteria().andDoctoridEqualTo(doctorId).andWeekEqualTo(week).andIssuccessEqualTo((byte)1);
		List<Releasenum> releasenumAmList = new LinkedList<>();
		releasenumAmList = releasenumMapper.selectByExample(releasenumExample);
		
		AppointBaseInfoDTO appointBaseInfoDTO ;
		List<AppointBaseInfoDTO> appointBaseInfoDTOList = new LinkedList<>();
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if(releasenumAmList!=null&&!releasenumAmList.isEmpty()){
			for(Releasenum re:releasenumAmList){
			appointBaseInfoDTO = new AppointBaseInfoDTO();
			appointBaseInfoDTO.setDate(format.format(re.getDate()));
			appointBaseInfoDTO.setReleasenumid(re.getRealseid());
			appointBaseInfoDTO.setAmpm(re.getAmpm());
			appointBaseInfoDTO.setWeek(re.getWeek());
			appointBaseInfoDTO.setPrice(re.getPrice());
			appointBaseInfoDTO.setOutpatientId(outpatient.getOutpatientid());
			appointBaseInfoDTO.setOutpatientName(outpatient.getOutpatientname());
			appointBaseInfoDTO.setSectionId(outpatient.getSectionid());
			appointBaseInfoDTO.setSectionName(outpatient.getSectionname());
			appointBaseInfoDTO.setDoctorId(doctorId);
			appointBaseInfoDTOList.add(appointBaseInfoDTO);
			}
		}
		
		if(appointBaseInfoDTOList!=null){
			return appointBaseInfoDTOList;
		}
		return null;
	}

}
