package com.gildata.quote.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class QuoteController {
	
	@RequestMapping(value = "/test")
	@ResponseBody
	public String test(){
		return "test";
	}

}
