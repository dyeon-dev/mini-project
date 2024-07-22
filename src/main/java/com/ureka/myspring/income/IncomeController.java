package com.ureka.myspring.income;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class IncomeController {
	@Autowired
	private IncomeRepository inRepo;
	
	
	
	@GetMapping("/income/count")
	@ResponseBody
	public String count() {
		return "" + inRepo.count();
	}
	
	@RequestMapping("/income/list")
	@ResponseBody
	public List<Income> list() {
		return inRepo.findAll();
	}
	
}