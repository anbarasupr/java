package spliterator;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import common.Person;

public class CreateSpliterator {

	public static void main(String[] args) {

		String p="D:\\workspace\\java\\java8\\src\\files\\peoples.txt";
		Path path = Paths.get(p);
		try (Stream<String> lines = Files.lines(path)) {
			Spliterator<String> lineSpliterator=lines.spliterator();
			Spliterator<Person> peopleSpliterator=new PeopleSpliterator(lineSpliterator);

			Stream<Person> people=StreamSupport.stream(peopleSpliterator, false);
			people.forEach(System.out::println);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
