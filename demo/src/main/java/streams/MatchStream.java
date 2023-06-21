package streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class MatchStream {

	public static void main(String args[]) {

		List<Employee> employees = new ArrayList<>();
		employees.add(new Employee("Sachin Tendulkar", 41, 1));
		employees.add(new Employee("Sachin Tendulkar", 36, 2));
		employees.add(new Employee("MS Dhoni", 34, 3));
		employees.add(new Employee("Rahul Dravid", 40, 4));
		employees.add(new Employee("Lokesh Rahul", 25, 5));
		employees.add(new Employee("Sourav Ganguly", 40, 6));

		List<Employee> employees2 = new ArrayList<>();
		employees2.add(new Employee("Sachin Tendulkar", 41, 11));
		employees2.add(new Employee("MS Dhoni", 34, 12));

		
		List<Employee> employees3 = new ArrayList<>();
		employees3.add(new Employee("Sachin Tendulkar", 41, 1));
		employees3.add(new Employee("MS Dhoni", 34, 3));
		
		
		// v5(employees);
		// v7(employees, employees2);
		// v8(employees);
		v9(employees, employees3);
	}

	public static void v1(List<Employee> employees) {
		boolean isPresent = employees.stream().anyMatch(
				employee -> employee.getName().equalsIgnoreCase("Sachin Tendulkar") && employee.getAge() == 41);
		System.out.println("isPresent: " + isPresent);
	}

	public static void v2(List<Employee> employees) {
		List<Employee> result = employees.stream()
				.filter(employee -> (employee.getName().equalsIgnoreCase("Sachin Tendulkar") && employee.getAge() == 36)
						|| (employee.getName().equalsIgnoreCase("Rahul Dravid") && employee.getAge() == 40))
				.collect(Collectors.toList());
		System.out.println("result: " + result);

	}

	public static void v3(List<Employee> employees) {
		List<Employee> listToFind = Arrays.asList(new Employee("Sachin Tendulkar", 36, 2),
				new Employee("Rahul Dravid", 40, 4));

		boolean isPresent = employees.containsAll(listToFind);
		System.out.println("isPresent: " + isPresent);
	}

	public static void v4(List<Employee> employees) {
		Predicate<Employee> p1 = e -> e.getName().equals("Sachin Tendulkar") && e.getAge() == 36;
		Predicate<Employee> p2 = e -> e.getName().equals("Rahul Dravid") && e.getAge() == 40;
		final boolean isFound = employees.stream().filter(p1.or(p2)).findAny().isPresent();
		System.out.println("isFound: " + isFound);

	}

	// dynamic predicates
	public static void v5(List<Employee> employees) {
		Predicate<Employee> ageLowerBoundPredicate = p -> p.getAge() >= 40;
		Predicate<Employee> ageUpperBoundPredicate = p -> p.getAge() < 45;
		Predicate<Employee> hasComputerPred = p -> p.getName().contains("ul");
		List<Predicate<Employee>> predicates = Arrays.asList(ageLowerBoundPredicate, ageUpperBoundPredicate,
				hasComputerPred);
		List<Employee> filteredPeople = employees.stream().filter(p -> predicates.stream().allMatch(f -> f.test(p)))
				.limit(5).collect(Collectors.toList());
		System.out.println("filteredPeople: " + filteredPeople);

	}

	public static void v6(List<Employee> employees, List<Employee> employees2) {

		boolean anyMatch = employees.stream().anyMatch(employees2::contains);
		System.out.println("anyMatch: " + anyMatch);

		anyMatch = employees2.stream().allMatch(employees::contains);
		System.out.println("anyMatch: " + anyMatch);

		anyMatch = employees.stream().anyMatch(new HashSet<>(employees2)::contains);
		System.out.println("anyMatch: " + anyMatch);

		anyMatch = !Collections.disjoint(employees, employees2); // Return true if 2 specified list have no common
																	// elements
		System.out.println("anyMatch: " + anyMatch);

	}

	public static void v7(List<Employee> listOne, List<Employee> listTwo) {

		List<Employee> listOneList = listOne.stream()
				.filter(one -> listTwo.stream().peek(e -> System.out.println(e))
						.anyMatch(two -> one.getName().equals(two.getName()) && two.getAge() == one.getAge()))
				.collect(Collectors.toList());
		System.out.println("listOneList: " + listOneList);

		List<Employee> result = new ArrayList<Employee>();

		for (Employee one : listOne) {
			for (Employee two : listTwo) {
				if (one.getName().equals(two.getName()) && one.getAge() == two.getAge()) {
					result.add(one);
				}
			}
		}

		System.out.println("oldWayList: " + result);

	}

	public static void v8(List<Employee> listOne) {
		listOne.add(new Employee("Sachin Tendulkar", 36, 2));
		listOne.stream().distinct().forEach(System.out::println);
	}

	public static void v9(List<Employee> listOne, List<Employee> listTwo) {
		// You need to override equals() method in Employee class. contains() method
		// you will uses the equals() method to evaluate if two objects are the same
		List<Employee> listCommon = listTwo.stream().filter(e -> listOne.contains(e)).collect(Collectors.toList());
		System.out.println("listCommon: " + listCommon);

	}
}

@AllArgsConstructor
@ToString
@EqualsAndHashCode
class Employee {

	public Employee(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	@Getter
	@Setter
	private String name;
	@Getter
	@Setter
	private int age;
	@Getter
	@Setter
	private int id;
}
