package com.proxtechshop.admincontrollers;

import java.io.Console;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.proxtechshop.common.Constants;
import com.proxtechshop.entities.Brand;
import com.proxtechshop.services.BrandService;

@Controller
public class BrandMngController {
	@Autowired
	BrandService brandService;

	@RequestMapping(Constants.ADMIN_BRAND_PATH+"/update")
	public String updateBrandPage(@RequestParam("id") int id,@RequestParam("data") String data,Model model) {
		boolean flag=brandService.updateBrand(id,data);
		if(flag)
		{
			model.addAttribute("flag",flag);
			model.addAttribute("msg","Thay đổi thành công!");
		}else
		{
			model.addAttribute("flag",flag);
			model.addAttribute("msg","Thay đổi không thành công!");
		}
//		//strangely, but if dont do that, it will load only one record which is just created
//		model.addAttribute("brand",brandService.loadAll());
		return getBrandPage(model);
	}
	
	@RequestMapping(value=Constants.ADMIN_BRAND_PATH,method=RequestMethod.POST)
	public String CreateBrand(Brand brand,Model model) {
		boolean flag=brandService.create(brand);
		if(flag)
		{
			model.addAttribute("flag2",flag);
			model.addAttribute("msg2","Thêm thành công");
		}
		else {
			model.addAttribute("flag2",flag);
			model.addAttribute("msg2","Thêm không thành công");
		}
		//strangely, but if dont do that, it will load only one record which is just created
		model.addAttribute("brand",brandService.loadAll());
		return getBrandPage(model);	
	}
	
	@RequestMapping(Constants.ADMIN_BRAND_PATH+"/f")
	public String SearchBrand(@RequestParam(value="search") String search,Model model) {
		
		if(search=="") {
			model.addAttribute("msg","Hãy nhập tên nhãn hiệu cần tìm!");
		}
		else
		{
			List<Brand> brandResult=brandService.searchBrand(search);
			if(brandResult.size()==0) {
				model.addAttribute("flag",false);
				model.addAttribute("msg","Không tìm thấy "+search);
			}
			else
			{
				model.addAttribute("brand",brandResult);
				model.addAttribute("flag",true);
				model.addAttribute("msg","Kết quả của "+search);
			}
		}
		return getBrandPage(model);
	}
	@RequestMapping(Constants.ADMIN_BRAND_PATH)
	public String getBrandPage(Model model) {
		if(model.getAttribute("brand")==null) {
			model.addAttribute("brand",brandService.loadAll());
		}
		//anchor model in create new brand
		model.addAttribute("newbrand",new Brand());
		return Constants.ADMIN_BRAND_VIEW;
	}
}
