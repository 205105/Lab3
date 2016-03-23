package it.polito.tdp.lab3.controller;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

//import it.polito.tdp.lab3.db.CorsoDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
//import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import it.polito.tdp.lab3.model.SegreteriaModel;
import it.polito.tdp.lab3.model.Studente;

public class SegreteriaStudentiController {

	private List<String> corsi=new LinkedList<String>();
	
	private SegreteriaModel model=new SegreteriaModel();
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> cmbCorsiDisp;

    @FXML
    private TextField txtMatricola;
    
    @FXML
    private ImageView immImmagine;
    
    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button bttCerca;

    @FXML
    private Button bttIscrivi;

    @FXML
    private TextArea txtRisultato;

    @FXML
    private Button bttReset;

    @FXML
    void doCerca(ActionEvent event) {

    }

    @FXML
    void doClickImmagine(MouseEvent event) {
    	String matricola=txtMatricola.getText();
    	Studente s=model.studente(matricola);
    	txtNome.setText(s.getNome());
    	txtCognome.setText(s.getCognome());
    }

    @FXML
    void doIscrivi(ActionEvent event) {

    }

    @FXML
    void doReset(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert cmbCorsiDisp != null : "fx:id=\"cmbCorsiDisp\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert immImmagine != null : "fx:id=\"immImmagine\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert bttCerca != null : "fx:id=\"bttCerca\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert bttIscrivi != null : "fx:id=\"bttIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert bttReset != null : "fx:id=\"bttReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        corsi.add("");//campo vuoto
        corsi.addAll(model.nomeCorso());
        cmbCorsiDisp.getItems().addAll(corsi);
        //immImmagine.setIm.setImage(new Image("lib\\spunta2.jpg")); 
        //potrei inizializzare qui l'immagine per inserire un tipo di immagine e poi premendo sull'immagine stessa o su reset modifico le immagini
    }
}
