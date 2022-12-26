package com.proxtechshop.admincontrollers;

import java.lang.ProcessBuilder.Redirect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.proxtechshop.common.Constants;
import com.proxtechshop.dto.AttrValueDto;
import com.proxtechshop.services.ProductService;

@Controller
public class SpecificationMngController {
	
	@Autowired
	ProductService productService;
	
	@RequestMapping(Constants.ADMIN_FOFMATTRIBUTE_PATH)
	public String getSpecificationForm(@RequestParam("id") String id,Model model) {
//		if(model.getAttribute("specifications")==null)
//		{
			AttrValueDto attrValueDto=new AttrValueDto();
			attrValueDto.setAttributeValues(productService.loadAllAttrValue(id));
			System.out.println(attrValueDto);
			model.addAttribute("specifications",attrValueDto);
			model.addAttribute("idproduct",id);			
//		}
		return Constants.ADMIN_FOFMATTRIBUTE_VIEW;
	}
	
	@RequestMapping(value=Constants.ADMIN_FOFMATTRIBUTE_PATH,method=RequestMethod.POST)
	public String ModifyListOfAttrValue(@RequestParam("id") String id,@ModelAttribute AttrValueDto attrValueDto,Model model)
	{
		boolean flag=productService.modifyListOfAttrValue(attrValueDto,id);
		if(flag) {
			model.addAttribute("flag",flag);
			model.addAttribute("msg","Thay đổi thành công!");
		}
		else {
			model.addAttribute("flag",flag);
			model.addAttribute("msg","Thay đổi không thành công!");
		}
		return getSpecificationForm(id, model);
	}
	
	@RequestMapping(Constants.ADMIN_FOFMATTRIBUTE_PATH+"/delete")
	public String deleteAttrValue(@RequestParam("id")String id,@RequestParam("idattr")int idAttr, Model model) {
		boolean flag=productService.deleteValueOfAttr(id, idAttr);
		if(flag) {
			model.addAttribute("flag",flag);
			model.addAttribute("msg","Xóa thành công!");
		}
		else {
			model.addAttribute("flag",flag);
			model.addAttribute("msg","Xóa không thành công!");
		}
		return getSpecificationForm(id, model);
	}
	
	@RequestMapping(value=Constants.ADMIN_FOFMATTRIBUTE_PATH+"/add",method=RequestMethod.POST)
	public RedirectView createAttrAndValue(@RequestParam("id") String id,@RequestParam("attr")String attr,@RequestParam("value")String value,Model model) {
		boolean flag=productService.addAttrAndValue(id, attr, value);
		if(flag)
		{
			model.addAttribute("flag2",flag);
			model.addAttribute("msg2","Thêm không thành công!");

		}
		else {
			model.addAttribute("flag2",flag);
			model.addAttribute("msg2","Thêm thành công!");
		}
		
		return new RedirectView(Constants.ADMIN_FOFMATTRIBUTE_PATH + "?id=" + id);
	}
	
}
