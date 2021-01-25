package com.jsp.jobportalwithjdbcservlet;

public class CompanyRegister {
	int cid;
	String cname;
	int cper;
	
	public CompanyRegister(int cid, String cname, int cper) {
		this.cid = cid;
		this.cname = cname;
		this.cper = cper;
	}
	
	void printdetailscompany(){
		System.out.println("Company name: "+cname+" has registered succesfully\n and the id is: "+cid);
		System.out.println("company requirement is this percentage: "+cper);
	}
	
	
}
