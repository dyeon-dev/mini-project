package com.ureka.myspring.expend;

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
public class ExpendController {
	@Autowired
	private ExpendRepository inRepo;
	
	
	
	@GetMapping("/expend/count")
	@ResponseBody
	public String count() {
		return "" + inRepo.count();
	}
	
	@GetMapping("/expend/list")
	@ResponseBody
	public List<Expend> list() {
		return inRepo.findAll();
	}
	
}