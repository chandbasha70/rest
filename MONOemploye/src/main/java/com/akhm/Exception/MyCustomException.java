package com.akhm.Exception;

public class MyCustomException extends RuntimeException{
	public MyCustomException(String message) {
		super(message);
	}
	public MyCustomException(String message,Throwable thro) {
		super(message,thro);
	}

}
