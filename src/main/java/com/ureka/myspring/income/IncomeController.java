package com.ureka.myspring.income;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
}
