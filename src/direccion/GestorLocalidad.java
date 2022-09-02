/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package direccion;

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
 * @author YOHANA HERNANDEZ
 */
public class GestorLocalidad {
    
    public void agregar(Localidad loc){
        String sql="insert into localidad (nombre,Provincia_idProvincia) values(?,?)";
        try {
            PreparedStatement p = Conexion.con.prepareStatement(sql);
            p.setString(1,loc.getNombreLoc());
            p.setInt(2, loc.getProvincia().getIdProv());
            p.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    
     public void modificar(Localidad loc){
        String sql="update Localidad set "
                + "nombre=?,"
                + "Provincia_idProvincia=? "
                + "where idLocalidad=?";
        try {
            PreparedStatement p = Conexion.con.prepareStatement(sql);
            p.setString(1,loc.getNombreLoc());
            p.setInt(2, loc.getProvincia().getIdProv());
            p.setInt(3, loc.getIdLocalidad());
            p.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
     
     public void eliminar(Localidad loc){
        String sql="delete from localidad where idLocalidad=?";
        try {
            PreparedStatement p = Conexion.con.prepareStatement(sql);
            p.setInt(1,loc.getIdLocalidad());
            p.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

   public ArrayList getLista(Provincia prov) {
      String sql="select * from localidad where Provincia_idProvincia=?";
      ArrayList lista=new ArrayList<>();
        try {
            PreparedStatement p=Conexion.con.prepareStatement(sql);
            p.setInt(1, prov.getIdProv());
            ResultSet rs=p.executeQuery();
            while(rs.next()){
                Localidad loc=new Localidad();
                loc.setIdLocalidad(rs.getInt("idLocalidad"));
                loc.setNombreLoc(rs.getString("nombre"));
                loc.setProvincia(prov);
                lista.add(loc);
            }
                    } catch (SQLException ex) {
            Logger.getLogger(GestorLocalidad.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return lista;
    }
    public ArrayList<Localidad> getLista(String prov) {
      String sql="SELECT l.* \n" +
"FROM localidad l, provincia p\n" +
"where l.`Provincia_idProvincia`=p.`idProvincia`\n" +
"and p.nombre=?";
      ArrayList lista=new ArrayList<>();
        try {
            PreparedStatement p=Conexion.con.prepareStatement(sql);
            p.setString(1, prov);
            ResultSet rs=p.executeQuery();
            while(rs.next()){
                Localidad loc=new Localidad();
                loc.setIdLocalidad(rs.getInt("idLocalidad"));
                loc.setNombreLoc(rs.getString("nombre"));
                
                lista.add(loc);
            }
                    } catch (SQLException ex) {
            Logger.getLogger(GestorLocalidad.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return lista;
    }
   
   public ArrayList getLista() {
      String sql="select localidad.idLocalidad,localidad.nombre as localidad,"
              + "provincia.idProvincia,provincia.nombre as provincia "
              + " from localidad,provincia "
              + "where localidad.Provincia_idProvincia=provincia.idProvincia";
      ArrayList lista=new ArrayList<>();
        try {
            PreparedStatement p=Conexion.con.prepareStatement(sql);
            ResultSet rs=p.executeQuery();
            while(rs.next()){
                Localidad loc=new Localidad();
                loc.setIdLocalidad(rs.getInt("idLocalidad"));
                loc.setNombreLoc(rs.getString("localidad"));
                loc.getProvincia().setIdProv(rs.getInt("idProvincia"));
                loc.getProvincia().setNombreProv(rs.getString("Provincia"));
                lista.add(loc);
            }
                    } catch (SQLException ex) {
            Logger.getLogger(GestorLocalidad.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return lista;
    }
   
   public Localidad getLocalidad(String nombreLocalidad) {
      String sql="select localidad.idLocalidad,localidad.nombre as localidad,"
              + "provincia.idProvincia,provincia.nombre as provincia "
              + " from localidad,provincia "
              + "where localidad.Provincia_idProvincia=provincia.idProvincia and "
              + "localidad.nombre=?";
      Localidad loc=null;
        try {
            PreparedStatement p=Conexion.con.prepareStatement(sql);
            p.setString(1, nombreLocalidad);
            ResultSet rs=p.executeQuery();
            while(rs.next()){
                loc=new Localidad();
                loc.setIdLocalidad(rs.getInt("idLocalidad"));
                loc.setNombreLoc(rs.getString("localidad"));
                loc.getProvincia().setIdProv(rs.getInt("idProvincia"));
                loc.getProvincia().setNombreProv(rs.getString("Provincia"));
                
            }
                    } catch (SQLException ex) {
            Logger.getLogger(GestorLocalidad.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return loc;
    }
     
    
}
