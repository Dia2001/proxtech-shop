package com.proxtechshop.admincontrollers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.proxtechshop.common.Constants;
import com.proxtechshop.entities.User;
import com.proxtechshop.services.MemberService;

@Controller
public class MemberMngController {
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(Constants.ADMIN_MEMBERSMNG_PATH)
	String GetMemberPage(Model model) {
		return findPaginated(1, "username", "asc", model);
	}
	
	@RequestMapping(Constants.ADMIN_FORMMEMBER_PATH)
	public String FormMember(@RequestParam(name="id",required=false) Optional<String> id,Model model){
		if(id.isPresent()) {
			try {
				model.addAttribute("member",memberService.GetUserById(id.get()));
			}catch(Exception e) {
				model.addAttribute("flag",false);
				model.addAttribute("msg","Không tìm thấy nhân viên này!");
				return findPaginated(1, "username", "asc", model);
			}	
		}
		else
		{
			model.addAttribute("member",new User());
		}
		return Constants.ADMIN_FOFMMEMBER_VIEW;
	}
	
	@RequestMapping(value=Constants.ADMIN_FORMMEMBER_PATH,method=RequestMethod.POST)
	String ModifyMember(User user,Model model) {
		if(memberService.UpdateMember(user)) {
			model.addAttribute("flag",true);
			model.addAttribute("msg","Sửa thành công!");
		}
		else {
			model.addAttribute("flag",false);
			model.addAttribute("msg","Tên người dùng bị trùng hoặc không hợp lệ!");
			//without add this,it will reject error id null
			model.addAttribute("member",user);
			return Constants.ADMIN_FOFMMEMBER_VIEW;
		}
		return findPaginated(1, "username", "asc", model);
	}
	
	@RequestMapping(Constants.ADMIN_MEMBERSMNG_PATH+"/delete")
	String RemoveMember(@RequestParam("id") String id,Model model) {
		if(memberService.Remove(id)) {
			model.addAttribute("flag",true);
			model.addAttribute("msg","Xóa thành công!");
		}else {
			model.addAttribute("flag",false);
			model.addAttribute("msg","Xóa không thành công!");
		}
		return findPaginated(1, "username", "asc", model);
	}
	@RequestMapping(Constants.ADMIN_MEMBERSMNG_PATH+"/filter")
	public String searchMember(@RequestParam("search") String search, Model model) {
		if(search=="")
		{
			model.addAttribute("flag",false);
			model.addAttribute("msg","Không để trống phần tìm kiếm");
			model.addAttribute("search",null);
			return findPaginated(1, "username", "asc", model);
		}
		else
		{
			model.addAttribute("search",search);
			return findPaginated(1, "username", "asc", model);
		}
		
	}
	
	//pagination
	@RequestMapping(Constants.ADMIN_MEMBERSMNG_PATH+"/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 4;
		//handler search engine
		Page<User> page;
		if(model.getAttribute("search")==null)
			page = memberService.paginated(pageNo, pageSize, sortField, sortDir);
		else
		{
			String search=model.getAttribute("search").toString();
			page=memberService.FilterAndPaginated(search, pageNo, pageSize, sortField, sortDir);
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
		List<User> listMembers = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
//		model.addAttribute("brand",typesProductService.getAllBrand()); can use bean
//		model.addAttribute("category",typesProductService.getAllCategory());
		
		model.addAttribute("member",listMembers);
		return Constants.ADMIN_MEMBERSMNG_VIEW;
}
}
