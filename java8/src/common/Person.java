package common;

public class Person {
	private String name;
	private Gender gender;
	private String location;
	private int age;

	public Person(String name, Gender gender, int age) {
		super();
		this.name = name;
		this.gender = gender;
		this.age = age;
	}

	public Person(String name, int age, String location) {
		super();
		this.name = name;
		this.setLocation(location);
		this.age = age;
	}

	public Person(String name, Gender gender, String location, int age) {
		super();
		this.name = name;
		this.gender = gender;
		this.setLocation(location);
		this.age = age;
	}

	public String getName() {
		System.out.println("getName:" + name);
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Gender getGender() {
		System.out.println("getGender:" + name + ", " + gender);
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public int getAge() {
		System.out.println("getAge:" + name + ", " + age);
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", gender=" + gender + ", location=" + location + ", age=" + age + "]";
	}

}
