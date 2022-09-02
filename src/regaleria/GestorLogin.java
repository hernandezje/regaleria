/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regaleria;

import bd.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author YOHANA HERNANDEZ
 */
public class GestorLogin {

    public static String descripcion;
    public static int idUsuario;
    public static int idEmpleado;
    public static int idCargo;
    public static String nombre;
    public static int leg;
    
    
    public boolean ingresar(String usuario, String clave){
        String sql="select * from usuario, empleado, cargo\n" +
"where usuario.usuario=? " +
"and usuario.contraseña=sha1(?) \n" +
"and usuario.Empleado_idEmpleado=empleado.idEmpleado \n" +
"and empleado.Cargo_idCargo=cargo.idCargo \n" ;
//"and empleado.legajo=empleado.legajo \n" ;
        boolean encontrado=false;
        System.out.println(sql);
        try {
            PreparedStatement p=Conexion.con.prepareStatement(sql);
            p.setString(1, usuario);
            p.setString(2, clave);
            ResultSet rs=p.executeQuery();
            if(rs.next()){
                descripcion=rs.getString("descripcion");
                idUsuario=rs.getInt("idUsuario");
                idEmpleado=rs.getInt("idEmpleado");
                idCargo=rs.getInt("idCargo");
                encontrado=true;
                nombre=rs.getString("nombre");
                leg=rs.getInt("legajo");
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return encontrado;
    }
    
}
