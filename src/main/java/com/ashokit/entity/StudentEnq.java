package com.ashokit.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class StudentEnq {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer enqid;
	private String name;
	private Long phno;
	private String classmode;
	private String coursename;
	private String enqstatus;
	@CreationTimestamp
	private LocalDate createddate;
	private Integer cid;
	public Integer getEnqid() {
		return enqid;
	}
	public void setEnqid(Integer enqid) {
		this.enqid = enqid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getPhno() {
		return phno;
	}
	public void setPhno(Long phno) {
		this.phno = phno;
	}
	public String getClassmode() {
		return classmode;
	}
	public void setClassmode(String classmode) {
		this.classmode = classmode;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	public String getEnqstatus() {
		return enqstatus;
	}
	public void setEnqstatus(String enqstatus) {
		this.enqstatus = enqstatus;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	
	
}
