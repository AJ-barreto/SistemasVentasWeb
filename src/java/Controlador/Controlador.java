/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Cliente;
import Modelo.ClienteDAO;
import Modelo.Empleado;
import Modelo.EmpleadoDAO;
import Modelo.Producto;
import Modelo.ProductoDAO;
import Modelo.Venta;
import Modelo.VentaDAO;
import config.GenerarSerie;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

/**
 *
 * @author AJ-Barreto
 */
public class Controlador extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Empleado em = new Empleado();
    EmpleadoDAO edao = new EmpleadoDAO();
    //cliente usado en empleado
    Cliente c = new Cliente();
    Cliente cl = new Cliente();
    //cliente usado en cliente
    Cliente cli = new Cliente();
    ClienteDAO cdao = new ClienteDAO();
    //producto usado en venta
    Producto p = new Producto();
    //producto usado en producto
    Producto pr = new Producto();
    ProductoDAO pdao = new ProductoDAO();
    Venta v = new Venta();
    List<Venta> lista = new ArrayList<>();
    int item;
    int cod;
    String descripcion;
    double precio;
    int cant;
    double subtotal;
    double totalPagar;
    int ide;
    String numeroserie;
    VentaDAO vdao = new VentaDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        HttpSession sesion = request.getSession();
        System.out.println("sesio numero en CONTROLADOR: "+sesion.getId());
        Empleado us = (Empleado) sesion.getAttribute("usuario");
        System.out.println("este es US en CONTROLADOR: "+us);
        if(us!=null)
        {
            System.out.println("este es US en CONTROLADOR IF: "+us);
        if (menu.equals("Principal")) {
            System.out.println("sesio numero CONTROLADOR PRINCIPAL: "+sesion.getId());
            request.getRequestDispatcher("Principal.jsp").forward(request, response);
        }
        if (menu.equals("Home")) {
            request.getRequestDispatcher("Home.jsp").forward(request, response);
        }
        if (menu.equals("Empleado")) {
            switch (accion) {
                case "Listar":
                    List lista = edao.listar();
                    request.setAttribute("empleados", lista);
                    break;
                case "Agregar":
                    String dni = request.getParameter("txtDni");
                    String contra = request.getParameter("txtContra");
                    String nom = request.getParameter("txtNombres");
                    String tel = request.getParameter("txtTel");
                    String est = request.getParameter("txtestado");
                    String user = request.getParameter("txtUsuario");
                    String contraSegura = asegurarClave(contra);
                    em.setDni(dni);
                    em.setContra(contraSegura);
                    em.setNom(nom);
                    em.setTel(tel);
                    em.setEstado(est);
                    em.setUser(user);
                    edao.agregar(em);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    System.out.println("ENTRA EN ACTUALIZAR 1");
                    ide = Integer.parseInt(request.getParameter("id"));
                    Empleado e = edao.listarId(ide);
                    request.setAttribute("empleado", e);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    System.out.println("ENTRA EN ACTUALIZAR 2");
                    String dni1 = request.getParameter("txtDni");
                    String contra1 = request.getParameter("txtContra");
                    String nom1 = request.getParameter("txtNombres");
                    String tel1 = request.getParameter("txtTel");
                    String est1 = request.getParameter("txtestado");
                    String user1 = request.getParameter("txtUsuario");
                    String contraSegura1 = asegurarClave(contra1);
                    em.setDni(dni1);
                    em.setContra(contraSegura1);
                    em.setNom(nom1);
                    em.setTel(tel1);
                    em.setEstado(est1);
                    em.setUser(user1);
                    em.setId(ide);
                    edao.actualizar(em);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;
                case "Delete":
                    ide = Integer.parseInt(request.getParameter("id"));
                    edao.delete(ide);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;

                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("Empleado.jsp").forward(request, response);
        }
        if (menu.equals("Clientes")) {
            switch (accion) {
                case "Listar":
                    List lista = cdao.listar();
                    request.setAttribute("clientes", lista);
                    break;
                case "Agregar":
                    String dni = request.getParameter("txtDni");
                    String nom = request.getParameter("txtNombres");
                    String dir = request.getParameter("txtDir");
                    String est = request.getParameter("txtestado");
                    cli.setDni(dni);
                    cli.setNom(nom);
                    cli.setDir(dir);
                    cli.setEstado(est);
                    cdao.agregar(cli);
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    System.out.println("ENTRA EN ACTUALIZAR 1");
                    ide = Integer.parseInt(request.getParameter("id"));
                    Cliente client = cdao.listarId(ide);
                    request.setAttribute("cliente", client);
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    System.out.println("ENTRA EN ACTUALIZAR 2");
                    String dni1 = request.getParameter("txtDni");
                    String nom1 = request.getParameter("txtNombres");
                    String dir1 = request.getParameter("txtDir");
                    String est1 = request.getParameter("txtestado");
                    cli.setDni(dni1);
                    cli.setNom(nom1);
                    cli.setDir(dir1);
                    cli.setEstado(est1);
                    cli.setId(ide);
                    cdao.actualizar(cli);
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    break;
                case "Delete":
                    ide = Integer.parseInt(request.getParameter("id"));
                    cdao.delete(ide);
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    break;

                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("Clientes.jsp").forward(request, response);
        }
        if (menu.equals("Producto")) {
            switch (accion) {
                case "Listar":
                    List lista = pdao.listar();
                    request.setAttribute("productos", lista);
                    break;
                case "Agregar":
                    String nom = request.getParameter("txtNombres");
                    int precio = Integer.parseInt(request.getParameter("txtPrecio"));
                    int stock = Integer.parseInt(request.getParameter("txtStock"));
                    String est = request.getParameter("txtestado");
                    pr.setNom(nom);
                    pr.setPrecio(precio);
                    pr.setStock(stock);
                    pr.setEstado(est);
                    pdao.agregar(pr);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    System.out.println("ENTRA EN ACTUALIZAR 1");
                    ide = Integer.parseInt(request.getParameter("id"));
                    Producto pro = pdao.listarId(ide);
                    request.setAttribute("producto", pro);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    System.out.println("ENTRA EN ACTUALIZAR 2");
                    String nom1 = request.getParameter("txtNombres");
                    int precio1 = Integer.parseInt(request.getParameter("txtPrecio"));
                    int stock1 = Integer.parseInt(request.getParameter("txtStock"));
                    String est1 = request.getParameter("txtestado");
                    pr.setNom(nom1);
                    pr.setPrecio(precio1);
                    pr.setStock(stock1);
                    pr.setEstado(est1);
                    pr.setId(ide);
                    pdao.actualizar(pr);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;
                case "Delete":
                    ide = Integer.parseInt(request.getParameter("id"));
                    pdao.delete(ide);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;

                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("Producto.jsp").forward(request, response);
        }
        if (menu.equals("RegistrarVenta")) {
            switch (accion) {
                case "BuscarCliente":
                    request.setAttribute("nserie", numeroserie);
                    String dni = request.getParameter("codigocliente");
                    c.setDni(dni);
                    cl = cdao.buscar(dni);
                    request.setAttribute("c", cl);
                    break;

                case "BuscarProducto":
                    request.setAttribute("nserie", numeroserie);
                    int id = Integer.parseInt(request.getParameter("codigoproducto"));
                    p = pdao.listarId(id);
                    request.setAttribute("c", cl);
                    request.setAttribute("producto", p);
                    request.setAttribute("lista", lista);
                    request.setAttribute("totalpagar", totalPagar);
                    break;
                case "Agregar":
                    request.setAttribute("nserie", numeroserie);
                    request.setAttribute("c", cl);
                    totalPagar = 0.0;
                    item = item + 1;
                    cod = p.getId();
                    descripcion = request.getParameter("nomproducto");
                    precio = Double.parseDouble(request.getParameter("precio"));
                    cant = Integer.parseInt(request.getParameter("cant"));
                    subtotal = precio * cant;
                    v = new Venta();
                    v.setItem(item);
                    v.setIdproducto(cod);
                    v.setDescripcionP(descripcion);
                    v.setPrecio(precio);
                    v.setCantidad(cant);
                    v.setSubtotal(subtotal);
                    lista.add(v);
                    for (int i = 0; i < lista.size(); i++) {
                        totalPagar = totalPagar + lista.get(i).getSubtotal();
                    }
                    request.setAttribute("totalpagar", totalPagar);
                    request.setAttribute("lista", lista);
                    break;
                case "GenerarVenta":
                    request.setAttribute("nserie", numeroserie);
                    //ACTUALIZAR STOCK
                    for (int i = 0; i < lista.size(); i++) {
                        Producto pr = new Producto();
                        int cantidad = lista.get(i).getCantidad();
                        int idproducto = lista.get(i).getIdproducto();
                        ProductoDAO aO = new ProductoDAO();
                        pr = aO.buscar(idproducto);
                        int sac = pr.getStock() - cantidad;
                        aO.actualizarStock(idproducto, sac);
                    }
                    Date date = new Date();  
                    //GUARDAR VENTA
                    v.setIdcliente(cl.getId());
                    v.setIdempleado(2);
                    v.setNumserie(numeroserie);
                    v.setFecha("2019-06-14");
                    v.setMonto(totalPagar);
                    v.setEstado("1");
                    System.out.println("ESTE ES EL OBJETO VENTA: " + v.getId() + " " + v.getIdcliente() + " " + v.getIdempleado() + " " + v.getNumserie() + " " + v.getFecha() + " " + v.getMonto() + " " + v.getEstado());
                    System.out.println("VA A GUARDAR VENTA");
                    vdao.guardarVentas(v);
                    //GUARDAR DETALLE VENTA
                    System.out.println("ESTE ES VDAOVENAS: " + vdao.IdVentas());
                    int idv = Integer.parseInt(vdao.IdVentas());
                    for (int i = 0; i < lista.size(); i++) {
                        v = new Venta();
                        v.setId(idv);
                        v.setIdproducto(lista.get(i).getIdproducto());
                        v.setCantidad(lista.get(i).getCantidad());
                        v.setPrecio(lista.get(i).getPrecio());
                        vdao.guardarDetalleventas(v);
                    }
                    break;
                default:
                    numeroserie = vdao.GenerarSerie();
                    if (numeroserie == null) {
                        numeroserie = "00000001";
                        request.setAttribute("nserie", numeroserie);
                    } else {
                        int incrementar = Integer.parseInt(numeroserie);
                        GenerarSerie gs = new GenerarSerie();
                        numeroserie = gs.NumeroSerie(incrementar);
                        request.setAttribute("nserie", numeroserie);
                    }
                    request.setAttribute("nserie", numeroserie);
                    request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
            }
            request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
        } 
    } else{
    request.getRequestDispatcher("index.jsp").forward(request, response);
}
    }
    
    private String asegurarClave(String textoClaro){
        String claveSha = null;
        
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            sha256.update(textoClaro.getBytes());
            claveSha = Base64.getEncoder().encodeToString(sha256.digest());
            System.out.println("CLAVESHA = "+claveSha);
            System.out.println("LONGITUD: "+claveSha.length());
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("ERROR EN INSTANCIAR 256"+ ex.getMessage());
        }
        return claveSha;
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
