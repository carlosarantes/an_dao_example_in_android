package com.model;

public class Car {
	
	private int idCar;
	private String name;
	private int year;
	private String factory;
		
	public Car() {
		super();
	}

	public Car(int idCar, String name, int year, String factory) {
		super();
		this.idCar = idCar;
		this.name = name;
		this.year = year;
		this.factory = factory;
	}
	
	public int getIdCar(){
		return idCar;
	}
	
	public void setIdCar(int idCar){
		this.idCar = idCar;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getFactory() {
		return factory;
	}
	public void setFactory(String factory) {
		this.factory = factory;
	}

	@Override
	public String toString() {
		return  idCar+" - "+name + ", - " + year + ", " + factory;
	}
}
