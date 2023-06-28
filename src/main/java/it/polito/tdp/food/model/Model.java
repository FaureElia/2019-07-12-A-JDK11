package it.polito.tdp.food.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;


import it.polito.tdp.food.db.FoodDao;

public class Model {
	private FoodDao dao;
	private Graph <Food, DefaultWeightedEdge> grafo;
	private Map<Integer,Food > idMap;
	
	public Model() {
		this.dao=new FoodDao();
	}

	public List<Food> creaGrafo(int p) {
		this.grafo=new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		this.idMap=new HashMap<>();
		
		List<Food> vertici=this.dao.getFoods(p);
		Graphs.addAllVertices(this.grafo, vertici);
		for(Food f: vertici) {
			this.idMap.put(f.getFood_code(), f);
		}
		
		List<Coppia> listaCoppie=this.dao.getCoppie(p,this.idMap);
		for(Coppia c: listaCoppie) {
			Graphs.addEdgeWithVertices(this.grafo, c.getF1(), c.getF2(), c.getMediaCalorie());			
		}
		
		return vertici;
		
		
		
		
	}

	public int getArchi() {
		
		return this.grafo.edgeSet().size();
	}

	public List<FoodAndCalories> getBestFoods(Food food) {
		if(this.grafo==null || !this.grafo.vertexSet().contains(food)) {
			System.out.println("Il cibo non è presente nel grafo");
			return null;
		}
		List<FoodAndCalories> listaTutti=new ArrayList<>();
		for(Food f: Graphs.neighborListOf(this.grafo, food)) {
			listaTutti.add(new FoodAndCalories(food,this.grafo.getEdgeWeight(this.grafo.getEdge(f, food))));	
		}
		Collections.sort(listaTutti);
		List<FoodAndCalories> migliori5=new ArrayList<>();
		int dim=0;
		if(listaTutti.size()>5) {
			 dim=5;
		}else {
			 dim=listaTutti.size();
		}
		
		
		for(int i=0; i<dim; i++) {
			migliori5.add(listaTutti.get(i));	
		}
		return migliori5;
	}

}
