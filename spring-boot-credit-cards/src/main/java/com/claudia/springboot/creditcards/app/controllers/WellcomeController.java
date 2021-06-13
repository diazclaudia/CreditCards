package com.claudia.springboot.creditcards.app.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WellcomeController {

	@RequestMapping("wellcome")
	public String wellcomeController(@RequestParam(value="name") String name) {
		return "Hello "+name+"!!";
	}
}