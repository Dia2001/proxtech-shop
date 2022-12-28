package com.proxtechshop.admincontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.proxtechshop.common.Constants;
import com.proxtechshop.services.OrderService;
import com.proxtechshop.viewmodels.OrderViewModel;

@Controller
public class OrderMngController {
	
	@Autowired
	private OrderService orderService;
	
	//list
	@RequestMapping(Constants.ADMIN_ORDERMNG_PATH)
	public String getListOrders(Model model) {
		return findPaginated(1, "createdDate", "asc", model);
	}
	
	//detail
	@RequestMapping(Constants.ADMIN_ORDERMNG_PATH+"/detail/{id}")
	public ModelAndView detailOrder(Model model, @PathVariable(value = "id") String id)
	{
		ModelAndView mav = new ModelAndView(Constants.ADMIN_ORDERMNG_VIEW_PATH);
		System.out.println(id);
		OrderViewModel order=orderService.getOneOrderByCustomer(id);
		mav.addObject("oneOrder",order);
		mav.addObject("orderStatuss",orderService.loadAddOrderStatus());
		return mav;
	}
	//filter
	@RequestMapping(Constants.ADMIN_ORDERMNG_PATH+"/filter")
	public String fiterOrders(@RequestParam("search")String search,@RequestParam("order_status")String orderStatus,Model model) {
		if(search!=""||orderStatus!="")
		{
			model.addAttribute("search",search);
			model.addAttribute("status",orderStatus);
			model.addAttribute("anchor",true);
		}
		else
		{
			model.addAttribute("anchor", null);
			model.addAttribute("msg", "Không để trống phần tìm kiếm");
		}
		return findPaginated(1, "createdDate", "asc", model);
	}
	//pagination
	@RequestMapping(Constants.ADMIN_ORDERMNG_PATH+"/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 3;
		
		// handler search engine
		Page<OrderViewModel> page;
		if(model.getAttribute("anchor")==null)
		{
			page = orderService.paginated(pageNo, pageSize, sortField, sortDir);			
		}else {
			String search=model.getAttribute("search").toString();
			String status=model.getAttribute("status").toString();
			page=orderService.FilterAndPaginated(search, status, pageNo, pageSize, sortField, sortDir);
			if (page.getContent().size() == 0) {
				model.addAttribute("flag", false);
				model.addAttribute("msg", "Không tìm thấy " + search);
			} else {
				model.addAttribute("flag", true);
				model.addAttribute("msg", "Tìm thấy " + page.getTotalElements() + " kết quả của " + search);
			}
		}
		
		List<OrderViewModel> listOrders = page.getContent();
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("orders",listOrders);
		model.addAttribute("orderStatuss",orderService.loadAddOrderStatus());
		return Constants.ADMIN_ORDERMNG_VIEW;
}
	
}
