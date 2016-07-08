package com.kosta.zuplay;

import java.util.ArrayList;
import java.util.List;

public class Test02 {

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(2);
		list.add(1);
		list.add(100);
		int index = list.indexOf(new Integer(100));
		list.remove(index);
		System.out.println(list);
	}

}
