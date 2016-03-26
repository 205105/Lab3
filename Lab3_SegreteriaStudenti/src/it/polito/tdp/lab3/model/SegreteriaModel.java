package it.polito.tdp.lab3.model;

import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab3.db.CorsoDAO;

public class SegreteriaModel {

	CorsoDAO dao=new CorsoDAO();
	private List<Corso> corsi=new LinkedList<Corso>(dao.corso());
	
	
	public List<String> nomeCorso(){
		List<String> c=new LinkedList<String>();
		for(Corso a: corsi)
			c.add(a.getNome());
		return c;
	}
	
	public Studente studente(int matricola){
		Studente s=dao.studente(matricola);
		return s;
	}
	
	public String codCorso(String nomeCorso){
		return dao.codCorso(nomeCorso);
	}
	
	public String studentiIscrittiDatoNomeCorso(String codCorso){
		return dao.studentiIscrittiDatoNomeCorso(codCorso);
	}
	
	public String studentiIscrittiDataMatricola(int matricola){
		//controllare che lo studente sia presente nel database
		Studente s=dao.studente(matricola);
		if(s!=null){
			//se presente restituire la lista di corsi a cui e' iscritto
			return dao.studentiIscrittiDataMatricola(matricola);
		}
		else {
		// se non e' presente restituire un mex d'errore
			return "error";
		}
	}
	
	public boolean iscriviStudenteACorso(int matricola, String codins){
		return dao.iscriviStudenteACorso(matricola, codins);
	}
}
