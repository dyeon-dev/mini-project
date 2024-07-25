package com.ureka.myspring.login;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MymemberRepository mymemRepo;

    public Map<String, Object> signup(Mymember member) {
        Map<String, Object> result = new HashMap<>();
        Optional<Mymember> existingMember = mymemRepo.findById(member.getMid());

        if (existingMember.isPresent()) {
            result.put("code", "error");
            result.put("message", "이미 존재하는 아이디입니다.");
        } else {
            mymemRepo.save(member);
            result.put("code", "ok");
            result.put("message", "회원가입 완료");
        }
        return result;
    }
}
