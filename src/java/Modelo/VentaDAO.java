/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author AJ-Barreto
 */
public class VentaDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
    public String GenerarSerie(){
        String numeroserie="";
        String sql="select max(NumeroSerie) from ventas";
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                numeroserie=rs.getString(1);
            }
        } catch (Exception e) {
        }
        return numeroserie;
    }
    
    public String IdVentas(){
        String idventas="";
        String sql = "select max(IdVentas) from ventas";
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                idventas=rs.getString(1);
                
            }
        } catch (Exception e) {
        }
        return idventas;
    }
    
    public int guardarVentas(Venta ve){
        String sql = "insert into ventas(IdVentas, IdCliente, IdEmpleado, NumeroSerie, FechaVentas, Monto, Estado)values(nextval('ventas_IdVentas_seq'),?,?,?,?,?,?)";
        try {
            System.out.println("ESTE ES EL OBJETO VENTA: "+ve.getId()+" "+ve.getIdcliente()+" "+ve.getIdempleado()+" "+ve.getNumserie()+" "+ve.getFecha()+" "+ve.getMonto()+" "+ve.getEstado());
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setInt(1, ve.getIdcliente());
            ps.setInt(2, ve.getIdempleado());
            ps.setString(3, ve.getNumserie());
            ps.setString(4, ve.getFecha());
            ps.setDouble(5, ve.getPrecio());
            ps.setString(6, ve.getEstado());
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }
    
    public int guardarDetalleventas(Venta ve){
        String sql="insert into Detalle_ventas (IdVentas, IdProducto, Cantidad, PrecioVenta) values(?,?,?,?)";
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setInt(1, ve.getId());
            ps.setInt(2, ve.getIdproducto());
            ps.setInt(3, ve.getCantidad());
            ps.setDouble(4, ve.getPrecio());
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }
}
