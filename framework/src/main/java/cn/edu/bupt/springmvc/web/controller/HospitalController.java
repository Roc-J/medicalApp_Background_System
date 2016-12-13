package cn.edu.bupt.springmvc.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.edu.bupt.springmvc.core.generic.GenericController;
import cn.edu.bupt.springmvc.web.model.Hospital;
import cn.edu.bupt.springmvc.web.service.HospitalService;

@Controller
@RequestMapping(value="hospital")
public class HospitalController extends GenericController {

	@Resource 
	private HospitalService hospitalService;
	
	@RequestMapping(value="insert")
	public void insert(HttpServletRequest request,HttpServletResponse response,Hospital record){
		int i = hospitalService.insert(record);
		if (i>0) {
			renderSuccessString(response, record);
		} else {
			renderErrorString(response, "insert table Hospital failed!");
		}
	}
	
	@RequestMapping(value="select")
	public void select(HttpServletRequest request, HttpServletResponse response){
		List<Hospital> list = hospitalService.selectByExample();
		if ( list!=null) {
			renderSuccessString(response, list);
		} else {
			renderErrorString(response, "select from Hospital no data!");
		}
	}
	
	/**
	 * 
	 * 因为医院是默认的，所以这里是设定了医院的ID
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="searchHosInfo")
	public void searchHosInfo(HttpServletRequest request, HttpServletResponse response){
		Hospital record = hospitalService.searchHosInfo("921ca47b-fee2-419e-8ac9-9ae7f1435d26");
		if (record!=null) {
			renderSuccessString(response, record);
		} else {
			renderErrorString(response, "select from Hospital no data!");
		}
	}
	
	@RequestMapping(value="update")
	public void update(HttpServletRequest request, HttpServletResponse response){
		Hospital hospital = new Hospital();
		int i = hospitalService.updateByPrimaryKeySelective(hospital);
		if (i>0) {
			renderSuccessString(response, hospital);
		} else {
			renderSuccessString(response, "update table Hospital failed!");
		}
	}
	
	@RequestMapping(value="delete")
	public void delete(HttpServletRequest request, HttpServletResponse response){
		int i = hospitalService.deleteByExample();
		if (i>0) {
			renderSuccessString(response, "delete table Hospital record");
		} else {
			renderErrorString(response, "delete table Hospital error!");
		}
	}
	
	/**
	 * 根据医院id获得医院和医院所包含的科室的信息
	 * 
	 * @param reqest
	 * @param response
	 * @param hospitalId
	 * @return
	 */
	@RequestMapping(value = "getHospitalSectionList", method = RequestMethod.GET)
	public void getHospitalAndSectonsInfoList(HttpServletRequest reqest, HttpServletResponse response) {

		String hospitalId = reqest.getParameter("hosId");
		
		if (hospitalId != null && !"".equals(hospitalId)) {
			try {
				List<Hospital> hospitalList = hospitalService.getHospitalSectionList(hospitalId);
				if (hospitalList != null) {
					renderSuccessString(response, hospitalList);
				} else {
					renderErrorString(response, "obtain info error!");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				renderErrorString(response, "obtain info error!");
				e.printStackTrace();
			}
		} else {
			renderErrorString(response, "illegalArgument!");
		}

	}
}
