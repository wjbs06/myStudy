package com.bit.am;

import java.util.ArrayList;

public class Ex10 {

	public static void main(String[] args) {
		ArrayList<Integer> list=new ArrayList<Integer>();
		list.add(111);
		list.add(222);
		list.add(333);
		list.add(444);

		
		ArrayList<Integer> list2=(ArrayList<Integer>)list.clone();
		
		ArrayList<Integer> list3=new ArrayList<Integer>(list);
		
		Object[] arr=list.toArray();
		
		ArrayList<Integer> list4=new ArrayList<Integer>();
		for (int i = 0; i < arr.length; i++) {
//			System.out.println(arr[i]);
			list4.add((Integer)arr[i]);
		}
		
		
	}

}
