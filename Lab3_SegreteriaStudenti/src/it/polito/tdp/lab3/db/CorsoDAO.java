package it.polito.tdp.lab3.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab3.model.Studente;

public class CorsoDAO {

	private List<String> corsi=new LinkedList<String>();
	
	private String jdbcURL="jdbc:mysql://localhost/iscritticorsi?user=root";
	
	public List<String> nomeCorso(){
		
		Connection conn;
		try {
			conn = DriverManager.getConnection(jdbcURL);
			Statement st=conn.createStatement();
			String sql="select nome from corso"; 
			ResultSet res=st.executeQuery(sql); //se inserisco devo fare executeUpdate()
			while(res.next()){
			corsi.add(res.getString("nome")); //nome e' il nome della colonna nella tabella CORSO in cui ci sono i nomi dei corsi
			}
			res.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return corsi;
	}
	
	public Studente studente(String matricola){
		Connection conn;
		try {
			conn = DriverManager.getConnection(jdbcURL);
			Statement st=conn.createStatement();
			int i=Integer.parseInt(matricola);
			String sql="select nome, cognome from studente where matricola=\""+i+"\""; 
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
}
