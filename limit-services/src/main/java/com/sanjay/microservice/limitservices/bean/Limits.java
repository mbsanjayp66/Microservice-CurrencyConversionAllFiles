package com.sanjay.microservice.limitservices.bean;

public class Limits {
	
	
	private int minimum;
	private int maximum;
	
	public Limits() {
		// TODO Auto-generated constructor stub
	}

	public Limits(int minimum, int maximum) {
		this.minimum = minimum;
		this.maximum = maximum;
	}

	public int getMinimum() {
		return minimum;
	}

	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}

	public int getMaximum() {
		return maximum;
	}

	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}
	
}
