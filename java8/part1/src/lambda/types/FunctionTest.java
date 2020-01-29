package lambda.types;

import java.util.*;
import java.util.Iterator;
import java.util.function.*;

import common.Employee;
import common.Student;

public class FunctionTest {

	public static void main(String[] args) {

		// count no of spaces in a string
		String s = "Hello my dear darling. How are you?";
		Function<String, Integer> f = str -> str.length() - str.replaceAll(" ", "").length();
		System.out.println("Spaces COUNT: " + f.apply(s));

		// find students information including grade whose marks >=60

		Function<Student, String> sf = stud -> {
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

		System.out.println("PRINTING STUDENTS GRADE");
		test(sf, getStudentList());

		System.out.println("Salary Increment for employees using both predicate and function, print the same");
		Predicate<Employee> p = emp -> emp.getSalary() < 3000;
		Function<Employee, Double> ef = emp -> {
			double salary = emp.getSalary();
			// calculate based on market correction
			double times = 0;
			if (salary >= 2000 && salary < 3000) {
				times = 1.5;
			} else if (salary >= 1000 && salary < 2000) {
				times = 2.5;
			} else {
				times = 3.5;
			}
			salary += salary * times;
			return salary;
		};
		List<Employee> list = getEmployeeList();
		for (Employee emp : list) {
			if (p.test(emp)) {
				System.out.println(
						"name: " + emp.getName() + ", salary: " + emp.getSalary() + " , Incremented: " + ef.apply(emp));
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

	public static List<Employee> getEmployeeList() {
		List<Employee> list = new ArrayList<>();
		list.add(new Employee("anbu", 2000));
		list.add(new Employee("priya", 5000));
		list.add(new Employee("selvam", 7000));
		list.add(new Employee("aruna", 1000));
		return list;
	}

	public static void test(Function<Student, String> f, List<Student> list) {
		for (Student stud : list) {
			System.out
					.println("name: " + stud.getName() + ", marks: " + stud.getMarks() + " , Grade: " + f.apply(stud));
		}
	}

}
