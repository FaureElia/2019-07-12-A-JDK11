package it.polito.tdp.food.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.food.model.Condiment;
import it.polito.tdp.food.model.Coppia;
import it.polito.tdp.food.model.Food;
import it.polito.tdp.food.model.Portion;

public class FoodDao {
	public List<Food> listAllFoods(){
		String sql = "SELECT * FROM food" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Food> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new Food(res.getInt("food_code"),
							res.getString("display_name")
							));
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			e.printStackTrace();
			return null ;
		}

	}
	
	public List<Condiment> listAllCondiments(){
		String sql = "SELECT * FROM condiment" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Condiment> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new Condiment(res.getInt("condiment_code"),
							res.getString("display_name"),
							res.getDouble("condiment_calories"), 
							res.getDouble("condiment_saturated_fats")
							));
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			e.printStackTrace();
			return null ;
		}
	}
	
	public List<Portion> listAllPortions(){
		String sql = "SELECT * FROM portion" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Portion> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new Portion(res.getInt("portion_id"),
							res.getDouble("portion_amount"),
							res.getString("portion_display_name"), 
							res.getDouble("calories"),
							res.getDouble("saturated_fats"),
							res.getInt("food_code")
							));
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			e.printStackTrace();
			return null ;
		}

	}

	public List<Food> getFoods(int p) {
		String sql = "SELECT f.* , COUNT(DISTINCT p.portion_id) AS numeroPorzioni "
				+ "FROM porzione p, food f "
				+ "WHERE p.food_code=f.food_code "
				+ "GROUP BY f.food_code "
				+ "HAVING numeroPorzioni<=? " ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, p);
			
			List<Food> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
					list.add(new Food(res.getInt("food_code"),
							res.getString("display_name")
							));
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			e.printStackTrace();
			return null ;
		}
	}

	public List<Coppia> getCoppie(int p, Map<Integer, Food> idMap) {
		String sql = " SELECT tmp1.food_code AS f1, tmp2.food_code AS f2, AVG(tmp1.condiment_calories) AS media_calorie "
				+ "FROM "
				+ "(SELECT f1.food_code, c1.condiment_code, c1.condiment_calories "
				+ "FROM food_condiment f1, condiment c1 "
				+ "WHERE f1.condiment_code = c1.condiment_code) AS tmp1, "
				+ "(SELECT f1.food_code, c1.condiment_code, c1.condiment_calories "
				+ "FROM food_condiment f1, condiment c1 "
				+ "WHERE f1.condiment_code = c1.condiment_code) AS tmp2 "
				+ "WHERE tmp1.food_code>tmp2.food_code AND tmp1.condiment_code=tmp2.condiment_code "
				+ "GROUP BY tmp1.food_code, tmp2.food_code " ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Coppia> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				if(idMap.containsKey(res.getInt("f1")) && idMap.containsKey(res.getInt("f2")) ) {
					list.add(new Coppia(idMap.get(res.getInt("f1") ),idMap.get(res.getInt("f2")),res.getDouble("media_calorie")));
				}
			}
			
				
			
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			e.printStackTrace();
			return null ;
		}
	}

	
}
