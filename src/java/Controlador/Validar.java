/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Empleado;
import Modelo.EmpleadoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 *
 * @author AJ-Barreto
 */
public class Validar extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    EmpleadoDAO edao=new EmpleadoDAO();
    Empleado em = new Empleado();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Validar</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Validar at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        //HttpSession sesion = null;
        //processRequest(request, response);
        String accion=request.getParameter("accion");
        if(accion.equalsIgnoreCase("Salir")){
            //sesion.removeAttribute("usuario");
            //sesion.invalidate();
            request.getRequestDispatcher("Controlador?menu=Principal").forward(request, response);
        }
        if(accion.equalsIgnoreCase("Ingresar")){
            String user=request.getParameter("txtuser");
            String pass=asegurarClave(request.getParameter("txtpass"));
            Empleado item = new Empleado();
            item.setUser(user);
            item.setContra(pass);
            em=edao.validar(item);
            if(em.getUser()!=null){
                HttpSession sesion = request.getSession();
                System.out.println("sesio numero: "+sesion.getId());
                sesion.setAttribute("usuario", em);
                request.setAttribute("usuario", em);
                request.getRequestDispatcher("Controlador?menu=Principal").forward(request, response);
                
                //sesion.removeAttribute("usuario");
                //sesion.invalidate();
                
            } else{
                request.getRequestDispatcher("Controlador?menu=Principal").forward(request, response);
            }
        } else{
            request.getRequestDispatcher("Controlador?menu=Principal").forward(request, response);
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
