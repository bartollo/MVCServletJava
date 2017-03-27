/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Veiculo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author bartollo_user
 */
public class VeiculoModel {


    private Connection conn;
    private Statement comando;
       
    public VeiculoModel(){                
        conn = ConnectionFactory.getConnection();
                        
    }
    
    public ArrayList<Veiculo> selectAll(){ 
        
        //criar a lista resultante da consulta a ser retornada.
        ArrayList<Veiculo> pessoas = new ArrayList<Veiculo>();
        
        try {
                //cria o comando.
                comando = conn.createStatement();
                //executa a consulta
                ResultSet resultado = comando.executeQuery("select Veiculo.id as id, placa, marca, pessoa_id, nome from Veiculo inner join Pessoa on Pessoa.id=Veiculo.pessoa_id");
                //acrescentando � lista.
                while(resultado.next())
                {
                        Veiculo v = new Veiculo();
                        v.setId(resultado.getInt("id"));
                        v.setPlaca(resultado.getString("placa"));                                                                
                        v.setMarca(resultado.getString("marca"));
                        v.setPessoa_id(resultado.getInt("pessoa_id"));
                        v.setNome(resultado.getString("nome"));
                //        p.setId(1);
                //        p.setNome("teste");

                        pessoas.add(v);
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
    
    public Veiculo select(int id){  
        Veiculo veiculo = new Veiculo();

        try {
                //cria o comando.
                comando = conn.createStatement();
                //executa a consulta
                ResultSet resultado = comando.executeQuery("select Veiculo.id, placa, marca, pessoa_id, nome from Veiculo inner join Pessoa on Pessoa.id=Veiculo.pessoa_id where Veiculo.id="+id);
                               
                //acrescentando � lista.
                while(resultado.next())
                {
                        veiculo.setId(resultado.getInt("id"));
                        veiculo.setPlaca(resultado.getString("placa")); 
                        veiculo.setMarca(resultado.getString("marca"));
                        veiculo.setPessoa_id(resultado.getInt("pessoa_id"));                                                
                        veiculo.setNome(resultado.getString("nome"));

                }
                //fecha comando e conex�o.
                comando.close();
                conn.close();
        } catch (SQLException e) {
                e.printStackTrace();
        }
        //retorna a lista de empregados.
        return veiculo;
        
    }
    
    public int insert(Veiculo pessoa) throws SQLException {   
        
            String sql = "insert into Veiculo (placa,marca,pessoa_id) values (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
             
            ps.setString(1, pessoa.getPlaca());
            ps.setString(2, pessoa.getMarca());
            ps.setString(3, pessoa.getPessoa_id()+"");
                        
            return ps.executeUpdate();
                       
    }
    
    public boolean update(Veiculo veiculo) throws Exception{
        
        String sql = "update Veiculo set placa =? , marca=? , pessoa_id=? where id=?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, veiculo.getPlaca());
        ps.setString(2, veiculo.getMarca());
        ps.setString(3, veiculo.getPessoa_id()+"");
        ps.setString(4, veiculo.getId()+"");

        try{ 
            ps.execute();
           } catch(Exception e) {
            return false;
           }

        return true;
    }
    
    public boolean delete(Veiculo pessoa) throws Exception{     
             String sql = "delete from Veiculo where id = ?";
             PreparedStatement ps = conn.prepareStatement(sql);
             
             ps.setInt(1, pessoa.getId());
             
             try{ 
                 ps.execute();
                } catch(Exception e) {
                 return false;
                }
             
             return true;
    }
   
    
}

