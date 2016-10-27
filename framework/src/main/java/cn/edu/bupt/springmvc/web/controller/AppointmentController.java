package cn.edu.bupt.springmvc.web.controller;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.edu.bupt.springmvc.core.generic.GenericController;
import cn.edu.bupt.springmvc.web.model.Appointment;
import cn.edu.bupt.springmvc.web.model.Customer;
import cn.edu.bupt.springmvc.web.model.Doctor;
import cn.edu.bupt.springmvc.web.model.Outpatient;
import cn.edu.bupt.springmvc.web.service.AppointmentService;
import cn.edu.bupt.springmvc.web.service.CustomerService;
import cn.edu.bupt.springmvc.web.service.DoctorService;
import cn.edu.bupt.springmvc.web.service.OutpatientService;

@Controller
@RequestMapping(value="appointment")
public class AppointmentController extends GenericController {

	@Resource
	private AppointmentService appointmentService;
	@Resource
	private DoctorService doctorService;
	@Resource
	private CustomerService  customerService;
	@Resource
	private OutpatientService outpatientService;
	
	@RequestMapping(value="insert")
	public void insert(HttpServletRequest request, HttpServletResponse response){
		Appointment record = new Appointment();
		int i = appointmentService.insert(record);
		if (i>0) {
			renderSuccessString(response, record);
		} else {
			renderErrorString(response, "insert appointment failed!");
		}
	}
	
	@RequestMapping(value="selectByExample")
	public void select(HttpServletRequest request, HttpServletResponse response){
		List<Appointment> list = appointmentService.selectByExample();
		if(list!=null){
			renderSuccessString(response, list);
		} else {
			renderErrorString(response, "select appointment no data");
		}
		
	}
	
	@RequestMapping(value="update")
	public void update(HttpServletRequest request, HttpServletResponse response){
		Appointment record = new Appointment();
		int i = appointmentService.updateByPrimaryKeySelective(record);
		if (i>0) {
			renderSuccessString(response, "update appointment success!");
		} else {
			renderErrorString(response, "update appointment failed!");
		}
	}
	
	@RequestMapping(value="delete")
	public void delete(HttpServletRequest request,HttpServletResponse response){
		int i = appointmentService.deleteByExample();
		if (i>0) {
			renderSuccessString(response, "delete appointment record success!");
		} else {
			renderErrorString(response, "delete appointment record failed!");
		}
	}
	
	/**
	 * 得到预约信息详情
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="getAppointmentDetails", method = RequestMethod.GET)
	public void getAppointmentDetails(HttpServletRequest request, HttpServletResponse response){
	
		String idCard = request.getParameter("idCard");
		String doctorId = request.getParameter("doctorId");
		Appointment appointment = new Appointment();
		
		try {
			
			Customer customer = customerService.getCustoemrDetailsByIdCard(idCard);
			Doctor doctor= doctorService.getDoctorDetailInfo(doctorId);
			Outpatient outpatient = outpatientService.getOutpatientDetailsById(doctor.getOutpatientid());
			String uuid = UUID.randomUUID().toString();
			appointment.setId(uuid);
			appointment.setDoctorid(doctorId);
			appointment.setCustomerid(customer.getCustomerid());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

