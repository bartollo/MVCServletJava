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
                            this.clear(request);
        
        response.setContentType("text/html;charset=UTF-8");

        entity.Pessoa p;
        model.PessoaModel pm = new model.PessoaModel();
                
        if (request.getParameter("acao")==null) {
            // ação de listar todas as pessoas
            this.clear(request); 
            ArrayList<Pessoa> pessoas = pm.selectAll();              
 
            request.getSession().setAttribute("pessoas", pessoas);
            request.getRequestDispatcher("/pessoaListar.jsp").forward(request,response);
           
        }else{
            
                p= new Pessoa();
                
                switch(request.getParameter("acao")){
                    case "add":                                                                              
                        if (request.getParameter("nome")!=null) {                            
                            p.setNome(request.getParameter("nome").toString());
                            this.clear(request);

                            if(this.addPessoa(p)){                                
                                request.getSession().setAttribute("pessoas", pm.selectAll());
                                request.getSession().setAttribute("retorno", 1);
                                request.getRequestDispatcher("/pessoaListar.jsp").forward(request,response); 
                                this.clear(request); 
                                
                            }else{                                
                                request.getSession().setAttribute("retorno", 0);
                                request.getRequestDispatcher("/pessoaListar.jsp").forward(request,response); 
                                this.clear(request); 
                                                               
                            }
                            request.getRequestDispatcher("/pessoaInserir.jsp").forward(request,response);
                            this.clear(request); 
     
                        }else{
                            this.clear(request);                                
                            request.getRequestDispatcher("/pessoaInserir.jsp").forward( request, response ); 
                            this.clear(request); 

                        }
                        break;

                    case "upd": 
                        if (request.getParameter("nome")!=null) {                            
                            p.setId(Integer.parseInt(request.getParameter("id")));
                            p.setNome(request.getParameter("nome").toString());
                            this.clear(request);
                            
                            if(this.updPessoa(p)){                                                                
                                request.getSession().setAttribute("pessoas", pm.selectAll());
                                request.getSession().setAttribute("retorno", 2);
                                request.getRequestDispatcher("/pessoaListar.jsp").forward( request, response );
                                
                            }else{
                                request.getSession().setAttribute("retorno", 0);
                                request.getRequestDispatcher("/pessoaListar.jsp").forward( request, response );
                                
                            }                           
                                                        
                        }else{
                            this.clear(request);
                            
                            p = pm.select(Integer.parseInt(request.getParameter("id")));
                            request.getSession().setAttribute("p", p);
                            
                            request.getRequestDispatcher("/pessoaEditar.jsp").forward( request, response );                             
                   
                        }

                        break;
                    case "del":  
                        p.setId(Integer.parseInt(request.getParameter("id")));
                        this.clear(request);
                        if(this.delPessoa(p)){    
                            
                            request.getSession().setAttribute("pessoas", pm.selectAll());
                            request.getSession().setAttribute("retorno", -1);
                            request.getRequestDispatcher("/pessoaListar.jsp").forward( request, response );
                        }else{
                            
                            request.getSession().setAttribute("retorno", 0);
                            request.getRequestDispatcher("/pessoaListar.jsp").forward( request, response );
                            
                        }
                        break;            

                
            
            }
        }

    }
    
    public void clear(HttpServletRequest request){
       
        request.removeAttribute("p");
        request.removeAttribute("nome");
        request.removeAttribute("id");
        request.removeAttribute("retorno");
    }
    
    public boolean addPessoa(Pessoa pessoa){  
        
        
        model.PessoaModel pm = new model.PessoaModel();
           try{               
                if(pm.insert(pessoa)>0){
                    return true;
                }else{
                    return false;
                }                
                
            }catch(Exception e){
                e.printStackTrace();
            }
        
            return false;
    }
    
    public boolean delPessoa(Pessoa pessoa){
        
        
        model.PessoaModel pm = new model.PessoaModel();
           try{               
                if(pm.delete(pessoa)){
                    return true;
                }else{
                    return false;
                }                
                
            }catch(Exception e){
                e.printStackTrace();
            }
        
            return false;        
        
    }
    
    public boolean updPessoa(Pessoa pessoa){
        
        model.PessoaModel pm = new model.PessoaModel();
           try{               
                if(pm.update(pessoa)){
                    return true;
                }else{
                    return false;
                }                
                
            }catch(Exception e){
                e.printStackTrace();
            }
        
            return false;         
        
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
