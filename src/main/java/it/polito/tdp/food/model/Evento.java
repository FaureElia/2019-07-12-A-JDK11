package it.polito.tdp.food.model;

public class Evento implements Comparable<Evento> {
	
	Stazione stazione;
	Cibi cibo;
	double tempo;
	
	
	
	public double getTempo() {
		return tempo;
	}
	public void setTempo(double tempo) {
		this.tempo = tempo;
	}
	public Evento(Stazione stazione, Cibi cibo, double tempo) {
		super();
		this.stazione = stazione;
		this.cibo = cibo;
		this.tempo = tempo;
	}
	public Stazione getStazione() {
		return stazione;
	}
	public void setStazione(Stazione stazione) {
		this.stazione = stazione;
	}
	public Cibi getCibo() {
		return cibo;
	}
	public void setCibo(Cibi cibo) {
		this.cibo = cibo;
	}
	@Override
	public int compareTo(Evento o) {
		// TODO Auto-generated method stub
		return (int)(this.tempo-o.tempo);
	}
	
	

}
