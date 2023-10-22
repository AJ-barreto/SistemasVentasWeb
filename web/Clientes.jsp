<%-- 
    Document   : Clientes
    Created on : 15 oct 2023, 20:37:36
    Author     : AJ-Barreto
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <title>Cliente</title>
    </head>
    <body style="background-color:powderblue;">
        <div style="text-align:center;">
            <img  src="https://cdn-icons-png.flaticon.com/512/686/686317.png" alt="Clientes" width="100"/>
            <h1 style="color:blue; font-family:verdana; text-align:center; ">SISTEMA DE CLIENTES</h1>
        </div>
        <div class="d-flex" style="align-items: center; justify-content: center; display: flex;">
            <div class="card" col-sm-6 style="border: 5px outset lightblue; background-color: lightblue; text-align: center; margin-left: 20px">
                <div class="card-body" style="border: 5px outset white;">
                    <form action="Controlador?menu=Clientes" method="POST">
                        <div class="form-group">
                            <label>Dni</label>
                            <input type="text" value="${cliente.getDni()}" name="txtDni" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Nombres</label>
                            <input type="text" value="${cliente.getNom()}" name="txtNombres" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Dirección</label>
                            <input type="text" value="${cliente.getDir()}" name="txtDir" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Estado</label>
                            <input type="text" value="${empleado.getEstado()}" name="txtestado" class="form-control">
                        </div>
                        <br>
                        <center>
                            <input type="submit" name="accion" value="Agregar" class="btn btn-info">
                            <input type="submit" name="accion" value="Actualizar" class="btn btn-success">
                        </center>
                    </form>
                </div>
            </div>
            <div class="col-ms-8" style="border: 5px outset saddlebrown; background-color: white; margin: 20px">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>DNI</th>
                            <th>NOMBRES</th>
                            <th>DIRECCIÓN</th>
                            <th>ESTADO</th>
                            <th>ACCIONES</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="cl" items="${clientes}">
                            <tr>
                                <td>${cl.getId()}</td>
                                <td>${cl.getDni()}</td>
                                <td>${cl.getNom()}</td>
                                <td>${cl.getDir()}</td>
                                <td>${cl.getEstado()}</td>
                                <td>
                                    <a class="btn btn-warning" href="Controlador?menu=Empleado&accion=Editar&id=${cl.getId()}">Editar</a>
                                    <a class="btn btn-danger" href="Controlador?menu=Empleado&accion=Delete&id=${cl.getId()}">Eliminar</a>
                                </td>
                            </tr>
                        </c:forEach>

                    </tbody>
                </table>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
    </body>
</html>
