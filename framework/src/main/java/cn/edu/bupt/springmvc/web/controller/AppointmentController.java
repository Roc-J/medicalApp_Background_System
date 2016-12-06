package cn.edu.bupt.springmvc.web.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.edu.bupt.springmvc.core.generic.GenericController;
import cn.edu.bupt.springmvc.web.dto.AppointBaseInfoDTO;
import cn.edu.bupt.springmvc.web.dto.AppointmentRecordDTO;
import cn.edu.bupt.springmvc.web.model.Appointment;
import cn.edu.bupt.springmvc.web.model.Customer;
import cn.edu.bupt.springmvc.web.model.Doctor;
import cn.edu.bupt.springmvc.web.model.Outpatient;
import cn.edu.bupt.springmvc.web.model.Releasenum;
import cn.edu.bupt.springmvc.web.model.Section;
import cn.edu.bupt.springmvc.web.service.AppointmentService;
import cn.edu.bupt.springmvc.web.service.CustomerService;
import cn.edu.bupt.springmvc.web.service.DoctorService;
import cn.edu.bupt.springmvc.web.service.OutpatientService;
import cn.edu.bupt.springmvc.web.service.ReleasenumService;
import cn.edu.bupt.springmvc.web.service.SectionService;

@Controller
@RequestMapping(value = "appointment")
public class AppointmentController extends GenericController {

	@Resource
	private AppointmentService appointmentService;
	@Resource
	private DoctorService doctorService;
	@Resource
	private CustomerService customerService;
	@Resource
	private OutpatientService outpatientService;
	@Resource
	private ReleasenumService releasenumService;
	@Resource
	private SectionService sectionService;

	@RequestMapping(value = "insert")
	public void insert(HttpServletRequest request, HttpServletResponse response) {
		Appointment record = new Appointment();
		int i = appointmentService.insert(record);
		if (i > 0) {
			renderSuccessString(response, record);
		} else {
			renderErrorString(response, "insert appointment failed!");
		}
	}

