<%-- 
    Document   : Producto
    Created on : 15 oct 2023, 20:37:15
    Author     : AJ-Barreto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "Modelo.Empleado" %>
<% HttpSession sesion=request.getSession();
Empleado emp = (Empleado) sesion.getAttribute("usuario");
if(emp!=null){
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <title>JSP Page</title>
    </head>
    <body style="background-color:powderblue;">
        <div>
            <h1 style="text-align:center;">Bienvenido a nuestra página</h1>
            <br>
            <center><img src="https://www.shutterstock.com/image-vector/spanish-language-vector-template-welcome-260nw-1792430023.jpg" alt="BIENVENIDO" ></center>
            <br>
            <p style="text-align:center;">
                Aquí podrás ingresar un empleado, generar una venta e imprimir de una vez el recibo o la factura de la venta.
            </p>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
    </body>
</html>
<%
    } else{
request.getRequestDispatcher("Controlador?menu=Principal").forward(request, response);
}
%>