package com.bit.spring03.springJava;

public class HelloModule {

	private String msg="asd";
	
	public HelloModule(String msg) {
		this.msg=msg;
	}
//	public void setMsg(String msg) {
//		this.msg = msg;
//	}
	
	
	
	public void print(){
		System.out.println(msg);
	}
}
