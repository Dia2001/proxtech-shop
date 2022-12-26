package com.proxtechshop.admincontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.proxtechshop.common.Constants;

@Controller
public class DashboardController {

	@GetMapping(Constants.ADMIN_DASHBOARD_PATH)
	public String getDashboardView() {
		return Constants.ADMIN_DASHBOARD_VIEW;
	}
}
