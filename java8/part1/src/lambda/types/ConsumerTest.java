package lambda.types;

import java.util.*;
import java.util.Iterator;
import java.util.function.*;

import common.Employee;
import common.Student;

public class ConsumerTest {

	public static void main(String[] args) {

		String s = "Hello my dear darling. How are you?";
		Consumer<String> c = str -> System.out.println("consumer received:" + str);
		c.accept(s);
		// find students information including grade whose marks >=60

		Function<Student, String> f = stud -> {
			int marks = stud.getMarks();
			if (marks >= 80) {
				return "A[Distinction]";
			} else if (marks >= 60) {
				return "B[First Class]";
			} else if (marks >= 50) {
				return "B[Second Class]";
			} else if (marks >= 40) {
				return "B[Third Class]";
			} else {
				return "E[Failed]";
			}
		};

		Predicate<Student> p=stud->stud.getMarks()>=60;
		Consumer<Student> con=stud->System.out
				.println("name: " + stud.getName() + ", marks: " + stud.getMarks() + " , Grade: " + f.apply(stud));
		
		System.out.println("PRINTING STUDENTS GRADE WHOSE MARKS >=60");
		List<Student> list=getStudentList();
		for (Student stud : list) {
			if(p.test(stud)) {
				con.accept(stud);
			}			
		}
	}

	public static List<Student> getStudentList() {
		List<Student> list = new ArrayList<>();
		list.add(new Student("anbu", 100));
		list.add(new Student("priya", 99));
		list.add(new Student("selvam", 60));
		list.add(new Student("aruna", 30));
		return list;
	}
}
