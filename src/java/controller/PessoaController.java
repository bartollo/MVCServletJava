/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Pessoa;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author bartollo_user
 */
@WebServlet(name = "pessoa", urlPatterns = {"/pessoa"})
public class PessoaController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
/*        try (PrintWriter out = response.getWriter()) {
            RequestDispatcher r = request.getRequestDispatcher("/pessoaListar.jsp");  
            r.forward( request, response );            
        }*/

        entity.Pessoa p = new entity.Pessoa();        
        model.PessoaModel pm = new model.PessoaModel();
                
        if (request.getParameter("acao")==null) {
        // ação de listar todas as pessoas
                       
            ArrayList<Pessoa> pessoas = pm.selectAll();              
            //ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();                                             
            
      /*ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
        
        Pessoa p1 = new Pessoa();
        p1.setId(1);
        p1.setNome("teste");

        Pessoa p2 = new Pessoa();
        p2.setId(2);
        p2.setNome("teste 2");

        
        pessoas.add(p1);
        pessoas.add(p2);*/
      
           
            //request..setAttribute("pessoas",pessoas);            
            request.getSession().setAttribute("pessoas", pessoas);
            request.getRequestDispatcher("/pessoaListar.jsp").forward(request,response);
           
        }else{
        
            try{
                switch(request.getParameter("acao")){
                    case "add":                 
                        if (request.getParameter("nome")!=null) {                            
                            p.setNome(request.getParameter("nome").toString());      
                            boolean retorno=  this.addPessoa(p);
                            
                            RequestDispatcher r = request.getRequestDispatcher("/pessoaInserir.jsp");  

                            if(retorno){                                
                                request.getRequestDispatcher("/pessoa?acao=add&reg=ok").forward(request,response);
                            }else{                                                                                           
                              request.getSession().setAttribute("p", p);
                                r.forward( request, response ); 
                            }
                        }else{
                            RequestDispatcher r = request.getRequestDispatcher("/pessoaInserir.jsp");  
                            r.forward( request, response ); 
                        
                        break;

                    case "upd": 
                        p.setId(Integer.parseInt(request.getParameter("id")));                
                        p.setNome(request.getParameter("nome").toString());
                        this.updPessoa(p);

                        break;
                    case "del":                               
                        this.delPessoa(Integer.parseInt(request.getParameter("id")));                                

                        break;            

                }
            }catch(Exception e){

            }
        }

    }
    
    public boolean addPessoa(Pessoa pessoa){  
        
        
        model.PessoaModel pm = new model.PessoaModel();
           try{               
                return pm.insert(pessoa);                
                
            }catch(Exception e){
                e.printStackTrace();
            }
        
            return false;
    }
    
    public void delPessoa(int pessoa_id){
        
    }
    
    public void updPessoa(entity.Pessoa pessoa){
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
