package it.polito.tdp.lab3.model;

import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab3.db.CorsoDAO;

public class SegreteriaModel {

	CorsoDAO dao=new CorsoDAO();
	private List<String> corsi=new LinkedList<String>();
	
	public List<String> nomeCorso(){
		corsi=dao.nomeCorso();
		return corsi;
	}
	
	public Studente studente(String matricola){
		Studente s=dao.studente(matricola);
		return s;
	}
}
