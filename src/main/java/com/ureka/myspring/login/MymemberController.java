package com.ureka.myspring.login;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

@Controller
@RestController
public class MymemberController {
	@Autowired
    private MemberService memberService;
	
	@Autowired
	private MymemberRepository mymemRepo;
	
	// 회원가입 기능 
	@PostMapping("/member/signup")
    @ResponseBody
    public Map<String, Object> signup(
            @RequestParam("mid") String mid,
            @RequestParam("mpwd") String mpwd,
            @RequestParam("mname") String mname,
            @RequestParam("memail") String memail,
            @RequestParam("mphone") String mphone) {

        Mymember member = new Mymember();
        member.setMid(mid);
        member.setMpwd(mpwd);
        member.setMname(mname);
        member.setMemail(memail);
        member.setMtel(mphone);

        return memberService.signup(member);
    }
	
	//로그인기능
	@PostMapping("/member/login")
	@ResponseBody
	public Map<String, Object> login(
			@RequestParam("mid") String mid, 
			@RequestParam("mpwd") String mpwd,
			HttpSession session){
		HashMap<String, Object> result = new HashMap<>();
		result.put("code", "error");
		
		Optional<Mymember> mem = mymemRepo.findById(mid);
		if(mem.isPresent()) {
			Mymember mymember = mem.get();
			if(mymember.getMpwd().equals(mpwd)) {
				result.put("code", "ok");
				result.put("message", "로그인완료");
				session.setAttribute("member", mymember);
			}else {
				result.put("message", "암호가 틀렸습니다.");
			}
		}else {
			result.put("message", "아이디를 다시 입력해주세요");
		}
		System.out.println(result);
		return result;
	}
	
	// 로그아웃 기능
	@GetMapping("/member/logout")
	@ResponseBody
	public Map<String, Object> logout( HttpSession session ){
		HashMap<String, Object> result = new HashMap<>();
		
		Object object = session.getAttribute("member");
		String message = "";
		if(object==null) {
			message += "로그인 안된 상태.";
		}else {
			message += "로그인 된 상태.";
		}
		session.invalidate();//로그인정보 삭제
		result.put("code", "ok");
		result.put("message", message + "로그아웃완료");
		
		System.out.println(result);
		return result;
	}
	
	//로그인 체크
	@GetMapping("/member/check_login")
	@ResponseBody
	public Map<String, Object> check_login( HttpSession session ){
		 Map<String, Object> result = new HashMap<>();
		 Object object = session.getAttribute("member");

	        if (object!= null) {
	            result.put("loggedIn", true);
	            result.put("code", "ok");
				result.put("message", "On Login");
				Mymember mm = (Mymember)object;
				mm.setMpwd(null);
				result.put("data", mm); //로그인된 사용자 정보

	        } else {
	            result.put("loggedIn", false);
	            result.put("code", "error");
				result.put("message", "Not Login");
	        }
	        System.out.println(result);
			return result;

	}
}
