package it.polito.tdp.food.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.food.model.Cibi.Stato;
import it.polito.tdp.food.model.Stazione.State;

public class Simulatore {
	//input
	private Graph<Food ,DefaultWeightedEdge> grafo;
	private int numeroStazioni;
	private Food partenza;
	//stato del sistema
	Map<Integer,Stazione> mappaStazioni;
	Map<Food,Cibi> mappaCibi;
	private double minutiAttuali;
	
	//codaEeventi
	private PriorityQueue<Evento> queue;
	
	
	public Simulatore(Graph<Food, DefaultWeightedEdge> grafo, int k, Food partenza) {
		this.grafo = grafo;
		this.numeroStazioni = k;
		this.partenza = partenza;
		this.mappaStazioni=new HashMap<>();
		this.mappaCibi=new HashMap<>();
		this.queue=new PriorityQueue();
		for(int i=1; i<k+1; i++) {
			mappaStazioni.put(i,new Stazione(i, State.FERMA,  null  ));
		}
		ConnectivityInspector<Food, DefaultWeightedEdge> inspector=new ConnectivityInspector<>(this.grafo);
		for(Food f: inspector.connectedSetOf(partenza)) {
			this.mappaCibi.put(f,new Cibi(f, Stato.ATTESA));
		}
		
	}
	
	public RisultatoSimulazione simula() {
		this.initialize();
		this.run();
		
		return new RisultatoSimulazione(this.minutiAttuali,this.mappaCibi.size());
	}

	private void run() {
		while(!this.queue.isEmpty()) {
			System.out.println(this.mappaCibi.values());
			Evento e=this.queue.poll();
			double tempo=e.getTempo();
			Stazione stazione=e.getStazione();
			Cibi cibo=e.getCibo();
			cibo.setStato(Stato.PREPARATO);
			System.out.println(this.mappaCibi.values());
			if(tempo>this.minutiAttuali) {
				this.minutiAttuali=tempo;
			}
			
			List<FoodAndCalories> adiacenti=this.getBestFoods(cibo.getFood());
			if(adiacenti.size()==0) {
				stazione.setStato(State.FERMA);
				stazione.setFood(null);
			}
			else {
				for(FoodAndCalories f: adiacenti) {
					f.getFood();
					System.out.println(this.mappaCibi.get(f.getFood()));
					if (this.mappaCibi.get(f.getFood()).getStato()==Stato.ATTESA) {
						this.queue.add(new Evento(stazione,this.mappaCibi.get(f.getFood()),tempo+f.getCalories()));
						this.mappaCibi.get(f.getFood()).setStato(Stato.IN_PREPARAZIONE);
						break;
					}
				}
			}
			
		}
		
	}

	private void initialize() {
		this.minutiAttuali=0;
		List<FoodAndCalories> adiacenti=this.getBestFoods(partenza);
		for(int i=0; i<mappaStazioni.size() && i<adiacenti.size(); i++) {
			this.queue.add(new Evento(this.mappaStazioni.get(i),this.mappaCibi.get(adiacenti.get(i).getFood()),adiacenti.get(i).getCalories()));
			this.mappaCibi.get(adiacenti.get(i).getFood()).setStato(Stato.IN_PREPARAZIONE);
			this.mappaStazioni.get(i+1).setStato(State.ATTIVA);
		}
		System.out.println("inizializzazione completata");
	}
	
	
	
	
	public List<FoodAndCalories> getBestFoods(Food food) {
		if(this.grafo==null || !this.grafo.vertexSet().contains(food)) {
			System.out.println("Il cibo non è presente nel grafo");
			return null;
		}
		List<FoodAndCalories> listaTutti=new ArrayList<>();
		for(Food f: Graphs.neighborListOf(this.grafo, food)) {
			listaTutti.add(new FoodAndCalories(f,this.grafo.getEdgeWeight(this.grafo.getEdge(f, food))));	
		}
		Collections.sort(listaTutti);
		return listaTutti;	
	}
	
	
	
	
	
	

	

}
