package com.CodingBootcamp.customException;

public class InvalidEmailFormatException extends Exception{

	public InvalidEmailFormatException(String s) {
		super(s);
	}
}
