package com.kosta.zuplay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Test {

	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>(Arrays.asList(new String[]{"A","B","C","D"}));
	Iterator<String> it =	list.iterator() ;
	while(it.hasNext()){
		
		String data = it.next();
		if(data.equals("B")){
		  it.remove();
		}
	}
	System.out.println("--------------------------");
	
	for(String s:list)
		System.out.println(s);

	}

}
