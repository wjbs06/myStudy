package com.bit.pm;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Ex08 {

	/*
	 * 
	 * 
	 *
	 */
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int su1=10;
		int su2=1;
		
		try{
			su2=sc.nextInt();
			int result=su1/su2;
			System.out.println("result:"+result);
		}
		catch(Exception ex){
			System.out.println("����ȸ��");
		}
		

	}
	
	

}
