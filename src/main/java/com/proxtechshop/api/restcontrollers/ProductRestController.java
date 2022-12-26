package com.proxtechshop.api.restcontrollers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proxtechshop.api.request.ProductStatisticsDayStartDayEndRequest;
import com.proxtechshop.api.request.ProductStatisticsMonthRequest;
import com.proxtechshop.common.Constants;
import com.proxtechshop.services.ProductService;

@RestController
@RequestMapping(Constants.API_PRODUCT_PATH)
public class ProductRestController {

	@Autowired
	private ProductService productService;

	@PostMapping(Constants.PRODUCT_STATISTICS_MONTH_PATH)
	public ResponseEntity<?> getProductStatisticsMonth(@RequestBody ProductStatisticsMonthRequest request) {
		Date start = new Date(request.getYear(), request.getMonth(), 1);
		Date end;
		if (request.getYear() == 12) {
			end = new Date(request.getYear() + 1, 1, 1);
		} else {
			end = new Date(request.getYear(), request.getMonth() + 1, 1);
		}

		return new ResponseEntity<>(productService.getProductPStatisticsDateStartDateEnd(start, end), HttpStatus.OK);
	}

	@PostMapping(Constants.PRODUCT_STATISTICS_DAY_START_DAY_END_PATH)
	public ResponseEntity<?> getProductStatisticsDayStartDayEnd(
			@RequestBody ProductStatisticsDayStartDayEndRequest request) {

		return new ResponseEntity<>(
				productService.getProductPStatisticsDateStartDateEnd(request.getStartDate(), request.getEndDate()),
				HttpStatus.OK);
	}
}
