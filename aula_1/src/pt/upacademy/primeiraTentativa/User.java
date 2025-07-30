package pt.upacademy.primeiraTentativa;

public class User {
	
	private String name;
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public void printUserInfo() {
		System.out.println("Hello " + getName() + "!");
		System.out.println("You're " + getAge() + " years old.");
	}
}
