package cn.edu.bupt.springmvc.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.synth.SynthScrollBarUI;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.edu.bupt.springmvc.core.generic.GenericController;
import cn.edu.bupt.springmvc.web.model.Doctor;
import cn.edu.bupt.springmvc.web.model.Releasenum;
import cn.edu.bupt.springmvc.web.service.DoctorService;

@Controller
@RequestMapping(value="doctor")
public class DoctorController extends GenericController {
	
	@Resource
	private DoctorService doctorService;
	
	@RequestMapping(value="insert")
	public void insert(HttpServletRequest request, HttpServletResponse response, Doctor doctor){
		int i = doctorService.insert(doctor);
		if (i>0) {
			renderSuccessString(response, doctor);
		} else {
			renderErrorString(response, "insert doctor failed!");
		}
	}
	
	@RequestMapping(value="selectByExample")
	public void select(HttpServletRequest request, HttpServletResponse response){
		List<Doctor> list = doctorService.selectByExample();
		if(list!=null){
			renderSuccessString(response, list);
		} else {
			renderErrorString(response, "select doctor no data");
		}
		
	}
	
	/**
	 * @author qjk
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="selectBySection", method=RequestMethod.POST)
	public void selectBySection(HttpServletRequest request, HttpServletResponse response){
		String sectionName = request.getParameter("data");
		List<Doctor> list = doctorService.selectBySection(sectionName);
		if(list!=null){
			renderSuccessString(response, list);
		} else {
			renderErrorString(response, "select doctor no data");
		}
		
	}
	
	/**
	 * @author qjk
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="searchDoctorInfo", method=RequestMethod.POST)
	public void searchDoctorInfo(HttpServletRequest request, HttpServletResponse response){
		String doctorId = request.getParameter("data");
		//System.out.println(doctorId);
		Doctor record = doctorService.searchDoctorInfo(doctorId);
		//System.out.println(record);
		if(record!=null){
			renderSuccessString(response, record);
		} else {
			renderErrorString(response, "select doctor no data");
		}
		
	}
	
	
	@RequestMapping(value="update")
	public void update(HttpServletRequest request, HttpServletResponse response){
		Doctor doctor = new Doctor();
		int i = doctorService.updateByPrimaryKeySelective(doctor);
		if (i>0) {
			renderSuccessString(response, "update doctor success!");
		} else {
			renderErrorString(response, "update doctor failed!");
		}
	}
	
	@RequestMapping(value="delete")
	public void delete(HttpServletRequest request,HttpServletResponse response){
		int i = doctorService.deleteByExample();
		if (i>0) {
			renderSuccessString(response, "delete doctor record success!");
		} else {
			renderErrorString(response, "delete doctor record failed!");
		}
	}
	
	
	/**
	 * 根据医生id查询医生的详细信息
	 * @author lhh
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "getDoctorDetailByDoctorId", method = RequestMethod.GET)
	public void getDoctorDetailByDoctorId(HttpServletRequest request, HttpServletResponse response) {
		
		String doctorId = request.getParameter("doctorId");
		
		if (doctorId != null && !"".equals(doctorId)) {
			try {
				Doctor doctor = doctorService.getDoctorDetailInfo(doctorId);
				renderSuccessString(response, doctor);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				renderErrorString(response, "can't obtain doctorDetail!");
				e.printStackTrace();
			}
		} else {
			renderErrorString(response, "NullPointException！");
		}
	}

	
	/**
	 * 根据门诊Id查询所有的医生信息和医生所对应的放号的信息
	 * @author lhh
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "getDoctorReleaseNumDetailByOutpatientId", method = RequestMethod.GET)
	public void getDoctorReleaseNumDetailByOutpatientId(HttpServletRequest request, HttpServletResponse response) {
		
		String outpatient = request.getParameter("outpatientId");
		System.out.println(outpatient);
		List<Doctor> doctorList = new ArrayList<>();
		
		List<Doctor> Mondayam = new LinkedList<>();
		List<Doctor> Tuesdayam= new LinkedList<>();
		List<Doctor> Wednesdayam = new LinkedList<>();
		List<Doctor> Thursdayam = new LinkedList<>();
		List<Doctor> Fridayam = new LinkedList<>();
		List<Doctor> Saturdayam = new LinkedList<>();
		List<Doctor> Sundayam = new LinkedList<>();
		
		List<Doctor> Mondaypm = new LinkedList<>();
		List<Doctor> Tuesdaypm= new LinkedList<>();
		List<Doctor> Wednesdaypm = new LinkedList<>();
		List<Doctor> Thursdaypm = new LinkedList<>();
		List<Doctor> Fridaypm = new LinkedList<>();
		List<Doctor> Saturdaypm = new LinkedList<>();
		List<Doctor> Sundaypm = new LinkedList<>();
		
		if (outpatient != null && !"".equals(outpatient)) {
			try {
				doctorList = doctorService.getDoctorReleaseNumByOutPatientId(outpatient);
				if(doctorList!=null){
					for(Doctor doc : doctorList){
						List<Releasenum> relesList = doc.getReleaseNumList();
						for(Releasenum re : relesList){
							Doctor doctor = new Doctor();
							doctor = getDoctorDetailsWithSingleNumber(doc);
							
							String week = re.getWeek()!=null && !re.getWeek().equals("") ? re.getWeek():"";
							String ampm = re.getAmpm()!=null && !re.getAmpm().equals("") ? re.getAmpm(): "";
							if(ampm.equals("上午")){
							switch(week){
							case "星期一" :
								if(!Mondayam.contains(doctor)){
								Mondayam.add(doctor);
								}
								break;
							case "星期二" :
								if(!Tuesdayam.contains(doctor)){
								Tuesdayam.add(doctor);
								}
							    break;
							case "星期三" : 
								if(!Wednesdayam.contains(doctor)){
								Wednesdayam.add(doctor);
								}
							    break;
							case "星期四" : 
								if(!Thursdayam.contains(doctor)){
							    Thursdayam.add(doctor);
								}
							    break;
							case "星期五" : 
								if(!Fridayam.contains(doctor)){
							    Fridayam.add(doctor);
								}
							    break;
							case "星期六" :
								if(!Saturdayam.contains(doctor)){
							    Saturdayam.add(doctor);
								}
							    break;
							case "星期日" : 
								if(!Sundayam .contains(doctor)){
							    Sundayam .add(doctor);
								}
							    break;
							}
							}else{
								switch(week){
								case "星期一" :
									if(!Mondaypm.contains(doctor)){
									Mondaypm.add(doctor);
									}
									break;
								case "星期二" :
									if(!Tuesdaypm.contains(doctor)){
									Tuesdaypm.add(doctor);
									}
								    break;
								case "星期三" : 
									if(!Wednesdaypm.contains(doctor)){
									Wednesdaypm.add(doctor);
									}
								    break;
								case "星期四" : 
									if(!Thursdaypm.contains(doctor)){
								    Thursdaypm.add(doctor);
									}
								    break;
								case "星期五" : 
									if(!Fridaypm.contains(doctor)){
								    Fridaypm.add(doctor);
									}
								    break;
								case "星期六" :
									if(!Saturdaypm.contains(doctor)){
								    Saturdaypm.add(doctor);
									}
								    break;
								case "星期日" : 
									if(!Sundaypm .contains(doctor)){
								    Sundaypm .add(doctor);
									}
								    break;
								}
							}
						}
					}
				
				}
				List<Map<String,List<Doctor>>> listAm = new ArrayList<>();
				Map<String,List<Doctor>> mapAm = new LinkedHashMap<>();
				mapAm.put("星期一", Mondayam);
				mapAm.put("星期二", Tuesdayam);
				mapAm.put("星期三", Wednesdayam);
				mapAm.put("星期四", Thursdayam);
				mapAm.put("星期五", Fridayam);
				mapAm.put("星期六", Saturdayam);
				mapAm.put("星期日", Sundayam );
				List<Map<String,List<Doctor>>> listPm = new ArrayList<>();
				Map<String,List<Doctor>> mapPm = new LinkedHashMap<>();
				mapPm.put("星期一", Mondaypm);
				mapPm.put("星期二", Tuesdaypm);
				mapPm.put("星期三", Wednesdaypm);
				mapPm.put("星期四", Thursdaypm);
				mapPm.put("星期五", Fridaypm);
				mapPm.put("星期六", Saturdaypm);
				mapPm.put("星期日", Sundaypm);
				
				listAm.add(mapAm);
				listPm.add(mapPm);
				Map<String,List<Map<String,List<Doctor>>>> maplist = new TreeMap<>();
				maplist.put("上午", listAm);
				maplist.put("下午", listPm);
				renderSuccessString(response, maplist);
			} catch (Exception e) {
				renderErrorString(response, "can't obtain doctorReleaseNumDetail!");
				e.printStackTrace();
			}
		} else {
			renderErrorString(response, "NullPointException！");
		}
	}
	Doctor getDoctorDetailsWithSingleNumber(Doctor doc){
		Doctor doctor = new Doctor();
		doctor.setDoctorid(doc.getDoctorid());
	    doctor.setDoctorname(doc.getDoctorname());
	    doctor.setPosition(doc.getPosition());
	   /* List<Releasenum> singleRelease = new ArrayList<>();
	    singleRelease.add(re);
	    doctor.setReleaseNumList(singleRelease);*/
	    if(doctor!=null){
	    	return doctor;
	    }
	    return null;
		
	}
	
}
