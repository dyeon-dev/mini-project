package com.ureka.myspring.income;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
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
    	return inRepo.findAll(Sort.by(Sort.Direction.ASC, "indate"));
    }
    
    @PostMapping("/income/regist")
    @ResponseBody
    public Map<String,Object> regist(Income in, @RequestParam("date")String indate) {
        Map<String, Object> result = new HashMap<>();
        LocalDate d = LocalDate.parse(indate);
        in.setIndate(d);
        inRepo.save(in);
        result.put("code", "ok");
        return result;
    }
    @GetMapping("/income/detail/{no}")
	@ResponseBody
	public Income detail(@PathVariable("no") int no) {
		Map<String, Object> result = new HashMap<>();
		Optional<Income> ys = inRepo.findById(no);
		if(ys.isPresent()) {
			Income board = ys.get();
			result.put("code", "ok");
			result.put("code", board);
		} else {
			result.put("code", "error");
			result.put("message", "없거나 삭제된 번호입니다");			
		}
		return ys.get();
	}
    @PostMapping("/income/update")
	@ResponseBody
	public Map<String,Object> update(Income in, @RequestParam("date")String indate) {
		Map<String, Object> result = new HashMap<>();
		Optional<Income> bd = inRepo.findById(in.getNo());
		if (bd.isPresent()) {
	        Income board = bd.get();
	        board.setTitle(in.getTitle());
	        board.setPrice(in.getPrice());
	        LocalDate d = LocalDate.parse(indate);
	        board.setIndate(d);
	        inRepo.save(board);
	        result.put("code", "ok");
	    } else {
	        result.put("code", "error");
	        result.put("message", "없거나 삭제된 번호입니다");
	    }
		return result;
	}
    
    @GetMapping("/income/delete/{id}")
	@ResponseBody
	public Map<String,Object> delete(@PathVariable("id") int no) {
		Map<String, Object> result = new HashMap<>();
		Optional<Income> bd = inRepo.findById(no);
		if(bd.isPresent()) {
			inRepo.deleteById(no);
			result.put("code", "ok");
		}else {
			result.put("code", "error");
			result.put("message","없거나 삭제된 번호입니다" );
		}
		return result ;
	}
}
