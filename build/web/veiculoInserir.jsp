<%-- 
    Document   : pessoaInserir
    Created on : 19/03/2017, 18:13:06
    Author     : bartollo_user
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <!-- Bootstrap Core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <title>Cadastro de Veículo</title>
        <!-- Custom CSS -->
        <link href="css/simple-sidebar.css" rel="stylesheet">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

    </head>

    <body>


        <div id="wrapper" class="toggled">

            <!-- Sidebar -->
            <div id="sidebar-wrapper">
                <ul class="sidebar-nav">
                    <li class="sidebar-brand">
                        <a href="#">
                            Menu Principal
                        </a>
                    </li>
                    <li>
                        <a href="pessoa">Pessoa</a>
                    </li>
                    <li>
                        <a href="veiculo">Veículo</a>
                    </li>
                </ul>
            </div>
            <!-- /#sidebar-wrapper -->

            <!-- Page Content -->
            <div id="page-content-wrapper">    
                <div class="container">                    
                    <form class="form-horizontal" action="veiculo" method="post">
                        <fieldset>

                            <!-- Form Name -->
                            <legend>Cadastro de Veículo</legend>      
                                
                                <input type="hidden" name="acao" value="<%=request.getParameter("acao")%>">
                                
                                <!-- Text input-->
                                <div class="form-group">
                                    <label class="col-md-1 control-label" for="textinput">ID</label>  
                                    <div class="col-md-4">
                                        <input name="id-view" class="form-control input-md" id="id" type="text" placeholder="" disabled="disabled" value="">
                                    </div>
                                </div>                            

                                <!-- Text input-->
                                <div class="form-group">
                                    <label class="col-md-1 control-label" for="textinput">Placa</label>  
                                    <div class="col-md-4">
                                        <input name="placa" class="form-control input-md" id="placa" type="text" placeholder="Ex: OIU-7777" value="">
                                    </div>
                                </div>


                                <!-- Text input-->
                                <div class="form-group">
                                    <label class="col-md-1 control-label" for="textinput">Marca</label>  
                                    <div class="col-md-4">
                                        <input name="marca" class="form-control input-md" id="marca" type="text" placeholder="Ex: Hyundai Hb20" value="">
                                    </div>
                                </div>


                                <!-- Text input-->
                                <div class="form-group">
                                    <label class="col-md-1 control-label" for="textinput">Proprietário</label>  
                                    <div class="col-md-4">
                                    <select name="pessoa_id" class="form-control" id="pessoa_id">
                                        <option value="0">Selecione o proprietário.</option>
                                        
                                        <c:forEach items="${pessoas}" var="p">
                                            <option value="${p.id}">${p.nome}</option>
                                        </c:forEach>
                                    </select>
                                   
                                    </div>
                                </div>                            

                                <!-- Button (Double) -->
                                <div class="form-group">
                                    <div class="col-md-8">
                                        <input type="submit" class="btn btn-success" id="button1id" value="Salvar">
                                        <a href="/pessoa" class="btn btn-primary" id="button2id">Voltar</a>
                                    </div>
                                </div>                          
                        </fieldset>
                    </form>

                </div>

            </div>
        </div>
    </body>
</html>
