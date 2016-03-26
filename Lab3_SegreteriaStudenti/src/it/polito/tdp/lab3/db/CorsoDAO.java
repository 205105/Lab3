package it.polito.tdp.lab3.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab3.model.Corso;
import it.polito.tdp.lab3.model.Studente;

public class CorsoDAO {

	private List<Corso> corsi=new LinkedList<Corso>();
	
	private String jdbcURL="jdbc:mysql://localhost/iscritticorsi?user=root";
	
	public List<Corso> corso(){
		
		Connection conn;
		try {
			conn = DriverManager.getConnection(jdbcURL);
			Statement st=conn.createStatement();
			String sql="select * from corso"; 
			ResultSet res=st.executeQuery(sql); //se inserisco devo fare executeUpdate()
			while(res.next()){
			corsi.add(new Corso(res.getString("codins"), res.getInt("crediti"), res.getString("nome"), res.getInt("pd"))); //nome e' il nome della colonna nella tabella CORSO in cui ci sono i nomi dei corsi
			}
			res.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return corsi;
	}
	
	public Studente studente(int matricola){
		Connection conn;
		try {
			conn = DriverManager.getConnection(jdbcURL);
			Statement st=conn.createStatement();
			String sql="select nome, cognome from studente where matricola=\""+matricola+"\""; 
			ResultSet res=st.executeQuery(sql); //se inserisco devo fare executeUpdate()
			if(res.next()){
				Studente s=new Studente(matricola, res.getString("nome"), res.getString("cognome"));
				res.close();
				conn.close();
				return s;
			} else {
			res.close();
			conn.close();
			return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String codCorso(String nomeCorso){
		Connection conn;
		try {
			conn = DriverManager.getConnection(jdbcURL);
			Statement st=conn.createStatement();
			String sql="select codins from corso where nome=\""+nomeCorso+"\""; 
			ResultSet res=st.executeQuery(sql);
			if(res.next()){
				String r=res.getString("codins");
				res.close();
				conn.close();
				return r;
			} else {
			res.close();
			conn.close();
			return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String studentiIscrittiDatoNomeCorso(String codCorso){
		Connection conn;
		try {
			conn = DriverManager.getConnection(jdbcURL);
			Statement st=conn.createStatement();
			String sql="select distinct matricola, nome, cognome, CDS  from studente  where matricola in (select matricola from iscrizione where codins=\""+codCorso+"\")";
			ResultSet res=st.executeQuery(sql);
			String risultato="";
			while(res.next()){
				risultato+=""+res.getInt("studente.matricola")+" "+res.getString("nome")+" "+res.getString("cognome")+" "+res.getString("CDS")+"\n";
			}
			res.close();
			conn.close();
			return risultato;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String studentiIscrittiDataMatricola(int matricola){
		Connection conn;
		try {
			conn = DriverManager.getConnection(jdbcURL);
			Statement st=conn.createStatement();
			String sql="select codins, crediti, nome, pd from corso where codins in (select codins from iscrizione where matricola=\""+matricola+"\")";
			ResultSet res=st.executeQuery(sql);
			String risultato="";
			while(res.next()){
				risultato+=""+res.getString("codins")+" "+res.getString("crediti")+" "+res.getString("nome")+" "+res.getString("pd")+"\n";
			}
			res.close();
			conn.close();
			return risultato;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean iscriviStudenteACorso(int matricola, String codins){
		Connection conn;
		try {
			conn = DriverManager.getConnection(jdbcURL);
			Statement st=conn.createStatement();
			String sql="INSERT INTO `iscritticorsi`.`iscrizione` (`matricola`, `codins`) VALUES ("+matricola+", '"+codins+"')";
			int res=st.executeUpdate(sql);
			conn.close();
			
			if(res==1)
				return true;
			else
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
