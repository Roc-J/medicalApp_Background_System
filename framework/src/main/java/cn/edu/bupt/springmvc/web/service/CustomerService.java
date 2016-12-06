package cn.edu.bupt.springmvc.web.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.edu.bupt.springmvc.web.model.Customer;
import cn.edu.bupt.springmvc.web.model.CustomerExample;

public interface CustomerService {
	
	int insert(Customer record);

	List<Customer> selectByExample();
	
	List<Customer> selectByPage(int page,int rows);
	
	int updateByPrimaryKeySelective(@Param("record") Customer record);
	
	int deleteByExample();
	
	public Customer getCustoemrDetailsByCustomerId(String doctorId) throws Exception;
	
	public Customer loginVerifyByUserName(String userName) throws Exception;
}
