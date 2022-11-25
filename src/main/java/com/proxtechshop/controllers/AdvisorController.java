package com.proxtechshop.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.proxtechshop.common.Constants;

/**
 * Handler Exception 
 * @author ongcaoboi
 *
 */
@ControllerAdvice
public class AdvisorController {

	// Handler error 404 page not found
    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handle(Exception ex) {

    	ModelAndView mv = new ModelAndView();
        mv.addObject("message", ex.getMessage());
        mv.setViewName(Constants.ERROR_404_VIEW);

        return mv;
    }
}