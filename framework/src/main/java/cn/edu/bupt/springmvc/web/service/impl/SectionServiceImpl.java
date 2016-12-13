package cn.edu.bupt.springmvc.web.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.edu.bupt.springmvc.web.dao.SectionMapper;
import cn.edu.bupt.springmvc.web.model.Doctor;
import cn.edu.bupt.springmvc.web.model.OutpatientExample;
import cn.edu.bupt.springmvc.web.model.Section;
import cn.edu.bupt.springmvc.web.model.SectionExample;
import cn.edu.bupt.springmvc.web.service.SectionService;

@Service
public class SectionServiceImpl implements SectionService {

	@Resource
	private SectionMapper sectionMapper;

	private SectionExample sectionExample;

	@Override
	public Section searchSectionInfo(String sectionId) {
		sectionExample = new SectionExample();
		sectionExample.createCriteria().andSectionidEqualTo(sectionId);
		// Section record = sectionMapper.selectByExample(sectionExample);
		return null;
	}

	@Override
	public int insert(Section record) {
		String id = UUID.randomUUID().toString();
		record.setSectionid(id);
		record.setHosid("921ca47b-fee2-419e-8ac9-9ae7f1435d26");
		record.setSectioncode("0000005");
		record.setSectionname("肾内科");
		record.setSectionloc("第一主楼八楼肾内科");
		record.setSectionpic(null);
		record.setSectionintro("科室介绍:关于北京市海淀区社区医院肾内科的简介。");
		int i = sectionMapper.insert(record);
		return i;
	}

	@Override
	public List<Section> selectByExample() {
		sectionExample = new SectionExample();
		sectionExample.createCriteria().andHosidEqualTo("921ca47b-fee2-419e-8ac9-9ae7f1435d26");
		List<Section> list = sectionMapper.selectByExample(sectionExample);
		return list;
	}

	@Override
	public Section selectBySectionName(String sectionName) {
		Section record = new Section();
		record = sectionMapper.selectBySectionName(sectionName);
		return record;
	}

	@Override
	public List<Section> selectByPage(int page, int rows) {
		sectionExample = new SectionExample();
		PageHelper.startPage(page, rows);
		List<Section> list = sectionMapper.selectByExample(sectionExample);
		return list;
	}

	@Override
	public int updateByPrimaryKeySelective(Section record) {
		record.setSectionid("");
		record.setHosid("");
		record.setSectioncode("");
		record.setSectionname("");
		record.setSectionloc("");
		record.setSectionpic("");
		int i = sectionMapper.updateByPrimaryKeySelective(record);
		return i;
	}

	@Override
	public int deleteByExample() {
		sectionExample = new SectionExample();
		sectionExample.createCriteria().andSectionnameEqualTo("");
		int i = sectionMapper.deleteByExample(sectionExample);
		return i;
	}

	@Override
	public List<Doctor> getSectionDoctorList(String sectionId) throws Exception {
		// TODO Auto-generated method stub
		return sectionMapper.selectSectionDoctorsBySectionId(sectionId);

	}

	@Override
	public Section getSectionById(String sectionId) {
		sectionExample = new SectionExample();
		sectionExample.createCriteria().andSectionidEqualTo(sectionId);
		List<Section> sectionList = sectionMapper.selectByExample(sectionExample);
		if (sectionList != null) {
			return sectionList.get(0);
		}
		return null;
	}

}
