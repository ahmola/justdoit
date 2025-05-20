package com.example.demo.Controller;

import com.example.demo.DTO.MemberDTO;
import com.example.demo.Entity.Member;
import com.example.demo.Repository.MemberRepository;
import com.example.demo.Service.MemberService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {
    @Autowired
    MemberService memberService;

    @Autowired
    PasswordEncoder passwordEncoder;

    // Register New member

    @GetMapping("/join")
    public String joinMember(Model model){
        model.addAttribute("memberDTO", new MemberDTO());

        return "/member/memberJoinForm";
    }

    @PostMapping("/join")
    public String joinMember(@Validated MemberDTO memberDTO, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            return "/member/memberJoinForm";
        }

        try {
            System.out.println("Get " + memberDTO.toString());
            Member member = Member.createMember(memberDTO, passwordEncoder);
            memberService.saveMember(member);
        }catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "/member/memberJoinForm";
        }

        return "redirect:/";
    }

    // Login

    @GetMapping("/login")
    public String loginMember(){
        return "/member/memberLoginForm";
    }

    @GetMapping("/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "Check Email or Password Please");
        return "/member/memberLoginForm";
    }
}
