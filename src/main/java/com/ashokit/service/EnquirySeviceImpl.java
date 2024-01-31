package com.ashokit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.ashokit.binding.SearchCriteria;
import com.ashokit.entity.StudentEnq;
import com.ashokit.repo.StudentEnqRepo;

@Service
public class EnquirySeviceImpl implements EnquiryService {

	@Autowired
	StudentEnqRepo srepo;
	
	@Override
	public boolean addEnq(StudentEnq se) {
                StudentEnq savedenq =  srepo.save(se);
		return savedenq.getCid() != null ;
	}

	@Override
	public List<StudentEnq> getEnquiries(Integer cid, SearchCriteria s) {
		
		StudentEnq enq = new StudentEnq();
		enq.setCid(cid);
		//if mode selected add that query
		if(s.getClassmode()!= null && !s.getClassmode().equals("")) {
			enq.setClassmode(s.getClassmode());
		}
		if(s.getCoursename()!= null && !s.getCoursename().equals("")) {
			enq.setCoursename(s.getCoursename());
		}
		if(s.getEnqstatus()!= null && !s.getEnqstatus().equals("")) {
			enq.setEnqstatus(s.getEnqstatus());
		}
		Example<StudentEnq> of = Example.of(enq);
		    List<StudentEnq> enquiries =  srepo.findAll(of);
		return enquiries;
	}

}
