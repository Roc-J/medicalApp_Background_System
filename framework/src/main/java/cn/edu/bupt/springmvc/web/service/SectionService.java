package cn.edu.bupt.springmvc.web.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.edu.bupt.springmvc.web.model.Doctor;
import cn.edu.bupt.springmvc.web.model.Section;

public interface SectionService {

	int insert(Section record);
	
	List<Section> selectByExample();
	
	List<Section> selectByPage(int page,int rows);
	
	int updateByPrimaryKeySelective(@Param("record") Section record);
		
	int deleteByExample();
	
	public List<Doctor> getSectionDoctorList(String sectionId)throws Exception;
	
	Section getSectionById(String sectionId);
	/**
	 * 根据科室名称查找科室门诊信息
	 * 
	 * @author qjk
	 * @param sectionName
	 * 
	 * */
	Section selectBySectionName(String sectionName);
	Section searchSectionInfo(String sectionId);
}
