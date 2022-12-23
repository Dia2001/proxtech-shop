package com.proxtechshop.admincontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.proxtechshop.common.Constants;
import com.proxtechshop.entities.Customer;
import com.proxtechshop.services.CustomerService;

@Controller
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@RequestMapping(Constants.ADMIN_CUSTOMERSMNG_PATH)
	public String getCustomer(Model model) {
		return findPaginated(1, "fullName", "asc", model);
	}
	
	@RequestMapping(Constants.ADMIN_CUSTOMERSMNG_PATH+"/delete")
	String RemoveMember(@RequestParam("id") String id,Model model) {
		if(customerService.remove(id)) {
			model.addAttribute("flag",true);
			model.addAttribute("msg","Xóa thành công!");
		}else {
			model.addAttribute("flag",false);
			model.addAttribute("msg","Xóa không thành công!");
		}
		return findPaginated(1, "fullName", "asc", model);
	}
	@RequestMapping(Constants.ADMIN_CUSTOMERSMNG_PATH+"/filter")
	public String searchMember(@RequestParam("search") String search, Model model) {
		if(search=="")
		{
			model.addAttribute("flag",false);
			model.addAttribute("msg","Không để trống phần tìm kiếm");
			model.addAttribute("search",null);
			return findPaginated(1, "fullName", "asc", model);
		}
		else
		{
			model.addAttribute("search",search);
			return findPaginated(1, "fullName", "asc", model);
		}
		
	}
	//pagination
		@RequestMapping(Constants.ADMIN_CUSTOMERSMNG_PATH+"/page/{pageNo}")
		public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
				@RequestParam("sortField") String sortField,
				@RequestParam("sortDir") String sortDir,
				Model model) {
			int pageSize = 4;
			//handler search engine
			Page<Customer> page;
			if(model.getAttribute("search")==null)
				page = customerService.findPaginated(pageNo, pageSize, sortField, sortDir);
			else
			{
				String search=model.getAttribute("search").toString();
				page=customerService.FilterAndPaginated(search, pageNo, pageSize, sortField, sortDir);
				if(page.getContent().size()==0)
				{
					model.addAttribute("flag",false);
					model.addAttribute("msg","Không tìm thấy "+search);
				}
				else
				{
					model.addAttribute("flag",true);
					model.addAttribute("msg","Tìm thấy "+page.getContent().size()+" kết quả của "+search);
				}
			}
			//end
			
			List<Customer> listCustomers = page.getContent();
			
			model.addAttribute("currentPage", pageNo);
			model.addAttribute("totalPages", page.getTotalPages());
			model.addAttribute("totalItems", page.getTotalElements());
			model.addAttribute("sortField", sortField);
			model.addAttribute("sortDir", sortDir);
			model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
			model.addAttribute("customer",listCustomers);
			
			return Constants.ADMIN_CUSTOMERSMNG_VIEW;
	}
}
