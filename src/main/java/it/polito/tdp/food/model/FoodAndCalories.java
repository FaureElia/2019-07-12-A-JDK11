package it.polito.tdp.food.model;

public class FoodAndCalories implements Comparable<FoodAndCalories>{
	
	private Food food;
	private double calories;
	
	
	public FoodAndCalories(Food food, double calories) {
		super();
		this.food = food;
		this.calories = calories;
	}
	
	public Food getFood() {
		return food;
	}
	public void setFood(Food food) {
		this.food = food;
	}
	public double getCalories() {
		return calories;
	}
	public void setCalories(double calories) {
		this.calories = calories;
	}

	@Override
	public int compareTo(FoodAndCalories o) {
		
		return (int)(this.calories-o.calories);
	}

	@Override
	public String toString() {
		return this.food+": "+this.calories;
	}
	
	
	
	

}
