package com.kh.woofly.info.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InfoController {
	
	@GetMapping("map")
	public String mapView() {
		return "map";
	}
}
