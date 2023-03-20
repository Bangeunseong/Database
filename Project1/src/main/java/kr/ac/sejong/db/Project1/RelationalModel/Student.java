package kr.ac.sejong.db.Project1.RelationalModel;

public class Student {
	//Field
	private String name, email, phoneNumber;
	
	//Constructor
	public Student(String name, String phoneNumber, String email) {
		this.name = name; this.phoneNumber = phoneNumber; this.email = email;
	}
	
	//Method
	public String getName() {return name;}
	public String getPhonenumber() {return phoneNumber;}
	public String getEmail() {return email;}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Student) {
			return phoneNumber.hashCode() == o.hashCode();
		}
		return false;
	}
	@Override
	public int hashCode() {
		return phoneNumber.hashCode();
	}
	@Override
	public String toString() {
		return "{" + name + ", " + phoneNumber + ", " + email + "}";
	}
}
