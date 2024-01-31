package com.ashokit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ashokit.binding.SearchCriteria;
import com.ashokit.entity.StudentEnq;
import com.ashokit.service.EnquiryService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
@Controller
public class EnqController {

	@Autowired
	private EnquiryService enquirysrv;
	
	@GetMapping("/enquiry")
	public String enqpage(Model model) {
		model.addAttribute("enq", new StudentEnq());
		return "addenqView";
	}
	@PostMapping("/enquiry")
	public String addenq(@ModelAttribute("enq") StudentEnq enq ,HttpServletRequest req, Model model) {
		
		             HttpSession session = req.getSession(false);
		         Integer cid      =  (Integer) session.getAttribute("Cid");
		            enq.setCid(cid);
		            if(cid == null) {
		            	return "redirect:logout";
		            }
		     boolean addenq = enquirysrv.addEnq(enq);
		     if(addenq) {
		    	 model.addAttribute("smsg", "enquiry added");
		     }else {
		    	 model.addAttribute("errmsg", "failed to add");
		     }
		return "addenqview";
	}
	@GetMapping("/enquiries")
	public String ViewEnquiries(HttpServletRequest req, Model model) {
		   HttpSession session = req.getSession(false);
	         Integer cid      =  (Integer) session.getAttribute("Cid"); 
	         if(cid == null) {
	        	 return "redirect:/logout";
	         }
	         model.addAttribute("sc", new SearchCriteria());
	List<StudentEnq> enquirylist =	enquirysrv.getEnquiries(cid, new SearchCriteria());
	model.addAttribute("enquiries", enquirylist);
		return "displayEnqView";
	}
	@PostMapping("filter-enquiries")
	public String FilterEnquiries(@ModelAttribute("sc") SearchCriteria sc ,HttpServletRequest req, Model model) {
		 HttpSession session = req.getSession(false);
         Integer cid      =  (Integer) session.getAttribute("Cid"); 
         if(cid == null) {
        	 return "redirect:/logout";
         }
		List<StudentEnq> enquirylist =	enquirysrv.getEnquiries(cid, sc);
		model.addAttribute("enquiries", enquirylist);
		return "displayEnqView";
	}
}
