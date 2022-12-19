package com.proxtechshop.admincontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.proxtechshop.common.Constants;
import com.proxtechshop.entities.Brand;
import com.proxtechshop.entities.Category;
import com.proxtechshop.services.CategoryService;

@Controller
public class CategoryMngController {
	
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping(Constants.ADMIN_CATEGORIESMNG_PATH+"/update")
	public String UpdateCategory(@RequestParam(value="id",required = true) int id,@RequestParam("data") String data,Model model) {
		boolean flag=categoryService.updateCtg(id,data);
		if(flag)
		{
			model.addAttribute("flag",flag);
			model.addAttribute("msg","Thay đổi thành công!");
		}else
		{
			model.addAttribute("flag",flag);
			model.addAttribute("msg","Thay đổi không thành công!");
		}
		return GetCategoryPage(model);
		//ban nen danh dau san pham moi update trong html de nguoi ta biet category da them vao duoc.
	}
	
	@RequestMapping(value=Constants.ADMIN_CATEGORIESMNG_PATH,method=RequestMethod.POST)
	public String CreateCategory(Category category,Model model) {
		boolean flag=categoryService.create(category);
		if(flag)
		{
			model.addAttribute("flag",flag);
			model.addAttribute("msg","Thêm thành công");
		}
		else {
			model.addAttribute("flag",flag);
			model.addAttribute("msg","Thêm không thành công");
		}
		model.addAttribute("category",categoryService.loadAll());
		return GetCategoryPage(model);	
	}
	
	@RequestMapping(Constants.ADMIN_CATEGORIESMNG_PATH+"/f")
	public String SearchCategory(@RequestParam(value="search") String search,Model model) {
		
		if(search=="") {
			model.addAttribute("msg","Hãy nhập tên nhãn hiệu cần tìm!");
		}
		else
		{
			List<Category> ctgResult=categoryService.searchCtg(search);
			if(ctgResult.size()==0) {
				model.addAttribute("flag",false);
				model.addAttribute("msg","Không tìm thấy "+search);
			}
			else
			{
				model.addAttribute("category",ctgResult);
				model.addAttribute("flag",true);
				model.addAttribute("msg","Kết quả của "+search);
			}
		}
		return GetCategoryPage(model);
	}
	//có thể xóa category không có sản phẩm nào áp dụng nó
	@RequestMapping(Constants.ADMIN_CATEGORIESMNG_PATH)
	public String GetCategoryPage(Model model) {
		if(model.getAttribute("category")==null) {
			List<Category> list=categoryService.loadAll();
			model.addAttribute("category",list);
		}
		//anchor model in create new ctg
				model.addAttribute("newctg",new Brand());
		return Constants.ADMIN_CATEGORIESMNG_VIEW;
	}
}
