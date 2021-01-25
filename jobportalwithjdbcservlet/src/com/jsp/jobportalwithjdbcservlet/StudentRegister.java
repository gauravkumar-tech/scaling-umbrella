package com.jsp.jobportalwithjdbcservlet;

public class StudentRegister {
	String sname;
	int sid;
	String semail;
	int sper;
	
	public StudentRegister(String sname, int sid, String semail, int sper) {
		this.sname = sname;
		this.sid = sid;
		this.semail = semail;
		this.sper=sper;
	}
	
	public int hashCode(){
		return sid;
	}
	
	public boolean equals(Object o){
		return this.hashCode()==o.hashCode();
	}
	
	
	void printdetails(){
		System.out.println("id of the student is: "+sid);
		System.out.println("name of the sudent is: "+sname);
		System.out.println("gmail of the student is: "+semail);
		System.out.println("percentage of the student is: "+sper);
	}
	
	
	
}
