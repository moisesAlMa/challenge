package com.challenge.gamerps.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.challenge.gamerps.bean.GameDataBean;
import com.challenge.gamerps.services.GameDataService;

@Controller
@RequestMapping("/gamerps")
public class RPSRestController {
	
	@Autowired
	GameDataService gameDataService;
	
	@GetMapping("/showinit")
	public String showInitPage() {
		return "init";
	}
	
	@GetMapping("/showplaying")
	public String showPlayingPage() {
		return "playingpage";
	}
	
	@PostMapping(value = "/showdata", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String showDataPage(@RequestBody GameDataBean[] dataBean, Model model) {
		model.addAttribute("data", gameDataService.getTotalData(dataBean));
		model.addAttribute("rounds", dataBean.length);
		return "datapage";
	}
	
}
