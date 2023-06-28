package it.polito.tdp.food.model;

public class Stazione {
	
	public enum State{
		ATTIVA,FERMA;
	}
	
	private Integer numeroStazione;
	private State Stato;
	private Food food;
	
	public Stazione(Integer numeroStazione, State stato, Food food) {
		super();
		this.numeroStazione = numeroStazione;
		this.Stato=stato;
		this.food = food;
	}
	
	public Integer getNumeroStazione() {
		return numeroStazione;
	}
	public void setNumeroStazione(Integer numeroStazione) {
		this.numeroStazione = numeroStazione;
	}
	public State getStato() {
		return Stato;
	}
	public void setStato(State stato) {
		Stato = stato;
	}
	public Food getFood() {
		return food;
	}
	public void setFood(Food food) {
		this.food = food;
	}
	
	

}
