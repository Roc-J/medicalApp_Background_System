package cn.edu.bupt.springmvc.web.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.edu.bupt.springmvc.web.dao.CustomerMapper;
import cn.edu.bupt.springmvc.web.model.Customer;
import cn.edu.bupt.springmvc.web.model.CustomerExample;
import cn.edu.bupt.springmvc.web.service.CustomerService;

@Service
public class CustomerServicelmpl implements CustomerService {

	@Resource
	private CustomerMapper customerMapper;
	
	private CustomerExample customerExample;
	
	@Override
	public int insert(Customer record) {
		String id = UUID.randomUUID().toString();
		record.setCustomerid(id);
		record.setPichead("");
		record.setCustomername("");
		record.setAge(34);
		record.setNickname("");
		record.setSex(1);
		record.setIdcard("");
		record.setPhone("010-23453666");
		record.setCustomeradrr("");
		record.setPassword("");
		record.setRemarks("");
		record.setIsmarried((byte)1);
		record.setJob("");
		record.setNation("");
		record.setBirthplace("");
		record.setResidence("");
		record.setWorkplace("");
		int i = customerMapper.insert(record);
		return i;
	}

	@Override
	public List<Customer> selectByExample() {
		customerExample = new CustomerExample();
		customerExample.createCriteria().andCustomernameEqualTo("");
		List<Customer> list = customerMapper.selectByExample(customerExample);
		return list;
	}

	@Override
	public List<Customer> selectByPage(int page, int rows) {
		customerExample = new CustomerExample();
		PageHelper.startPage(page, rows);
		List<Customer> list = customerMapper.selectByExample(customerExample);
		return list;
	}

	@Override
	public int updateByPrimaryKeySelective(Customer record) {
		record.setCustomerid("");
		record.setPichead("");
		record.setCustomername("");
		record.setAge(34);
		record.setNickname("");
		record.setSex(1);
		record.setIdcard("");
		record.setPhone("010-23453666");
		record.setCustomeradrr("");
		record.setPassword("");
		record.setRemarks("");
		record.setIsmarried((byte)1);
		record.setJob("");
		record.setNation("");
		record.setBirthplace("");
		record.setResidence("");
		record.setWorkplace("");
		int i = customerMapper.updateByPrimaryKeySelective(record);
		return i;
	}

	@Override
	public int deleteByExample() {
		customerExample = new CustomerExample();
		customerExample.createCriteria().andCustomernameEqualTo("");
		int i = customerMapper.deleteByExample(customerExample);
		return i;
	}

}