package com.CodingBootcamp.customException;

public class InvalidUserNameException extends Exception {

	public InvalidUserNameException(String s) {
		super(s);
	}
}
