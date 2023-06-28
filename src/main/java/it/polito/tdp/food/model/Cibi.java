package it.polito.tdp.food.model;

public class Cibi {
	
	public enum Stato{
		ATTESA,IN_PREPARAZIONE,PREPARATO;
	}
	
	private Food food;
	private Stato stato;
	
	
	public Food getFood() {
		return food;
	}
	public void setFood(Food food) {
		this.food = food;
	}
	public Stato getStato() {
		return stato;
	}
	public void setStato(Stato stato) {
		this.stato = stato;
	}
	public Cibi(Food food, Stato stato) {
		super();
		this.food = food;
		this.stato = stato;
	}
	@Override
	public String toString() {
		return "Cibi [food=" + food + ", stato=" + stato + "]";
	}
	
	
	
	

}
