package edu.handong.csee.java.hw3;

public class StringNotMatchException extends Exception {

	public StringNotMatchException() {
		super("edu.handong.csee.java.hw3.StringNotMatchException occured!");
	}
	public StringNotMatchException(String message) {
		super(message);
	}
}
