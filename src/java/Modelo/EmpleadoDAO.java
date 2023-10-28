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
public class EmpleadoDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int respuesta;
    
    public Empleado validar(Empleado item){
        Empleado em = new Empleado();
        String sql = "select * from empleado where usuario=? and contra=?";
        try {
            con = (Connection) cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, item.getUser());
            ps.setString(2, item.getContra());
            rs=ps.executeQuery();
            while(rs.next()){
                em.setId(rs.getInt("idEmpleado"));
                em.setContra(rs.getString("contra"));
                em.setUser(rs.getString("usuario"));
                em.setDni(rs.getString("Dni"));
                em.setNom(rs.getString("Nombres"));
            }
            
        } catch (SQLException e) {
            System.out.println("ERROR EN EDAO1: "+e.getMessage());
        }
        return em;
    }
    
    //CRUD
    
    public List listar(){
        String sql="select * from empleado";
        List<Empleado>lista = new ArrayList<>();
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Empleado em = new Empleado();
                em.setId(rs.getInt(1));
                em.setDni(rs.getString(2));
                em.setContra(rs.getString(3));
                em.setNom(rs.getString(4));
                em.setTel(rs.getString(5));
                em.setEstado(rs.getString(6));
                em.setUser(rs.getString(7));
                lista.add(em);
            }
        } catch (SQLException e) {
            System.out.println("ERROR EN EDAO2: "+e.getMessage());
        }
        return lista;
    }
    
    public int agregar(Empleado em){
        String sql="insert into empleado(Dni, contra, Nombres, Telefono,Estado,Usuario)values(?,?,?,?,?,?)";
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setString(1, em.getDni());
            ps.setString(2,em.getContra());
            ps.setString(3, em.getNom());
            ps.setString(4, em.getTel());
            ps.setString(5, em.getEstado());
            ps.setString(6, em.getUser());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ERROR EN EDAO3: "+e.getMessage());
        }
        return respuesta;
    }
    
    public Empleado listarId(int id){
        Empleado emp = new Empleado();
        String sql="select * from empleado where idEmpleado="+id;
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                emp.setDni(rs.getString(2));
                emp.setDni(rs.getString(3));
                emp.setNom(rs.getString(4));
                emp.setTel(rs.getString(5));
                emp.setEstado(rs.getString(6));
                emp.setUser(rs.getString(7));
            }
        } catch (SQLException e) {
            System.out.println("ERROR EN EDAO4: "+e.getMessage());
        }
        return emp;
    }
    
    public int actualizar(Empleado em){
        String sql="update empleado set Dni=?, contra=?, Nombres=?, Telefono=?,Estado=?,Usuario=? where idEmpleado=?";
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setString(1, em.getDni());
            ps.setString(2, em.getContra());
            ps.setString(3, em.getNom());
            ps.setString(4, em.getTel());
            ps.setString(5, em.getEstado());
            ps.setString(6, em.getUser());
            ps.setInt(7, em.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ERROR EN EDAO5: "+e.getMessage());
        }
        return respuesta;
    }
    
    public void delete(int id){
        String sql="delete from empleado where idEmpleado="+id;
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ERROR EN EDAO6: "+e.getMessage());
        }
    }
}
