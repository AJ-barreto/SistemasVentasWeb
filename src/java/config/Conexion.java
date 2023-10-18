/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.sql.DriverManager;
import java.sql.*;

/**
 *
 * @author AJ-Barreto
 */
public class Conexion {
    Connection con;
    
    String usuario = "postgres";
    String contrasena = "123456";
    String bd = "bd_ventas";
    String ip = "localhost";
    String puerto = "5432";
      
    String cadena = "jdbc:postgresql://"+ip+":"+puerto+"/"+bd;
    public Connection Conexion(){
        try {
            Class.forName("org.postgresql.Driver");
            con = (Connection) DriverManager.getConnection(cadena,usuario,contrasena);
        } catch (Exception e) {
        }
        return con;
    }
}
