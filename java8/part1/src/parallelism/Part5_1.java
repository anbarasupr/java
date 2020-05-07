package parallelism;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import common.Gender;
import common.Person;

/*
 * stream
 * It does not execute a function on a collection of data
 * It instead executes a collection of functions on a piece of data. So the streams are efficient and lazy evaluation (not doing extra work)
 */
public class Part5_1 {

	public static List<Person> createpeople() {

		return Arrays.asList(new Person("Sara", Gender.FEMALE, 20), new Person("Sara", Gender.FEMALE, 22),
				new Person("Bob", Gender.MALE, 20), new Person("Paula", Gender.FEMALE, 32),
				new Person("Paul", Gender.MALE, 32), new Person("Jack", Gender.MALE, 2),
				new Person("Jack", Gender.MALE, 732), new Person("Jill", Gender.FEMALE, 42));
	}

	public static void main(String a[]) throws InterruptedException {

		// find the name in uppercase of the first female older than 30
		String result=createpeople()
		.stream()
		.parallel() // unleash the threads
		.filter(person->person.getGender()==Gender.FEMALE)
		.filter(person-> person.getAge()>30)
		.map(Person::getName)
		.map(String::toUpperCase)
		// .findFirst() // always imposes order
		.findAny() // dont care about the sequence. but it just give me the name of female who is older than 30
		.orElse("No one");
		System.out.println("result: "+result);
	}

}
