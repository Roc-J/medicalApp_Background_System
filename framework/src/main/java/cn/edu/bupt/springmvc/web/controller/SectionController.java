package cn.edu.bupt.springmvc.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.edu.bupt.springmvc.core.generic.GenericController;
import cn.edu.bupt.springmvc.web.model.Doctor;
import cn.edu.bupt.springmvc.web.model.Section;
import cn.edu.bupt.springmvc.web.service.SectionService;

@Controller
@RequestMapping(value="section")
public class SectionController extends GenericController {

	@Resource
	private SectionService sectionService;
	
	/**
	 * @author qjk
	 * 插入科室表，给定医院id,进行在该医院下新建一条科室记录
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="insert")
	public void insert(HttpServletRequest request, HttpServletResponse response){
		//String hostId = request.getParameter("hostId");
		//该记录已经在数据库中，为新建数据方便
		//String hostId = "921ca47b-fee2-419e-8ac9-9ae7f1435d26";
		Section section = new Section();
		//section.setHosid(hostId);
		int i = sectionService.insert(section);
		if (i>0) {
			renderSuccessString(response, section);
		} else {
			renderErrorString(response, "insert section failed!");
		}
	}
	
	/**
	 * 科室选择，选择医院下所有的科室名称信息返回给前台
	 * @author qjk
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="selectAllSection")
	public void selectAllSection(HttpServletRequest request, HttpServletResponse response){
		List<Section> list = sectionService.selectByExample();
		List<Map<String, String>> result = new ArrayList<>();
		if(list!=null){		
			for(int i=0;i<list.size();i++){
				Map<String, String> item = new HashMap<>();
				item.put("sectionName", list.get(i).getSectionname());
				item.put("sectionId", list.get(i).getSectionid());
				result.add(item);
			}			
		}
		if(result!=null){
			renderSuccessString(response, result);
		}else{
			renderErrorString(response, "select section no data");
		}	
	}
	
	/**
	 * 根据科室id获取科室详细的信息
	 * @author qjk
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="searchSectionInfo")
	public void searchSectionInfo(HttpServletRequest request, HttpServletResponse response){
		String sectionId = request.getParameter("data");
		Section record = sectionService.getSectionById(sectionId);
		if (record!=null) {
			renderSuccessString(response, record);
		} else {
			renderErrorString(response, "select section info error!sorry!");
		}
	}
	
	@RequestMapping(value="update")
	public void update(HttpServletRequest request, HttpServletResponse response){
		Section section = new Section();
		int i = sectionService.updateByPrimaryKeySelective(section);
		if (i>0) {
			renderSuccessString(response, "update section success!");
		} else {
			renderErrorString(response, "update section failed!");
		}
	}
	
	@RequestMapping(value="delete")
	public void delete(HttpServletRequest request,HttpServletResponse response){
		int i = sectionService.deleteByExample();
		if (i>0) {
			renderSuccessString(response, "delete section record success!");
		} else {
			renderErrorString(response, "delete section record failed!");
		}
	}
	
	/**
	 * 根据科室主键得到科室下的所有的医生
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "getSeciontDoctorListBySectionId", method = RequestMethod.GET)
	public void getSeciontDoctorListBySectionId(HttpServletRequest request, HttpServletResponse response) {
		
		List<Doctor> doctorList = new ArrayList<>();
		String sectionId = request.getParameter("sectionId");
		
		if (sectionId != null && !"".equals(sectionId)) {
			try {
				doctorList = sectionService.getSectionDoctorList(sectionId);
				renderSuccessString(response, doctorList);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				renderErrorString(response, "can't obtain doctorList!");
				e.printStackTrace();
			}
		} else {
			renderErrorString(response, "NullPointException！");
		}

	}

}

