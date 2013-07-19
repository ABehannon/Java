//Alex Behannon
//Java Week 2
//07.18.2013

package com.behannon.lib;

public enum Enum {
	//cat names for random selection
	catName0(0,"Ambassador","McMittens"),
	catName1(1,"Lieutenant","Maximillian"),
	catName2(2,"Captain","McClump"),
	catName3(3,"The Honorable","Von Paws"),
	catName4(4,"Viscount","Whiskers"),
	catName5(5,"Baron","McFluff"),
	catName6(6,"Chancellor","Furrington"),
	catName7(7,"Deacon","Angrypants"),
	catName8(8,"Magistrate","Scratches"),
	catName9(9,"Professor","Pawsalot");
	
	//set strings for the first and last name
	private final float nameCount;
	private final String firstName;
	private final String lastName;
	
	//sets up a new enum
	private Enum(float nameCount, String firstName, String lastName) {
		this.nameCount = nameCount;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	//sets name count
	public float setNameCount(){
		return nameCount;
	}
	
	//sets first name
	public String setFirstName(){
		return firstName;
	}
	
	//sets last name
	public String setLastName(){
		return lastName;
	}
}