package org.firstlamda;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class ComparatorLambda {

	public static void main(String[] args) throws InterruptedException {
		// anonymous class
		Comparator<String> comparator=new Comparator<String>() {			
			@Override
			public int compare(String s1, String s2) {
				return Integer.compare(s1.length(), s2.length());
			}
		};

		// lambda exp
		Comparator<String> comparatorLambda=(String s1,String s2)->Integer.compare(s1.length(), s2.length());
		
		List<String> list=Arrays.asList("******","*****","****","***","**","*");
		Collections.sort(list, comparator);
		for(String s:list) {
			System.out.println(s);
		}
		
		Collections.sort(list, comparatorLambda);
		for(String s:list) {
			System.out.println(s);
		}
		
	}

}
