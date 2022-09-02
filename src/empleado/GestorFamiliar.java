/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empleado;

import articulo.GestorCategoria;
import bd.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author yohan
 */
public class GestorFamiliar {
    
    public void agregar(Familiar fam){
        String sql="insert into familiar (parentesco,nombre,dni,"
                + "telefono,fecha_nac,Empleado_idEmpleado) values (?,?,?,?,?,?)";
            try{
                PreparedStatement p =Conexion.con.prepareStatement(sql);
                p.setString(1,fam.getParentesco());
                p.setString(2,fam.getNombre());
                p.setString(3,fam.getDni());
                p.setString(4,fam.getTelFamiliar());
                p.setDate(5, new java.sql.Date(fam.getFechaNac().getTime()));
                p.setInt(6,fam.getEmpleado().getIdEmpleado());
                p.execute();
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            } 
    }
     public void modificar(Familiar fam){
        String sql="update familiar set "
                + "Empleado_idEmpleado=?,"
                + "parentesco=?, "
                + "nombre=?,"
                + "dni=?,"
                + "telefono=?,"
                + "fecha_nac=?"
                + "where idFamiliar=?";
        try {
            PreparedStatement p = Conexion.con.prepareStatement(sql);
                p.setInt(1, fam.getEmpleado().getIdEmpleado());
                p.setString(2,fam.getParentesco());
                p.setString(3,fam.getNombre());
                p.setString(4,fam.getDni());
                p.setString(5,fam.getTelFamiliar());
                p.setDate(6, new java.sql.Date(fam.getFechaNac().getTime()));
                
                p.setInt(7,fam.getIdFamiliar());    
                
            p.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
     
     public void eliminar(Familiar fam){
        String sql="delete from Familiar where idFamiliar=?";
        try {
            PreparedStatement p = Conexion.con.prepareStatement(sql);
            p.setInt(1,fam.getIdFamiliar());
            p.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    ArrayList<Familiar> getListaFamiliar() {
    
      String sql="select empleado.nombre as empleado, familiar.* from familiar, empleado where familiar.Empleado_idEmpleado=empleado.idEmpleado";
      ArrayList<Familiar> listaFamiliar=new ArrayList<>();
        try {
            ResultSet rs=Conexion.con.createStatement().executeQuery(sql);
            while(rs.next()){
                Familiar fam=new Familiar();
                fam.setIdFamiliar(rs.getInt("idFamiliar"));
                fam.getEmpleado().setNombre(rs.getString("empleado"));
                fam.setParentesco(rs.getString("parentesco"));
                fam.setNombre(rs.getString("nombre"));
                fam.setDni(rs.getString("dni"));
                fam.setTelFamiliar(rs.getString("telefono"));
                fam.setFechaNac(rs.getDate("fecha_nac"));
                
                listaFamiliar.add(fam);
            }
                    } catch (SQLException ex) {
            Logger.getLogger(GestorCategoria.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return listaFamiliar;
    }
    public ArrayList<Familiar> getListaFamiliar(Empleado e) {
    
      String sql="select * from familiar "
              + "where Empleado_idEmpleado="+e.getIdEmpleado();
      ArrayList<Familiar> listaFamiliar=new ArrayList<>();
        try {
            ResultSet rs=Conexion.con.createStatement().executeQuery(sql);
            while(rs.next()){
                Familiar fam=new Familiar();
                fam.setIdFamiliar(rs.getInt("idFamiliar"));
                fam.setParentesco(rs.getString("parentesco"));
                fam.setNombre(rs.getString("nombre"));
                fam.setDni(rs.getString("dni"));
                fam.setTelFamiliar(rs.getString("telefono"));
                fam.setFechaNac(rs.getDate("fecha_nac"));
                
                listaFamiliar.add(fam);
            }
                    } catch (SQLException ex) {
            Logger.getLogger(GestorCategoria.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return listaFamiliar;
    }

    void recuperar(Familiar familiar) {
   }
           
    
     
    
    
}
