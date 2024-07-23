package com.ureka.myspring.expend;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
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
public class ExpendController {
    
    @Autowired
    private ExpendRepository exRepo;
    
    @GetMapping("/expend/count")
    @ResponseBody
    public String count() {
        return "" + exRepo.count();
    }
    
    @RequestMapping("/expend/list")
    @ResponseBody
    public List<Expend> list() {
    	return exRepo.findAll(Sort.by(Sort.Direction.ASC, "indate"));
    }
    
    @PostMapping("/expend/regist")
    @ResponseBody
    public Map<String,Object> regist(Expend in, @RequestParam("date")String indate) {
        Map<String, Object> result = new HashMap<>();
        LocalDate d = LocalDate.parse(indate);
        in.setIndate(d);
        exRepo.save(in);
        result.put("code", "ok");
        return result;
    }
    @GetMapping("/expend/detail/{no}")
	@ResponseBody
	public Expend detail(@PathVariable("no") int no) {
		Map<String, Object> result = new HashMap<>();
		Optional<Expend> ys = exRepo.findById(no);
		if(ys.isPresent()) {
			Expend board = ys.get();
			result.put("code", "ok");
			result.put("code", board);
		} else {
			result.put("code", "error");
			result.put("message", "없거나 삭제된 번호입니다");			
		}
		return ys.get();
	}
    @PostMapping("/expend/update")
	@ResponseBody
	public Map<String,Object> update(Expend in, @RequestParam("date")String indate) {
		Map<String, Object> result = new HashMap<>();
		Optional<Expend> bd = exRepo.findById(in.getNo());
		if (bd.isPresent()) {
	        Expend board = bd.get();
	        board.setTitle(in.getTitle());
	        board.setPrice(in.getPrice());
	        board.setCategory(in.getCategory());
	        LocalDate d = LocalDate.parse(indate);
	        board.setIndate(d);
	        exRepo.save(board);
	        result.put("code", "ok");
	    } else {
	        result.put("code", "error");
	        result.put("message", "없거나 삭제된 번호입니다");
	    }
		return result;
	}
    
    @GetMapping("/expend/delete/{id}")
	@ResponseBody
	public Map<String,Object> delete(@PathVariable("id") int no) {
		Map<String, Object> result = new HashMap<>();
		Optional<Expend> bd = exRepo.findById(no);
		if(bd.isPresent()) {
			exRepo.deleteById(no);
			result.put("code", "ok");
		}else {
			result.put("code", "error");
			result.put("message","없거나 삭제된 번호입니다" );
		}
		return result ;
	}
}