package com.challenge.gamerps.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@PostMapping(value = "/postdata", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<List<Object>> postData(@RequestBody GameDataBean[] dataBean, Model model) {
		return gameDataService.getTotalData(dataBean);
	}
	
	@GetMapping(value = "/showdata")
	public String showDataPage(@RequestParam(value="firsts", defaultValue="0") String firsts,
			@RequestParam(value="seconds", defaultValue="0") String seconds,
			@RequestParam(value="draws", defaultValue="0") String draws, 
			@RequestParam(value="rounds", defaultValue="0") String rounds, Model model) {
		model.addAttribute("firsts", firsts);
		model.addAttribute("seconds", seconds);
		model.addAttribute("draws", draws);
		model.addAttribute("rounds", rounds);
		return "datapage";
	}
	
}
