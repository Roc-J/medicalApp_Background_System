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
		int i = customerMapper.insert(record);
		return i;
	}

	@Override
	public List<Customer> selectByExample() {
		customerExample = new CustomerExample();
		customerExample.createCriteria().andCustomernameEqualTo("秦君");
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
		record.setIsmarried((byte) 1);
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

	@Override
	public Customer getCustoemrDetailsByCustomerId(String customerId) throws Exception {
		customerExample = new CustomerExample();
		customerExample.createCriteria().andCustomeridEqualTo(customerId);
		List<Customer> customerList = customerMapper.selectByExample(customerExample);
		Customer customer = new Customer();
		if (customerList != null) {
			customer = customerList.get(0);
		}
		return customer;
	}

	@Override
	public Customer loginVerifyByUserName(String userName) throws Exception {
		customerExample = new CustomerExample();
		customerExample.createCriteria().andCustomernameEqualTo(userName);
		Customer customer = new Customer();
		List<Customer> customerList = customerMapper.selectByExample(customerExample);
		if (customerList != null) {
			customer = customerList.get(0);
			return customer;
		}
		return null;
	}

}
