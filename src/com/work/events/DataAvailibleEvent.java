package com.work.events;

import java.util.EventObject;

@SuppressWarnings("serial")
public class DataAvailibleEvent extends EventObject{
	
	private String message;

	public DataAvailibleEvent(Object event, String message) {
		super(event);
		this.message = message;
		
	}
	
	public String toString() {
		return getClass().getName() + "[source = " + getSource() + ", message = " + message + "]";
		
	}

}
