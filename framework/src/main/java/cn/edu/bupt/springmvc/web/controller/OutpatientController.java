package cn.edu.bupt.springmvc.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.edu.bupt.springmvc.core.generic.GenericController;
import cn.edu.bupt.springmvc.web.model.Outpatient;
import cn.edu.bupt.springmvc.web.service.OutpatientService;

@Controller
@RequestMapping(value="outpatient")
public class OutpatientController extends GenericController {

	@Resource
	private OutpatientService outpatientService;
	
	@RequestMapping(value="insert")
	public void insert(HttpServletRequest request, HttpServletResponse response){
		Outpatient outpatient = new Outpatient();
		int i = outpatientService.insert(outpatient);
		if (i>0) {
			renderSuccessString(response, outpatient);
		} else {
			renderErrorString(response, "insert outpatient failed!");
		}
	}
	
	/**
	 * 
	 * @author qjk
	 * @param request
	 * @param response
	 * 
	 * 请求方法是post，根据科室名称进行门诊的查询显示
	 */
	@RequestMapping(value="selectByExample",method=RequestMethod.POST)
	public void select(HttpServletRequest request, HttpServletResponse response){
		String sectionName = request.getParameter("data");
		System.out.println(sectionName);
		List<Outpatient> list = outpatientService.selectBySectionName(sectionName);
		if(list!=null){
			renderSuccessString(response, list);
		} else {
			renderErrorString(response, "select outpatient no data");
		}
		
	}
	
	@RequestMapping(value="update")
	public void update(HttpServletRequest request, HttpServletResponse response){
		Outpatient outpatient = new Outpatient();
		int i = outpatientService.updateByPrimaryKeySelective(outpatient);
		if (i>0) {
			renderSuccessString(response, "update outpatient success!");
		} else {
			renderErrorString(response, "update outpatient failed!");
		}
	}
	
	@RequestMapping(value="delete")
	public void delete(HttpServletRequest request,HttpServletResponse response){
		int i = outpatientService.deleteByExample();
		if (i>0) {
			renderSuccessString(response, "delete outpatient record success!");
		} else {
			renderErrorString(response, "delete outpatient record failed!");
		}
	}
}


