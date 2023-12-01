<%@ page import="org.iesvdm.jsp_servlet_jdbc.model.Socio" %><%--CÓDIGO REUTILIZADO DE formularioSocioB--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="estilos.css" />
</head>
<body class="bg-light">
<div class="container bg-white">
    <div class="row border-bottom">
        <div class="col-12 h2">Edite los datos del nuevo socio</div>
    </div>
</div>
<div class="container bg-light">
    <%
        Socio socioAEditar = (Socio)request.getAttribute("socioAEditar");

        if (socioAEditar != null) {
    %>
        <form method="post" action="EditarSociosServlet">
            <div class="row body mt-2">
                <div class="col-md-6 align-self-center">Socio ID</div>
                <div class="col-md-6 align-self-center"><input type="hidden" name="socioID" value="<%=socioAEditar.getSocioId()%>"/></div>
            </div>


            <div class="row body mt-2">
                <div class="col-md-6 align-self-center">Nombre</div>
                <div class="col-md-6 align-self-center"><input type="text" name="nombre" value="<%=socioAEditar.getNombre()%>"/></div>
            </div>
            <div class="row body mt-2">
                <div class="col-md-6 align-self-center">Estatura</div>
                <div class="col-md-6 align-self-center"><input type="text" name="estatura" value="<%=socioAEditar.getEstatura()%>"/></div>
            </div>
            <div class="row body mt-2">
                <div class="col-md-6 align-self-center">Edad</div>
                <div class="col-md-6 align-self-center"><input type="text" name="edad" value="<%=socioAEditar.getEdad()%>"/></div>
            </div>
            <div class="row body mt-2">
                <div class="col-md-6 align-self-center">Localidad</div>
                <div class="col-md-6 align-self-center"><input type="text" name="localidad" value="<%=socioAEditar.getLocalidad()%>"/></div>
            </div>
            <div class="row mt-2">
                <div class="col-md-6">
                    &nbsp;
                </div>
                <div class="col-md-6 align-self-center">
                    <input class="btn btn-primary" type="submit" value="Aceptar">
                </div>
            </div>
        </form>
    <%
        } else {
            System.out.println("SOCIO A EDITAR NULO");
        }
    %>
    <%
        //                                                 v---- RECOGER MENSAJE DE ERROR DEL ÁMBITO request
        String error = (String) request.getAttribute("error");
//            v---- SI ESTÁ PRESENTE INFORMAR DEL ERROR
        if (error != null) {
    %>
    <div class="row mt-2">
        <div class="col-6">
            <div class="alert alert-danger alert-dismissible fade show">
                <strong>Error!</strong> <%=error%>
                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
            </div>
        </div>
    </div>
    <%
        }
    %>
</div>
<script src="js/bootstrap.bundle.js" ></script>
</body>
</html>