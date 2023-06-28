package it.polito.tdp.food.model;

public class RisultatoSimulazione {

	double minutiImpiegati;
	int cibiPreparati;
	
	public RisultatoSimulazione(double minutiImpiegati, int cibiPreparati) {
		super();
		this.minutiImpiegati = minutiImpiegati;
		this.cibiPreparati = cibiPreparati;
	}

	public double getMinutiImpiegati() {
		return minutiImpiegati;
	}

	public void setMinutiImpiegati(double minutiImpiegati) {
		this.minutiImpiegati = minutiImpiegati;
	}

	public int getCibiPreparati() {
		return cibiPreparati;
	}

	public void setCibiPreparati(int cibiPreparati) {
		this.cibiPreparati = cibiPreparati;
	}
	
	
	
}
