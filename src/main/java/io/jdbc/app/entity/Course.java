package io.jdbc.app.entity;

import java.util.List;

public class Course {
	
	private int id;
	private String code;
	private List<Lession> lessions;
	
	public Course() {
		super();
	}
	public Course(int id, String code, List<Lession> lessions) {
		super();
		this.id = id;
		this.code = code;
		this.lessions = lessions;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public List<Lession> getLessions() {
		return lessions;
	}
	public void setLessions(List<Lession> lessions) {
		this.lessions = lessions;
	}
	
	

}
