package com.bit.am;

public class Ex06 implements Runnable{

	//Runnable 인터페이스르 상속하면 1개의 객체만 만들면 가능하다
	public static void main(String[] args) {
		System.out.println("start");
		Ex06 me = new Ex06();
		Thread thr1=new Thread(me,"첫번째");
		Thread thr2=new Thread(me,"첫번째");
		Thread thr3=new Thread(me,"첫번째");
		thr1.start();
		thr2.start();
		thr3.start();
		System.out.println("end");
	}
	public void run(){
		Thread thr=Thread.currentThread();
		System.out.println(thr.getName()+"시작");
		System.out.println(thr.getName()+"종료");
	}
}
