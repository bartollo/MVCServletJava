/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Pessoa;
import entity.Veiculo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "veiculo", urlPatterns = {"/veiculo"})
public class VeiculoController extends HttpServlet {

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

        entity.Veiculo v;
        model.VeiculoModel vm = new model.VeiculoModel();
                
        if (request.getParameter("acao")==null) {
            // ação de listar todas as pessoas
            this.clear(request); 
            ArrayList<Veiculo> veiculos = vm.selectAll();              
 
            request.getSession().setAttribute("veiculos", veiculos);
            request.getRequestDispatcher("/veiculoListar.jsp").forward(request,response);
           
        }else{
            
                v= new Veiculo();
                            
                entity.Pessoa p;
                model.PessoaModel pm = new model.PessoaModel();
                ArrayList<Pessoa> pessoas = pm.selectAll();              
                request.getSession().setAttribute("pessoas", pessoas);                            
                
                
                switch(request.getParameter("acao")){
                    case "add":                                                                              
                        if (request.getParameter("placa")!=null) {  
                            v.setPlaca(request.getParameter("placa").toString());
                            v.setMarca(request.getParameter("marca").toString());
                            v.setPessoa_id(Integer.parseInt(request.getParameter("pessoa_id")));

                            this.clear(request);

                            if(this.addVeiculo(v)){                                
                                request.getSession().setAttribute("veiculos", vm.selectAll());
                                request.getSession().setAttribute("retorno", 1);
                                request.getRequestDispatcher("/veiculoListar.jsp").forward(request,response); 
                            }else{                                
                                request.getSession().setAttribute("retorno", 0);
                                request.getRequestDispatcher("/veiculoListar.jsp").forward(request,response); 
                                                               
                            }
                            request.getRequestDispatcher("/veiculoInserir.jsp").forward(request,response);
                         
                        }else{
                            this.clear(request);                                
                            request.getRequestDispatcher("/veiculoInserir.jsp").forward( request, response ); 
                        }
                        break;

                    case "upd": 
                        if (request.getParameter("placa")!=null) {                            
                            v.setId(Integer.parseInt(request.getParameter("id")));
                            v.setPlaca(request.getParameter("placa").toString());
                            v.setMarca(request.getParameter("marca").toString());
                            v.setPessoa_id(Integer.parseInt(request.getParameter("pessoa_id")));

                            this.clear(request);
                            
                            if(this.updVeiculo(v)){                                                                
                                request.getSession().setAttribute("veiculos", vm.selectAll());
                                request.getSession().setAttribute("retorno", 2);
                                request.getRequestDispatcher("/veiculoListar.jsp").forward( request, response );
                                
                            }else{
                                request.getSession().setAttribute("retorno", 0);
                                request.getRequestDispatcher("/veiculoListar.jsp").forward( request, response );
                                
                            }                           
                                                        
                        }else{
                            this.clear(request);
                            
                            v = vm.select(Integer.parseInt(request.getParameter("id")));
                            request.getSession().setAttribute("v", v);
                            
                            System.out.println(v.getMarca());
                            
                            request.getRequestDispatcher("/veiculoEditar.jsp").forward( request, response );                             
                   
                        }

                        break;
                    case "del":  
                        v.setId(Integer.parseInt(request.getParameter("id")));
                        this.clear(request);
                        if(this.delVeiculo(v)){    
                            
                            request.getSession().setAttribute("veiculos", vm.selectAll());
                            request.getSession().setAttribute("retorno", -1);
                            request.getRequestDispatcher("/veiculoListar.jsp").forward( request, response );
                        }else{
                            
                            request.getSession().setAttribute("retorno", 0);
                            request.getRequestDispatcher("/veiculoListar.jsp").forward( request, response );
                            
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
    
    public boolean addVeiculo(Veiculo veiculo){  
        
        
        model.VeiculoModel vm = new model.VeiculoModel();
           try{               
                if(vm.insert(veiculo)>0){
                    return true;
                }else{
                    return false;
                }                
                
            }catch(Exception e){
                e.printStackTrace();
            }
        
            return false;
    }
    
    public boolean delVeiculo(Veiculo veiculo){
        
        
        model.VeiculoModel vm = new model.VeiculoModel();
           try{               
                if(vm.delete(veiculo)){
                    return true;
                }else{
                    return false;
                }                
                
            }catch(Exception e){
                e.printStackTrace();
            }
        
            return false;        
        
    }
    
    public boolean updVeiculo(Veiculo veiculo){
        
        model.VeiculoModel vm = new model.VeiculoModel();
           try{               
                if(vm.update(veiculo)){
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
