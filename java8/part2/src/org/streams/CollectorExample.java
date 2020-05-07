package org.streams;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.common.Person;

public class CollectorExample {

	public static void main(String[] args) {

		List<Person> persons = new ArrayList<Person>();
		try {
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(CollectorExample.class.getResourceAsStream("people.txt")));
			Stream<String> stream = reader.lines();
			Function<String, Person> f = line -> {
				String s[] = line.split(" ");
				Person p = new Person(s[0], Integer.parseInt(s[1]));
				persons.add(p);
				return p;
			};
			Consumer<? super Person> c = System.out::println; // print the person
			stream.map(f).forEach(c);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("persons list size:" + persons.size());

		Optional<Person> min = persons.stream().filter(p -> p.getAge() >= 20).min(Comparator.comparing(Person::getAge));
		System.out.println("Optional person:" + min);

		Optional<Person> max = persons.stream().max(Comparator.comparing(Person::getAge));
		System.out.println("Optional person:" + max);

		// group gives map
		Map<Integer, List<Person>> groupByAge = persons.stream().collect(Collectors.groupingBy(Person::getAge));
		System.out.println("groupByAge person:" + groupByAge);

		Map<Integer, Long> groupByAgeCount = persons.stream()
				.collect(Collectors.groupingBy(Person::getAge, Collectors.counting()));
		System.out.println("groupByAgeCount person:" + groupByAgeCount);

		Map<Integer, List<String>> groupByAgeWithNameList = persons.stream().collect(
				Collectors.groupingBy(Person::getAge, Collectors.mapping(Person::getName, Collectors.toList())));
		System.out.println("groupByAgeWithNameList person:" + groupByAgeWithNameList);

		Map<Integer, String> groupByAgeWithNameCommaSep = persons.stream().collect(
				Collectors.groupingBy(Person::getAge, Collectors.mapping(Person::getName, Collectors.joining(","))));
		System.out.println("groupByAgeWithNameCommaSep person:" + groupByAgeWithNameCommaSep);

		Map<Integer, Set<String>> groupByAgeWithNameSortedSet = persons.stream().collect(Collectors.groupingBy(
				Person::getAge, Collectors.mapping(Person::getName, Collectors.toCollection(TreeSet::new))));
		System.out.println("groupByAgeWithNameSortedSet person:" + groupByAgeWithNameSortedSet);

	}

}
