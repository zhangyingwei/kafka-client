package com.zhangyw.kafka.producer.exception;

public class KCSException extends Exception{
	public KCSException(){
		super();
	}
	public KCSException(Exception e){
		super(e);
	}
	public KCSException(String message){
		super(message);
	}
	public KCSException(String message,Exception e){
		super(message,e);
	}
}