	@RequestMapping(value = "selectByExample")
	public void select(HttpServletRequest request, HttpServletResponse response) {
		List<Appointment> list = appointmentService.selectByExample();
		if (list != null) {
			renderSuccessString(response, list);
		} else {
			renderErrorString(response, "select appointment no data");
		}

	}
    /**根据病人id得到病人的预约记录
     * @author lhh
     * @param request
     * @param response
     */
	@RequestMapping(value = "getAppointmentDetailsByCustomerId", method = RequestMethod.POST)
	public void getAppointmentDetailsByCustomerId(HttpServletRequest request, HttpServletResponse response) {
		String customreId = request.getParameter("customerId");
		List<AppointmentRecordDTO> list;
		try {
			list = appointmentService.getAppointRecordDTOByCustomerId(customreId);
			if (list != null&&!list.isEmpty()) {
				renderSuccessString(response, list);
			} else {
				renderErrorString(response, "no data");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			renderErrorString(response, "no data");
		}
		

	}

	@RequestMapping(value = "update")
	public void update(HttpServletRequest request, HttpServletResponse response) {
		Appointment record = new Appointment();
		int i = appointmentService.updateByPrimaryKeySelective(record);
		if (i > 0) {
			renderSuccessString(response, "update appointment success!");
		} else {
			renderErrorString(response, "update appointment failed!");
		}
	}

	@RequestMapping(value = "delete")
	public void delete(HttpServletRequest request, HttpServletResponse response) {
		int i = appointmentService.deleteByExample();
		if (i > 0) {
			renderSuccessString(response, "delete appointment record success!");
		} else {
			renderErrorString(response, "delete appointment record failed!");
		}
	}

	/**@author lhh
	 * 得到详细的预约信息
	 */
	@RequestMapping(value = "InsertAppointmentDetails", method = RequestMethod.POST)
	public void getAppointmentDetails(HttpServletRequest request, HttpServletResponse response) {

		String customerId = request.getParameter("customerId");
		String doctorId = request.getParameter("doctorId");
		String releasenumId = request.getParameter("releasenumid");
		Appointment appointment = new Appointment();
		try {
			Customer customer = customerService.getCustoemrDetailsByCustomerId(customerId);
			Doctor doctor = doctorService.getDoctorDetailInfo(doctorId);
			Outpatient outpatient = outpatientService.getOutpatientDetailsById(doctor.getOutpatientid());
			Releasenum releasenum = releasenumService.getReleasenumDetailsById(releasenumId);
			releasenum.setIssuccess((byte)1);
			releasenumService.updateByPrimaryKeySelective(releasenum);
			Section section = sectionService.getSectionById(outpatient.getSectionid());
			String uuid = UUID.randomUUID().toString();
			appointment.setId(uuid);
			appointment.setDoctorid(doctor.getDoctorid());
			appointment.setCustomerid(customer.getCustomerid());
			appointment.setRealseid(releasenum.getRealseid());
			appointment.setIdcard(customer.getIdcard());
			appointment.setSectionname(outpatient.getSectionname());
			appointment.setOutpatientname(outpatient.getOutpatientname());
			appointment.setTelephone(outpatient.getTelephone());
			appointment.setSectionarea(section.getSectionloc());
			appointment.setAppointdate(releasenum.getDate());
			appointment.setCost(releasenum.getPrice());
			int i = appointmentService.insert(appointment);
			if (i > 0) {
				renderSuccessString(response,"SUCCESS");
			} else {
				renderErrorString(response, "insert appointment failed!");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			renderErrorString(response, "insert appointment failed!");
			e.printStackTrace();

		}

	}

	/**
	 * 保存预约信息
	 * @param request
	 * @param response
	 * 
	 */
	@RequestMapping(value = "insertDetails")
	public void saveAppointmentDetails(HttpServletRequest request, HttpServletResponse response) {
		Appointment record = (Appointment) request.getAttribute("appointment");
		/* record.setId(UUID.randomUUID().toString()); */
		int i = appointmentService.insert(record);
		if (i > 0) {
			renderSuccessString(response, record);
		} else {
			renderErrorString(response, "insert appointment failed!");
		}
	}

	/*	
		*//**
			 * 根据医生id得到排班表之后的预约的基本信息界面
			 * 
			 * @author lhh
			 * @param request
			 * @param response
			 */
	@RequestMapping(value = "getAppointBaseInfoByDoctorId", method = RequestMethod.POST)
	public void getAppointBaseInfoByDoctorId(HttpServletRequest request, HttpServletResponse response) {

		String doctorId = request.getParameter("doctorId");
		String week = request.getParameter("week");
		if (doctorId != null && week != null && !doctorId.equals("") && !week.equals("")) {
			try {
				List<AppointBaseInfoDTO> appointDtoList = releasenumService.getAppointBaseInfoDTOByDoctorId(doctorId,
						week);
				Map<String, List<AppointBaseInfoDTO>> map = new HashMap<>();
				List<AppointBaseInfoDTO> am = new LinkedList<>();
				List<AppointBaseInfoDTO> pm = new LinkedList<>();
				if (appointDtoList != null && !appointDtoList.isEmpty()) {
					for (AppointBaseInfoDTO dto : appointDtoList) {
						if (dto.getAmpm().equals("上午")) {
							am.add(dto);
						} else {
							pm.add(dto);
						}
					}
				}
				map.put("上午", am);
				map.put("下午", pm);
				renderSuccessString(response, map);
			} catch (Exception exception) {
				exception.printStackTrace();
				renderErrorString(response, "can't catch appointDto infor!");
			}
		} else {
			renderErrorString(response, "doctorId or week is null!");
		}

	}
	/**根据放号id进行退号
	 * @author lhh
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "withdrawlAppointmentByReleasenumId", method = RequestMethod.POST)
	public void withdrawlAppointmentByAppointId(HttpServletRequest request, HttpServletResponse response) {
		String releasenumId = request.getParameter("releasenumId");
		try {
			if(releasenumId!=null&&!releasenumId.equals("")){
			Releasenum releasenum = releasenumService.getReleasenumDetailsById(releasenumId);
			releasenum.setIssuccess((byte)0);
			releasenumService.updateByPrimaryKeySelective(releasenum);
			renderSuccessString(response, "withdraw releasenum success");
			}else{
				renderErrorString(response, "withdraw releasenum failure");
			}
		} catch (Exception e) {
			renderErrorString(response, "withdraw releasenum failure");
		}
	}
}
