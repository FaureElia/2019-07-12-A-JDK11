/**
 * Sample Skeleton for 'Food.fxml' Controller Class
 */

package it.polito.tdp.food;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.food.model.Food;
import it.polito.tdp.food.model.FoodAndCalories;
import it.polito.tdp.food.model.Model;
import it.polito.tdp.food.model.RisultatoSimulazione;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FoodController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtPorzioni"
    private TextField txtPorzioni; // Value injected by FXMLLoader

    @FXML // fx:id="txtK"
    private TextField txtK; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnalisi"
    private Button btnAnalisi; // Value injected by FXMLLoader

    @FXML // fx:id="btnCalorie"
    private Button btnCalorie; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="boxFood"
    private ComboBox<Food> boxFood; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Creazione grafo...");
    	String porzioni=this.txtPorzioni.getText();
    	if(porzioni=="") {
    		this.txtResult.appendText("\n inserire qualcosa");
    		return;
    	}
    	try {
    		int p=Integer.parseInt(porzioni);
    		if(p<1) {
    			this.txtResult.appendText("\n inserire un numero maggiore di 0");
    			return;
    		}
    		List<Food> vertici=this.model.creaGrafo(p);
    		if(vertici.size()!=0 && vertici!=null) {
    			this.txtResult.setText("grafo creato !\n");
    			this.txtResult.appendText("numero di vertici: "+vertici.size()+"\n");
    			this.txtResult.appendText("numero di archi: "+this.model.getArchi()+"\n");
    			this.boxFood.getItems().addAll(vertici);
    		}
    	}catch(NumberFormatException e) {
    		this.txtResult.appendText("\n inserire un numero");
    	}
    }
    
    @FXML
    void doCalorie(ActionEvent event) {
    	Food f=this.boxFood.getValue();
    	if(f==null) {
    		this.txtResult.setText("inserire un cibo");
    		return;
    		
    	}
    	List<FoodAndCalories> listaMigliori=this.model.getBestFoods(f);
    	if (listaMigliori!=null) {
    		this.txtResult.appendText("trovati migliori: "+listaMigliori.size()+"\n");
    		
    		for(FoodAndCalories fo:listaMigliori) {
    			this.txtResult.appendText(fo+"\n");
    		}
    		
    	}
    }

    @FXML
    void doSimula(ActionEvent event) {
    	String k=this.txtK.getText();
    	Food f=this.boxFood.getValue();
    	if(f==null) {
    		this.txtResult.setText("inserire un cibo!");
    	}
    	try {
    		int kInt=Integer.parseInt(k);
    		if(kInt<1 || kInt>10) {
    			this.txtResult.setText("inserire un numero compreso tra 1 e 10");
    			return;
    		}
    		RisultatoSimulazione ris=this.model.simula(f,kInt);
    		if(ris!=null) {
    			this.txtResult.setText("cibi preparati: "+ris.getCibiPreparati()+"\n");
    			this.txtResult.appendText("minuti impiegati: "+ris.getMinutiImpiegati()+"\n");
    			
    		}
    	}catch(NumberFormatException e) {
    		
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtPorzioni != null : "fx:id=\"txtPorzioni\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtK != null : "fx:id=\"txtK\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnAnalisi != null : "fx:id=\"btnAnalisi\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnCalorie != null : "fx:id=\"btnCalorie\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Food.fxml'.";
        assert boxFood != null : "fx:id=\"boxFood\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Food.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
