package cn.edu.bupt.springmvc.web.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.edu.bupt.springmvc.web.dto.AppointmentRecordDTO;
import cn.edu.bupt.springmvc.web.model.Appointment;
import cn.edu.bupt.springmvc.web.model.AppointmentExample;

public interface AppointmentService {

	int insert(Appointment record);
	
	List<Appointment> selectByExample();
	
	List<Appointment> selectByPage(int page,int rows);
	
	int updateByPrimaryKeySelective(@Param("record") Appointment record);
	
	int deleteByExample();

	List<AppointmentRecordDTO> getAppointRecordDTOByCustomerId(String customreId) throws Exception;

}
