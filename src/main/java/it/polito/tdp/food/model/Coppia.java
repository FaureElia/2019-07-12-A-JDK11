package it.polito.tdp.food.model;

public class Coppia {
	
	private Food f1;
	private Food f2;
	private double mediaCalorie;
	
	
	public Coppia(Food f1, Food f2, double mediaCalorie) {
		super();
		this.f1 = f1;
		this.f2 = f2;
		this.mediaCalorie = mediaCalorie;
	}
	
	
	public Food getF1() {
		return f1;
	}
	public void setF1(Food f1) {
		this.f1 = f1;
	}
	public Food getF2() {
		return f2;
	}
	public void setF2(Food f2) {
		this.f2 = f2;
	}
	public double getMediaCalorie() {
		return mediaCalorie;
	}
	public void setMediaCalorie(double mediaCalorie) {
		this.mediaCalorie = mediaCalorie;
	}
	
	
	

}
