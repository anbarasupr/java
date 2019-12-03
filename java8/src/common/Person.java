package common;

public class Person {
	private String name;
	private Gender gender;
	private int age;

	public Person(String name, Gender gender, int age) {
		super();
		this.name = name;
		this.gender = gender;
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
		System.out.println("getGender:" + name+", "+ gender);
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public int getAge() {
		System.out.println("getAge:" + name+", "+ age);
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
