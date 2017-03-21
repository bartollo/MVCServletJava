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

        <title>Cadastro de Pessoa</title>
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
                    <form class="form-horizontal" action="pessoa" method="post">
                        <fieldset>

                            <!-- Form Name -->
                            <legend>Cadastro de Pessoa</legend>  
                            <c:if test="${p.nome != null}">                           
                            <div class="alert alert-success" style="width:80%">
                                <a href="#" class="close" data-dismiss="alert" aria-label="close" title="close">×</a>
                                <strong>Successo!</strong> Registro inserido!
                            </div>
                            </c:if>
                                
                                <input type="hidden" name="acao" value="add">
                                <!-- Text input-->
                                <div class="form-group">
                                    <label class="col-md-1 control-label" for="textinput">ID</label>  
                                    <div class="col-md-4">
                                        <input name="id" class="form-control input-md" id="textinput" type="text" placeholder="" disabled="disabled" value="<c:out value="${p.id}"/>">
                                    </div>
                                </div>                            

                                <!-- Text input-->
                                <div class="form-group">
                                    <label class="col-md-1 control-label" for="textinput">Nome</label>  
                                    <div class="col-md-4">
                                        <input name="nome" class="form-control input-md" id="textinput" type="text" placeholder="Nome da Pessoa" value="<c:out value="${p.nome}"/>"">
                                    </div>
                                </div>


                                <!-- Button (Double) -->
                                <div class="form-group">
                                    <div class="col-md-8">
                                        <input type="submit" class="btn btn-success" id="button1id" value="Salvar">
                                        <button class="btn btn-danger" id="button2id">Cancelar</button>                                    
                                        <button class="btn btn-primary" id="button2id">Voltar</button>
                                    </div>
                                </div>                          
                        </fieldset>
                    </form>

                </div>

            </div>
        </div>
    </body>
</html>
