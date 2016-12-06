package cn.edu.bupt.springmvc.web.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.edu.bupt.springmvc.web.dao.AppointmentMapper;
import cn.edu.bupt.springmvc.web.dto.AppointmentRecordDTO;
import cn.edu.bupt.springmvc.web.model.Appointment;
import cn.edu.bupt.springmvc.web.model.AppointmentExample;
import cn.edu.bupt.springmvc.web.model.Releasenum;
import cn.edu.bupt.springmvc.web.service.AppointmentService;
import cn.edu.bupt.springmvc.web.service.ReleasenumService;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Resource
	private AppointmentMapper appointmentMapper;
	
	private AppointmentExample appointmentExample;
	
	@Resource
	private ReleasenumService releasenumService;
	
	@Override
	public int insert(Appointment record) {
		int i = appointmentMapper.insert(record);
		return i;
	}

	@Override
	public List<Appointment> selectByExample() {
		appointmentExample = new AppointmentExample();
		appointmentExample.createCriteria().andDoctornameEqualTo("");
		List<Appointment> list = appointmentMapper.selectByExample(appointmentExample);
		return list;
	}

	@Override
	public List<Appointment> selectByPage(int page, int rows) {
		appointmentExample = new AppointmentExample();
		PageHelper.startPage(page, rows);
		List<Appointment> list = appointmentMapper.selectByExample(appointmentExample);
		return list;
	}

	@Override
	public int updateByPrimaryKeySelective(Appointment record) {
		record.setId("");
		record.setDoctorid(null);
		record.setCustomerid(null);
		record.setRealseid(null);
		record.setIdcard(null);
		record.setType((byte)1);
		record.setSectionname("");
		record.setAppointdate(null);
		record.setCost(100.0);
		record.setDoctorname("");
		record.setTelephone("010-23445456");
		record.setSectionarea(null);
		int i = appointmentMapper.updateByPrimaryKeySelective(record);
		return i;
	}

	@Override
	public int deleteByExample() {
		appointmentExample = new AppointmentExample();
		appointmentExample.createCriteria().andIdcardEqualTo("");
		int i = appointmentMapper.deleteByExample(appointmentExample);
		return i;
	}

	@Override
	public List<AppointmentRecordDTO> getAppointRecordDTOByCustomerId(String customreId) throws Exception{
		appointmentExample = new AppointmentExample();
		appointmentExample.setOrderByClause("appointdate ASC");
		appointmentExample.createCriteria().andCustomeridEqualTo(customreId);
		List<Appointment> list = appointmentMapper.selectByExample(appointmentExample);
		List<AppointmentRecordDTO> apList = new ArrayList<>();
		AppointmentRecordDTO appointmentRecordDTO = null;
		if(list!=null&&!list.isEmpty()){
			for(Appointment ap : list){
				appointmentRecordDTO = new AppointmentRecordDTO();
				String relaeanumid = ap.getRealseid();
				//得到已经预约的号
				Releasenum releasenum = releasenumService.getAlreadyReleasednumDetailsById(relaeanumid);
				if(releasenum==null){
					continue;
				}
				appointmentRecordDTO.setAmpm(releasenum.getAmpm());
				appointmentRecordDTO.setWeek(releasenum.getWeek());
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				
				appointmentRecordDTO.setAppointdate(format.format(ap.getAppointdate()));
				appointmentRecordDTO.setCost(ap.getCost());
				appointmentRecordDTO.setCustomerid(ap.getCustomerid());
				appointmentRecordDTO.setCustomername(ap.getCustomername());
				appointmentRecordDTO.setDoctorid(ap.getDoctorid());
				appointmentRecordDTO.setDoctorname(ap.getDoctorname());
				appointmentRecordDTO.setId(ap.getId());
				appointmentRecordDTO.setIdcard(ap.getIdcard());
				appointmentRecordDTO.setOutpatientname(ap.getOutpatientname());
				appointmentRecordDTO.setRealseid(ap.getRealseid());
				appointmentRecordDTO.setSectionarea(ap.getSectionarea());
				appointmentRecordDTO.setSectionname(ap.getSectionname());
				appointmentRecordDTO.setTelephone(ap.getTelephone());
				appointmentRecordDTO.setType(ap.getType());
				
				apList.add(appointmentRecordDTO);
				
			}
			
		}
		if(apList!=null&&!apList.isEmpty()){
			return apList;
		}
		return null;
	}

}
