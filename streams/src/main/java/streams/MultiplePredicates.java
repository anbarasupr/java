package streams;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MultiplePredicates {
	public static void main(String[] args) {
		List<Employee> employeeList = getEmployeesFromDataSource();

		// filter 1
		Predicate<Employee> isEmployeeActive = e -> e.getStatus() == EmployeeStatus.ACTIVE;

		// filter2
		Predicate<Employee> isAccountActive = e -> e.getAccount().getStatus() == AccountStatus.ACTIVE;

		// Active employees
		String result = employeeList.stream().filter(isEmployeeActive).map(e -> String.valueOf(e.getId()))
				.collect(Collectors.joining(",", "[", "]"));

		System.out.println("Active employees = " + result);

		// Active employees with active accounts
		result = employeeList.stream().filter(isEmployeeActive.and(isAccountActive)).map(e -> String.valueOf(e.getId()))
				.collect(Collectors.joining(",", "[", "]"));

		System.out.println("Active employees with active accounts = " + result);

		// Active employees with inactive accounts
		result = employeeList.stream().filter(isEmployeeActive.and(isAccountActive.negate()))
				.map(e -> String.valueOf(e.getId())).collect(Collectors.joining(",", "[", "]"));

		System.out.println("Active employees with inactive accounts = " + result);

		// Inactive employees with inactive accounts
		result = employeeList.stream().filter(isEmployeeActive.negate().and(isAccountActive.negate()))
				.map(e -> String.valueOf(e.getId())).collect(Collectors.joining(",", "[", "]"));

		System.out.println("Inactive employees with inactive accounts = " + result);
	}
 
}