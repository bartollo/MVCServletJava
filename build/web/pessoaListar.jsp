<%-- 
    Document   : veiculoListar
    Created on : 19/03/2017, 16:15:25
    Author     : bartollo_user
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, shrink-to-fit=no, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Sistema de cadastramento de veículos e proprietários</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

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

    <div class="row">


        <div class="col-md-12">
            <h1>Lista de Pessoas      </h1>
            <c:if test="${retorno == 1}">                           
            <div class="alert alert-success">
                <a href="#" class="close" data-dismiss="alert" aria-label="close" title="close">×</a>
                <strong>Successo!</strong> Registro inserido!
            </div>
            </c:if>    
            <c:if test="${retorno == 2}">                           
            <div class="alert alert-success">
                <a href="#" class="close" data-dismiss="alert" aria-label="close" title="close">×</a>
                <strong>Successo!</strong> Registro alterado!
            </div>
            </c:if>    
            
            <c:if test="${retorno < 0}">                                           
            <div class="alert alert-success">
                <a href="#" class="close" data-dismiss="alert" aria-label="close" title="close">×</a>
                <strong>Successo!</strong> Registro deletado!
            </div>
            </c:if>            
            <c:if test="${retorno == 0}">                           
            <div class="alert alert-danger">
                <a href="#" class="close" data-dismiss="alert" aria-label="close" title="close">×</a>
                <strong>Erro!</strong> Operação não realizada!
            </div>
            </c:if>             
            <button type="button" class="btn btn-success" onclick="javascript:window.location.href='pessoa?acao=add'">Adicionar</button>
            <div class="table-responsive">


                <table id="mytable" class="table table-bordred table-striped">

                    <thead>

                    <th>ID</th>
                    <th  width="75%">Nome</th>
                    <th>Editar</th>

                    <th>Deletar</th>
                    </thead>
                    <tbody>
                        
                    <c:forEach var="pessoa" items="${pessoas}" >
                        <tr>
                            <td>${pessoa.id}</td>
                            <td>${pessoa.nome}</td>
                            <td><p data-placement="top" data-toggle="tooltip" title="Edit" id="${pessoa.id}"><button class="btn btn-primary btn-xs" data-title="Edit" data-toggle="modal" data-target="#edit" onclick="javascript:window.location.href='pessoa?acao=upd&id=${pessoa.id}'"><span class="glyphicon glyphicon-pencil"></span></button></p></td>
                            <td><p data-placement="top" data-toggle="tooltip" title="Delete" id="${pessoa.id}"><button class="btn confirm-delete btn-danger btn-xs" data-title="Delete" data-toggle="modal" data-target="#delete" onclick="javascript:window.location.href='pessoa?acao=del&id=${pessoa.id}'"><span class="glyphicon glyphicon-trash"></span></button></p></td>
                        </tr>
                    </c:forEach>

                    </tbody>

                </table>
            </div>

        </div>
    </div>
</div>

</body>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
</html>