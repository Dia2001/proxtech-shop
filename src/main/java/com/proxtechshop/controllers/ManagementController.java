package com.proxtechshop.controllers;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.proxtechshop.common.Constants;
import com.proxtechshop.entities.Product;
import com.proxtechshop.services.ProductService;
import com.proxtechshop.services.TypesProductService;


@Controller
public class ManagementController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	TypesProductService typesProductService;
	@RequestMapping(Constants.ADMIN_PATH)
	public RedirectView testAdmin2(Model model) {
		return new RedirectView(Constants.ADMIN_PRODUCTMNG_PATH);
	}
	
	
	@RequestMapping(Constants.ADMIN_PRODUCTMNG_PATH)
	public String ProductMng(Model model)
	{
		return findPaginated(1, "name", "asc", model);
	}
	@RequestMapping(Constants.ADMIN_CUSTOMERSMNG_PATH)
	public String CustomerMng(Model model)
	{
		return Constants.ADMIN_CUSTOMERSMNG_VIEW;
	}
	@RequestMapping(Constants.ADMIN_MEMBERSMNG_PATH)
	public String MemberMng(Model model)
	{
		model.addAttribute("products",productService.getAllProduct());
		return Constants.ADMIN_MEMBERSMNG_VIEW;
	}
//	@RequestMapping(Constants.ADMIN_BRAND_PATH)
//	public String BrandMng(Model model) {
//		return Constants.ADMIN_BRAND_VIEW;
//	}
//	@RequestMapping(Constants.ADMIN_CATEGORIESMNG_PATH)
//	public String CategoryMng(Model model)
//	{
//		return Constants.ADMIN_CATEGORIESMNG_VIEW;
//	}
	@RequestMapping(Constants.ADMIN_ORDERMNG_PATH)
	public String OrderMng(Model model)
	{
		return Constants.ADMIN_ORDERMNG_VIEW;
	}
	@RequestMapping(Constants.ADMIN_PROFILE_PATH)
	public String ProfileAdminMng(Model model)
	{
		return Constants.ADMIN_PROFILE_VIEW;
	}
	
	
	@RequestMapping(Constants.ADMIN_PRODUCTMNG_PATH+"/delete/{id}")
	public String DeleteProduct(@PathVariable("id")Optional<String> id,Model model)
	{
		if(id.isPresent())
		{
			try {
				productService.DeleteProduct(id.get());
			}catch(Exception e){
				model.addAttribute("flag",false);
				model.addAttribute("msg","Sản phẩm không tồn tại.");
				//model.addAttribute("products",productService.getAllProduct());
				return findPaginated(1, "name", "asc", model);
			}
			model.addAttribute("flag",true);
			model.addAttribute("msg","Xóa thành công");
			//model.addAttribute("products",productService.getAllProduct());
			return findPaginated(1, "name", "asc", model);
		}
		model.addAttribute("flag",false);
		model.addAttribute("msg","Xóa không thành công");
		//model.addAttribute("products",productService.getAllProduct());
		return findPaginated(1, "name", "asc", model);
	}
	@RequestMapping(Constants.ADMIN_PRODUCTMNG_PATH+"/search")
	public RedirectView searchProduct(@RequestParam(value="find",required = false) String search,Model model) {
		return new RedirectView(findPaginated(1, "name", "asc", model));
	}
	
	@RequestMapping(Constants.ADMIN_PRODUCTMNG_PATH+"/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 4;
		
		Page<Product> page = productService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Product> listProducts = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("brand",typesProductService.getAllBrand());
		model.addAttribute("category",typesProductService.getAllCategory());
		
		model.addAttribute("products",listProducts);
		return Constants.ADMIN_PRODUCTMNG_VIEW;
}
	@GetMapping(Constants.ADMIN_FOFMPRODUCT_PATH)
	public String formProduct(@RequestParam(name="id",required=false) Optional<String> id,Model model) {
		if(id.isPresent()) {
			try {
				model.addAttribute("product",productService.getProductById(id.get()));
				model.addAttribute("specifications",productService.showAtrsAndValues(id.get()));
			}catch(Exception e){
				model.addAttribute("flag",false);
				model.addAttribute("msg","Không tìm thấy sản phẩm chỉnh sửa!");
				return findPaginated(1, "name", "asc", model);
			}
		}else
		{
		model.addAttribute("specifications",productService.showAtrsAndValues(null));
		model.addAttribute("product",new Product());
		}
		//add product attribute value , how can i add this effectively?
		return getFormProduct(model);
	}
	
	public String getFormProduct(Model model) {
		model.addAttribute("brand",typesProductService.getAllBrand());
		model.addAttribute("category",typesProductService.getAllCategory());
		return Constants.ADMIN_FOFMPRODUCT_VIEW;
	}
	@RequestMapping(value=Constants.ADMIN_FOFMPRODUCT_PATH,method=RequestMethod.POST)
	public String UpdateProduct(Product product,Model model)
	{
		boolean flag=productService.updateProduct(product);
		if(flag) {
			model.addAttribute("flag",true);
			model.addAttribute("msg","Thêm hoặc sửa sản phẩm thành công!");
			return findPaginated(1, "name", "asc", model);
		}
		else {
			model.addAttribute("flag",false);
			model.addAttribute("msg","Thêm hoặc sửa không thành công!");
			return getFormProduct(model);
		}
	}
	@RequestMapping(Constants.ADMIN_PATH+"/{page}")
	public RedirectView testAdmin(@PathVariable(name="page",required = false)Optional<String> id ,Model model) {
		switch (id.get()) {
		case "1":
			return new RedirectView(Constants.ADMIN_CUSTOMERSMNG_PATH);
		case "2":
			return new RedirectView(Constants.ADMIN_MEMBERSMNG_PATH);
		case "3":
			return new RedirectView(Constants.ADMIN_BRAND_PATH);
		case "4":
			return new RedirectView(Constants.ADMIN_CATEGORIESMNG_PATH);
		case "5":
			return new RedirectView(Constants.ADMIN_PRODUCTMNG_PATH);
		case "6":
			return new RedirectView(Constants.ADMIN_ORDERMNG_PATH);
		case "7":
			return new RedirectView(Constants.ADMIN_PROFILE_PATH);
		}
		return new RedirectView(Constants.ERROR_404_VIEW);
	}
}

