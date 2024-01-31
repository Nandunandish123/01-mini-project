package com.ashokit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ashokit.binding.DashboardResponse;
import com.ashokit.entity.Counsellor;
import com.ashokit.service.CounsellorService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CounsellorController {
    
	@Autowired
	CounsellorService counsellorsrv;
	@GetMapping("/logout")
	public String logout(HttpServletRequest req,Model model) {
		       HttpSession session =  req.getSession(false);
		       session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/")
	public String index(Model model) {
		
		model.addAttribute("counsellor" ,new Counsellor());
		return "loginview";
	}
	@PostMapping("/login")
	public String handleLogin(Counsellor c,HttpServletRequest req,  Model model) {
		     Counsellor obj =  counsellorsrv.loginCheck(c.getEmail(), c.getPwd());
		     if(obj == null) {
		    	 model.addAttribute("errormsg", "invald credentials");
		    		return "loginview";
		     }
		  HttpSession session =   req.getSession(true);
		  session.setAttribute("Cid", obj.getCid());
		return "redirect:dashboard";
	}
	@GetMapping("/dashboard")
	public String buildDashboard(HttpServletRequest req, Model model) {
		                  HttpSession session =  req.getSession(false);
		            Object obj =  session.getAttribute("Cid");
		             Integer cid = (Integer)obj;
		DashboardResponse dashboardinfo = counsellorsrv.getDashboardInfo(cid);
		model.addAttribute("dashboard", dashboardinfo);
		
		return "dashboard";
	}
	@GetMapping("/register")
	public String regPage(Model model) {
		
		model.addAttribute("counsellor" ,new Counsellor());
		return "registerView";
	}
	
	@PostMapping("/register")
	public String handleRegistration(Counsellor c, Model model) {
		   String msg =   counsellorsrv.savecounsellor(c);
		   model.addAttribute("msg",msg);
		return "registerView";
	}
	
	
	@GetMapping("/forgot-pwd")
	public String recoverPwdPage(Model model) {
		return "forgotPwdView";
	}
	
	@GetMapping("recover-pwd")
	public String recoverpwd(@RequestParam String email,Model model) {
		       boolean status =   counsellorsrv.recoverPwd(email);
		         if(status) {
		        	 model.addAttribute("sms", "password sent to mail");
		         }else {
		        	 model.addAttribute("errormsg", "invalid email");
		         }
		         return "forgotPwdView";
	}
}
