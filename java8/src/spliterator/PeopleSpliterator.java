package spliterator;

import java.util.Spliterator;
import java.util.function.Consumer;

import common.Gender;
import common.Person;

public class PeopleSpliterator implements Spliterator<Person> {

	Spliterator<String> lineSpliterator;
	private String name;
	private String location;
	private int age;

	public PeopleSpliterator(Spliterator<String> lineSpliterator) {
		this.lineSpliterator = lineSpliterator;
	}

	@Override
	public int characteristics() {
		return lineSpliterator.characteristics();
	}

	@Override
	public long estimateSize() {
		return lineSpliterator.estimateSize() / 3;
	}

	@Override
	public boolean tryAdvance(Consumer<? super Person> action) {

		if (this.lineSpliterator.tryAdvance(line -> this.name = line)
				&& this.lineSpliterator.tryAdvance(line -> this.age = Integer.parseInt(line))
				&& this.lineSpliterator.tryAdvance(line -> this.location = line)) {
			Person p = new Person(name, age, location);
			action.accept(p);
			return true;
		}
		return false;
	}

	@Override
	public Spliterator<Person> trySplit() {
		return null;
	}

}
