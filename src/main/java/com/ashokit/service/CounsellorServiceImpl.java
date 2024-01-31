package com.ashokit.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashokit.binding.DashboardResponse;
import com.ashokit.entity.Counsellor;
import com.ashokit.entity.StudentEnq;
import com.ashokit.repo.CounsellorRepo;
import com.ashokit.repo.StudentEnqRepo;
import com.ashokit.utils.EmailUtils;

@Service
public class CounsellorServiceImpl implements CounsellorService {

	@Autowired
	CounsellorRepo crepo;
	
	@Autowired
	EmailUtils emailutils;
	
	@Autowired
	StudentEnqRepo srepo;
	
	@Override
	public String savecounsellor(Counsellor c) {
		
	       Counsellor obj =	 crepo.findByEmail(c.getEmail());
		    if(obj != null) {
		    	return "duplicate email";
		    }
		
		 Counsellor savedobj =  crepo.save(c);
		 if(savedobj != null) {
			 return "Registration Success";
		 }
		return "Registration Failure";
	}

	@Override
	public Counsellor loginCheck(String email, String pwd) {
		return crepo.findByEmailAndPwd(email, pwd);
	}

	@Override
	public boolean recoverPwd(String email) {
		    Counsellor c = crepo.findByEmail(email);
		    
		    if(c == null) {
		    	return false;
		    }
		    
		    String subject = "your email recover passsword";
		    String body = "<h1>your password ::"+c.getPwd()+"<h1>";
	       return emailutils.sendemail(subject,body, email);
	}

	@Override
	public DashboardResponse getDashboardInfo(Integer cid) {
		
		List<StudentEnq>  allEnqs =  srepo.findByCid(cid);
		          int enrolledenq =  allEnqs.stream().filter(e -> e.getEnqstatus().equals("enrolled")).collect(Collectors.toList()).size();
		     
		          
		     DashboardResponse resp = new DashboardResponse();
		     
		     resp.setTotalenq(allEnqs.size());
		     resp.setEnrolledenq(enrolledenq);
		     resp.setLostenq(allEnqs.size()-enrolledenq);
		return resp;
	}

}
