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
//import it.polito.tdp.lab3.model.Corso;
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
    	try{
    	if(cmbCorsiDisp.getValue()!=null && txtMatricola.getText().compareTo("")==0){
    	//cercare tutti gli studenti iscritti ad un corso dato il nome di un corso dal menù a tendina(ES 2.3)
    	String nomeCorso=cmbCorsiDisp.getValue();
    	String codCors=model.codCorso(nomeCorso);
    	txtRisultato.setText(model.studentiIscrittiDatoNomeCorso(codCors));
    	}
    	
    	if(txtMatricola.getText().compareTo("")!=0 && cmbCorsiDisp.getValue()==null){
    		//controllare se lo studente e' iscritto nel database e nel caso visualizzare tutti i corsi a cui e' iscritto, se non e' presente
    		//visualizzare un messaggio d'errore(ES 2.4)
    		int matricola=Integer.parseInt(txtMatricola.getText());
    		String s=model.studentiIscrittiDataMatricola(matricola);
    		if(s.compareTo("error")!=0){
    			//visualizzare la lista di corsi
    			txtRisultato.setText(s);
    		} else {
    			//visualizzare mex d'errore
    			txtRisultato.setText("La matricola inserita non e' presente nel database");
    		}
    	}
    	
    	if(txtMatricola.getText().compareTo("")!=0 && cmbCorsiDisp.getValue()!=null){
    		//controllare che lo studente specificato sia presente nel corso(ES 2.5)
    		int matricola=Integer.parseInt(txtMatricola.getText());
    		String nomeCorso=cmbCorsiDisp.getValue();
    		String corsiInCuiEIscrittoLoStudente=model.studentiIscrittiDataMatricola(matricola);
    		Studente st=model.studente(matricola);
    		if(corsiInCuiEIscrittoLoStudente.contains(nomeCorso)){
    			//lo studente xxx e' iscritto al corso yyy
    			txtRisultato.setText("Lo studente "+st.getNome()+" "+st.getCognome()+" ("+st.getMatricola()+") e' iscritto al corso \""+nomeCorso+"\"");
    		} else if(corsiInCuiEIscrittoLoStudente.compareTo("error")==0) {
    			//visualizzare mex d'errore
    			txtRisultato.setText("La matricola inserita non e' presente nel database");
    		} else {
    			//lo studente xxx non e' iscritto al corso yyy
    			txtRisultato.setText("Lo studente "+st.getNome()+" "+st.getCognome()+" ("+st.getMatricola()+") NON e' iscritto al corso \""+nomeCorso+"\"");
    		}
    	} 
    	}catch (NumberFormatException n){
    		txtRisultato.setText("formato della matricola non valido, inserire solo numeri");
    	}
    }

    @FXML
    void doClickImmagine(MouseEvent event) {
    	try{
    		int matricola=Integer.parseInt(txtMatricola.getText());
    	Studente s=model.studente(matricola);
    	txtNome.setText(s.getNome());
    	txtCognome.setText(s.getCognome());
    	} catch (NumberFormatException n){
    		txtRisultato.setText("formato della matricola non valido, inserire solo numeri");
    	}
    }

    @FXML
    void doIscrivi(ActionEvent event) {
    	try{
    	int matricola=Integer.parseInt(txtMatricola.getText());
		String nomeCorso=cmbCorsiDisp.getValue();
		if(nomeCorso.compareTo("")==0){
			txtRisultato.setText("selezionare il nome di un corso");
			return;
		}
		String corsiInCuiEIscrittoLoStudente=model.studentiIscrittiDataMatricola(matricola);
		Studente st=model.studente(matricola);
		if(st==null){
			txtRisultato.setText("inserita la matricola di uno studente non presente nel database");
		} else {
		if(corsiInCuiEIscrittoLoStudente.contains(nomeCorso)){
			txtRisultato.setText("Lo studente "+st.getNome()+" "+st.getCognome()+" ("+st.getMatricola()+") e' gia' iscritto al corso \""+nomeCorso+"\"");
		} else {
			//iscrivere lo studente al corso
			String codCors=model.codCorso(nomeCorso);
			boolean b=model.iscriviStudenteACorso(matricola, codCors);
			if(b==true)
				txtRisultato.setText("Lo studente "+st.getNome()+" "+st.getCognome()+" ("+st.getMatricola()+") e' stato iscritto al corso \""+nomeCorso+"\"");
			else
				txtRisultato.setText("errore nell'iscrizione: bisogna inserire un numero di matricola e selezionare il nome di un corso gia' presenti nel database");
		}
		}
		} catch (NumberFormatException n){
    		txtRisultato.setText("formato della matricola non valido, inserire solo numeri");
    	}
    }

    @FXML
    void doReset(ActionEvent event) {
    	txtMatricola.setText("");
    	txtNome.setText("");
    	txtCognome.setText("");
    	txtRisultato.setText("");
    	cmbCorsiDisp.setValue(null);
    	
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
        cmbCorsiDisp.setValue("");
        //immImmagine.setImage(new Image("spuntaVerde.png")); 
        //potrei inizializzare qui l'immagine per inserire un tipo di immagine e poi premendo sull'immagine stessa o su reset modifico le immagini
    }
}
