package edu.handong.csee.java.hw3;
/**
 * This is MessageLists class
 * It has name, message, year, month, date, time members
 * It has MessageLists and getter methods.
 * It's usage is like a structure.
 * @author Minju
 *
 */
public class MessageLists {

	String name="";
	String message="";
	int year=0;
	int month=0;
	int date=0;
	int time=0;
	/**
	 * This is MessageLists constructor
	 * It gets name,message,year,month,date,time and then save it to MessagLists members
	 * @param name , String
	 * @param message , String
	 * @param year ,int 
	 * @param month ,int
	 * @param date , int 
	 * @param time ,int
	 */
	public MessageLists(String name, String message, int year, int month, int date, int time) {
		this.name = name;
		this.message = message;
		this.year = year;
		this.month = month;
		this.date = date;
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public String getMessage() {
		return message;
	}

	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}

	public int getDate() {
		return date;
	}

	public int getTime() {
		return time;
	}

}
