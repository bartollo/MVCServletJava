/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author bartollo_user
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionFactory {

	private static final String login = "navegart_carros";
	private static final String senha = "carros123";
	
	public static Connection getConnection() {  
		Connection conn = null;
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();          
			conn = DriverManager.getConnection("jdbc:mysql://navegart.com.br/navegart_carros", login, senha);
                /*
                        Statement comando = conn.createStatement();
                        comando.executeQuery("use carros"); //id,nome from Pessoa");*/
                        
		}
		catch(SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e){                    
                    e.printStackTrace();
		}
		return conn;

	}
    
    
    
}
