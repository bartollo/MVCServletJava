/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Pessoa;
import java.util.ArrayList;
import model.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 *
 * @author bartollo_user
 */
public class PessoaModel {
    
    private Connection conn;
    private Statement comando;
       
    public PessoaModel(){                
        conn = ConnectionFactory.getConnection();
                        
    }
    
    public ArrayList<Pessoa> selectAll(){ 
        
        //criar a lista resultante da consulta a ser retornada.
        ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
        
        try {
                //cria o comando.
                comando = conn.createStatement();
                //executa a consulta
                ResultSet resultado = comando.executeQuery("select id,nome from Pessoa");
                //acrescentando � lista.
                while(resultado.next())
                {
                        Pessoa p = new Pessoa();
                        p.setId(resultado.getInt("id"));
                        p.setNome(resultado.getString("nome"));                                                                
                //        p.setId(1);
                //        p.setNome("teste");

                        pessoas.add(p);
                }
                //fecha comando e conex�o.
                comando.close();
                conn.close();
        } catch (SQLException e) {
                e.getSQLState();
        }
        //retorna a lista de empregados.*/
        return pessoas;
	
  
    }
    
    public Pessoa select(int id){  
        Pessoa pessoa = new Pessoa();

        try {
                //cria o comando.
                comando = conn.createStatement();
                //executa a consulta
                ResultSet resultado = comando.executeQuery("select * from Pessoa where id="+id);
                //acrescentando � lista.
                while(resultado.next())
                {
                        pessoa.setId(resultado.getInt("id"));
                        pessoa.setNome(resultado.getString("nome"));                                                                

                }
                //fecha comando e conex�o.
                comando.close();
                conn.close();
        } catch (SQLException e) {
                e.printStackTrace();
        }
        //retorna a lista de empregados.
        return pessoa;
        
    }
    
    public boolean insert(Pessoa pessoa) throws SQLException {   
        
            String sql = "insert into Pessoa (nome) values (?)";
            PreparedStatement ps = conn.prepareStatement(sql);
             
            ps.setString(1, pessoa.getNome());
             
            return ps.execute();
    }
    
    public void update(Pessoa pessoa) throws Exception{
        
        String sql = "update Pessoa set nome =? where id=?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, pessoa.getNome());

        ps.execute(); 
    }
    
    public void delete(Pessoa pessoa) throws Exception{     
             String sql = "delete from Pessoa where id = ?";
             PreparedStatement ps = conn.prepareStatement(sql);
             
             ps.setInt(1, pessoa.getId());
             
             ps.execute();        
    }
   
    
}
