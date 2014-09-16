package com.runmit.uc.management.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.runmit.uc.core.domain.Admin;
import com.runmit.uc.core.service.AdminService;
import com.runmit.uc.management.domain.Address;
import com.runmit.uc.management.domain.Company;
import com.runmit.uc.management.domain.User;

/**
 * 
 * @author TianLiang
 */
@RestController
@RequestMapping(value = "/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	// @RequestMapping(value = "/admin", method = RequestMethod.GET)
	// public String siteView() {
	// return "admin/admin";
	// }
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Admin getAdmin(@PathVariable("id") Integer id) {
		return adminService.getAdmin(id);
	}

	@RequestMapping(value = "test/{id}", method = RequestMethod.GET)
	public User getAdmin2(@PathVariable String id, @RequestParam String name) {
		Company company = new Company();
		company.setId(100);
		company.setName("RUNMIT");
		User user = new User();
		user.setId(99);
		user.setName("SUN");
		user.setCompany(company);

		Address address1 = new Address();
		address1.setAd("ad1");
		address1.setCode(1);
		Address address2 = new Address();
		address2.setAd("ad2");
		address2.setCode(2);
		List<Address> list = new ArrayList<Address>();
		// list.add(address1);
		// list.add(address2);
		user.setAds(list);
		return user;
	}

	@RequestMapping(value = "address", method = RequestMethod.POST)
	public void createAddress(@RequestBody Address address) {
		System.out.println("address code=" + address.getCode());
		System.out.println("address name=" + address.getAd());
	}

}
