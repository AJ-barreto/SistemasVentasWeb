/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import config.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author AJ-Barreto
 */
public class ClienteDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int respuesta;
    
    /*public Empleado validar(String user, String dni){
        Empleado em = new Empleado();
        String sql = "select * from empleado where usuario=? and Dni=?";
        try {
            con = (Connection) cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, dni);
            rs=ps.executeQuery();
            while(rs.next()){
                em.setId(rs.getInt("idEmpleado"));
                em.setUser(rs.getString("usuario"));
                em.setDni(rs.getString("Dni"));
                em.setNom(rs.getString("Nombres"));
            }
            
        } catch (Exception e) {
        }
        return em;
    }*/
    
    public Cliente buscar(String dni){
        Cliente c=new Cliente();
        String sql="select * from cliente where Dni='"+dni+"'";
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                c.setId(rs.getInt(1));
                c.setDni(rs.getString(2));
                c.setNom(rs.getString(3));
                c.setDir(rs.getString(4));
                c.setEstado(rs.getString(5));
            }
        } catch (SQLException e) {
            System.out.println("ERROR EN CDAO1: "+e.getMessage());
        }
        return c;
    }
    
    //CRUD
    
    public List listar(){
        String sql="select * from cliente";
        List<Cliente>lista = new ArrayList<>();
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Cliente em = new Cliente();
                em.setId(rs.getInt(1));
                em.setDni(rs.getString(2));
                em.setNom(rs.getString(3));
                em.setDir(rs.getString(4));
                em.setEstado(rs.getString(5));
                lista.add(em);
            }
        } catch (SQLException e) {
            System.out.println("ERROR EN CDAO2: "+e.getMessage());
        }
        return lista;
    }
    
    public int agregar(Cliente em){
        String sql="insert into cliente(Dni, Nombres, Direccion,Estado)values(?,?,?,?)";
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setString(1, em.getDni());
            ps.setString(2, em.getNom());
            ps.setString(3, em.getDir());
            ps.setString(4, em.getEstado());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ERROR EN CDAO3: "+e.getMessage());
        }
        return respuesta;
    }
    
    public Cliente listarId(int id){
        Cliente emp = new Cliente();
        String sql="select * from cliente where idCliente="+id;
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                emp.setDni(rs.getString(2));
                emp.setNom(rs.getString(3));
                emp.setDir(rs.getString(4));
                emp.setEstado(rs.getString(5));
            }
        } catch (SQLException e) {
            System.out.println("ERROR EN CDAO4: "+e.getMessage());
        }
        return emp;
    }
    
    public int actualizar(Cliente em){
        String sql="update cliente set Dni=?, Nombres=?, Direccion=?,Estado=? where idCliente=?";
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setString(1, em.getDni());
            ps.setString(2, em.getNom());
            ps.setString(3, em.getDir());
            ps.setString(4, em.getEstado());
            ps.setInt(5, em.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ERROR EN CDAO5: "+e.getMessage());
        }
        return respuesta;
    }
    
    public void delete(int id){
        String sql="delete from cliente where idCliente="+id;
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ERROR EN CDAO6: "+e.getMessage());
        }
    }
}
