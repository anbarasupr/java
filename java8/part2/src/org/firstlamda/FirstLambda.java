package org.firstlamda;

import java.io.File;
import java.io.FileFilter;
import java.util.Iterator;

public class FirstLambda {

	public static void main(String[] args) {
		// anonymous class
		FileFilter filter = new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				System.out.println("pathname:" + pathname);
				return pathname.getName().endsWith(".java");
			}
		};

		// lambda exp
		FileFilter filterLambda = (File pathname) -> pathname.getName().endsWith(".java");
		File dir = new File("D:/workspace/java/java8/part1/src/common/");
		// File[] files=dir.listFiles(filter); // using anonymous class
		File[] files = dir.listFiles(filterLambda); // using lamda exp

		for (File f : files) {
			System.out.println(f);
		}
	}

}
