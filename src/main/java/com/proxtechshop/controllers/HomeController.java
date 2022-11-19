package com.proxtechshop.controllers;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import com.proxtechshop.common.Constants;
import com.proxtechshop.utils.FileUploadUtil;

@Controller
public class HomeController {
	
	@GetMapping("upload")
	public String uploadGet() {
		return "upload_t/index";
	}

	@PostMapping("upload")
	public RedirectView upload(@RequestParam("file") MultipartFile file) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
 
        FileUploadUtil.saveFile("", fileName, file);
		
		return new RedirectView(Constants.HOME_PATH, true);
	}
}
