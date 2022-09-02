/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empleado;

import direccion.*;
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
public class GestorEmpleado {
    
    public void agregar(Empleado emp){
        String sql="insert into direccion (Localidad_idLocalidad, calle, numero) values(?,?,?)";
        try {
            PreparedStatement p = Conexion.con.prepareStatement(sql);
            p.setInt(1,emp.getDireccion().getLocalidad().getIdLocalidad());
            p.setString(2, emp.getDireccion().getCalle());
            p.setInt(3, emp.getDireccion().getNumero());
            p.execute();
            sql="select max(idDireccion) as ultimo from direccion";
            p=Conexion.con.prepareStatement(sql);
            ResultSet r =p.executeQuery();
            if (r.next()){
                emp.getDireccion().setIdDireccion(r.getInt("ultimo"));
                sql = "insert into empleado (Cargo_idCargo, Direccion_idDireccion, legajo, nombre, dni, telefono, fecha_nac, fecha_ingreso) values(?,?,?,?,?,?,?,?)";
            p=Conexion.con.prepareStatement(sql);
            p.setInt(1, emp.getCargo().getIdCargo());
            p.setInt(2, emp.getDireccion().getIdDireccion());
            p.setInt(3, emp.getLegajo());
            p.setString(4, emp.getNombre());
            p.setString(5, emp.getDni());
            p.setString(6, emp.getTelefono());
            p.setDate(7, new java.sql.Date(emp.getFecha_nac().getTime()));
            p.setDate(8, new java.sql.Date(emp.getFecha_ingreso().getTime()));
            p.execute();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    
     public void modificar(Empleado emp){
       String sql="update direccion set "
               + "Localidad_idLocalidad =?,"
               + "calle=?, "
               + "numero=? "
               + "where idDireccion=?";
        try {
            PreparedStatement p = Conexion.con.prepareStatement(sql);
            p.setInt(1,emp.getDireccion().getLocalidad().getIdLocalidad());
            p.setString(2, emp.getDireccion().getCalle());
            p.setInt(3, emp.getDireccion().getNumero());
            p.setInt(4, emp.getDireccion().getIdDireccion());
            p.execute();
                sql = "update empleado set "
                        + "Cargo_idCargo=?, "
                        + "Direccion_idDireccion=?, "
                        + "legajo=?, "
                        + "nombre=?, "
                        + "dni=?, "
                        + "telefono=?, "
                        + "fecha_nac=?, "
                        + "fecha_ingreso=?"
                        + "where idEmpleado=?";
            p=Conexion.con.prepareStatement(sql);
            p.setInt(1, emp.getCargo().getIdCargo());
            p.setInt(2, emp.getDireccion().getIdDireccion());
            p.setInt(3, emp.getLegajo());
            p.setString(4, emp.getNombre());
            p.setString(5, emp.getDni());
            p.setString(6, emp.getTelefono());
            p.setDate(7, new java.sql.Date(emp.getFecha_nac().getTime()));
            p.setDate(8, new java.sql.Date(emp.getFecha_ingreso().getTime()));
            p.setInt(9, emp.getIdEmpleado());
            p.execute();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
     
     public void eliminar(Empleado emp){
        String sql="delete from empleado where idEmpleado=?";
        try {
            PreparedStatement p = Conexion.con.prepareStatement(sql);
            p.setInt(1,emp.getIdEmpleado());
            p.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

   
   public ArrayList<Empleado> getLista() {
      String sql="SELECT e.* , c.descripcion,c.salario,d.calle,d.`Localidad_idLocalidad`,d.numero, \n" 
              + "l.`Provincia_idProvincia`,l.nombre as localidad,p.nombre as provincia\n" 
              + "FROM empleado e,cargo c,direccion d, localidad l, provincia p\n" 
              + "WHERE e.`Cargo_idCargo`=c.`idCargo`\n" 
              + "AND e.`Direccion_idDireccion`=d.`idDireccion`\n" 
              + "AND d.`Localidad_idLocalidad`=l.`idLocalidad`\n"
              + "AND l.`Provincia_idProvincia`=p.`idProvincia`;";
      ArrayList<Empleado> lista=new ArrayList<>();
        try {
            PreparedStatement p=Conexion.con.prepareStatement(sql);
            ResultSet rs=p.executeQuery();
            while(rs.next()){
              Empleado e=new Empleado();
              e.setDni(rs.getString("dni"));
              e.setFecha_ingreso(rs.getDate("fecha_ingreso"));
              e.setFecha_nac(rs.getDate("fecha_nac"));
              e.setIdEmpleado(rs.getInt("idEmpleado"));
              e.setLegajo(rs.getInt("legajo"));
              e.setNombre(rs.getString("nombre"));
              e.setTelefono(rs.getString("telefono"));
              e.getCargo().setDescripcionCargo(rs.getString("descripcion"));
              e.getCargo().setIdCargo(rs.getInt("cargo_idCargo"));
              e.getCargo().setSalario(rs.getDouble("salario"));
              e.getDireccion().setCalle(rs.getString("calle"));
              e.getDireccion().setIdDireccion(rs.getInt("Direccion_idDireccion"));
              e.getDireccion().setNumero(rs.getInt("numero"));
              e.getDireccion().getLocalidad().setIdLocalidad(rs.getInt("Localidad_idLocalidad"));
              e.getDireccion().getLocalidad().setNombreLoc(rs.getString("localidad"));
              e.getDireccion().getLocalidad().getProvincia().setIdProv(rs.getInt("Provincia_idProvincia"));
              e.getDireccion().getLocalidad().getProvincia().setNombreProv(rs.getString("provincia"));
              lista.add(e);
            }
                    } catch (SQLException ex) {
            Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return lista;
    }
     
 public void recuperar (Empleado e){
      String sql="SELECT e.* , c.descripcion,c.salario,d.calle,d.`Localidad_idLocalidad`,d.numero, \n"
              + "l.`Provincia_idProvincia`,l.nombre as localidad,p.nombre as provincia\n" 
              + "FROM empleado e,cargo c,direccion d, localidad l, provincia p\n" 
              + "WHERE e.`Cargo_idCargo`=c.`idCargo`\n" 
              + "AND e.`Direccion_idDireccion`=d.`idDireccion`\n" 
              + "AND d.`Localidad_idLocalidad`=l.`idLocalidad`\n" 
              + "AND l.`Provincia_idProvincia`=p.`idProvincia` "
              + "AND e.idEmpleado=?";
      
        try {
            PreparedStatement p=Conexion.con.prepareStatement(sql);
            
            p.setInt(1, e.getIdEmpleado());
            ResultSet rs=p.executeQuery();
            while(rs.next()){
                
              e.setDni(rs.getString("dni"));
              e.setFecha_ingreso(rs.getDate("fecha_ingreso"));
              e.setFecha_nac(rs.getDate("fecha_nac"));
              e.setIdEmpleado(rs.getInt("idEmpleado"));
              e.setLegajo(rs.getInt("legajo"));
              e.setNombre(rs.getString("nombre"));
              e.setTelefono(rs.getString("telefono"));
              e.getCargo().setDescripcionCargo(rs.getString("descripcion"));
              e.getCargo().setIdCargo(rs.getInt("cargo_idCargo"));
              e.getCargo().setSalario(rs.getDouble("salario"));
              e.getDireccion().setCalle(rs.getString("calle"));
              e.getDireccion().setIdDireccion(rs.getInt("Direccion_idDireccion"));
              e.getDireccion().setNumero(rs.getInt("numero"));
              e.getDireccion().getLocalidad().setIdLocalidad(rs.getInt("Localidad_idLocalidad"));
              e.getDireccion().getLocalidad().setNombreLoc(rs.getString("localidad"));
              e.getDireccion().getLocalidad().getProvincia().setIdProv(rs.getInt("Provincia_idProvincia"));
              e.getDireccion().getLocalidad().getProvincia().setNombreProv(rs.getString("provincia"));
            
            }
                    } catch (SQLException ex) {
            Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            
        }

 }    

    public Empleado buscarXLegajo(int leg) {
        
        String sql="SELECT e.* , c.descripcion,c.salario,d.calle,d.`Localidad_idLocalidad`,d.numero, \n" 
              + "l.`Provincia_idProvincia`,l.nombre as localidad,p.nombre as provincia\n" 
              + "FROM empleado e,cargo c,direccion d, localidad l, provincia p\n" 
              + "WHERE e.`Cargo_idCargo`=c.`idCargo`\n" 
              + "AND e.`Direccion_idDireccion`=d.`idDireccion`\n" 
              + "AND d.`Localidad_idLocalidad`=l.`idLocalidad`\n"
              + "AND l.`Provincia_idProvincia`=p.`idProvincia` "
                + "AND e.legajo=?";
        
    Empleado e=null;
        try {
            PreparedStatement p=Conexion.con.prepareStatement(sql);
            p.setInt(1, leg);
            ResultSet rs=p.executeQuery();
            if(rs.next()){
            e=new Empleado();
              e.setDni(rs.getString("dni"));
              e.setFecha_ingreso(rs.getDate("fecha_ingreso"));
              e.setFecha_nac(rs.getDate("fecha_nac"));
              e.setIdEmpleado(rs.getInt("idEmpleado"));
              e.setLegajo(rs.getInt("legajo"));
              e.setNombre(rs.getString("nombre"));
              e.setTelefono(rs.getString("telefono"));
              e.getCargo().setDescripcionCargo(rs.getString("descripcion"));
              e.getCargo().setIdCargo(rs.getInt("cargo_idCargo"));
              e.getCargo().setSalario(rs.getDouble("salario"));
              e.getDireccion().setCalle(rs.getString("calle"));
              e.getDireccion().setIdDireccion(rs.getInt("Direccion_idDireccion"));
              e.getDireccion().setNumero(rs.getInt("numero"));
              e.getDireccion().getLocalidad().setIdLocalidad(rs.getInt("Localidad_idLocalidad"));
              e.getDireccion().getLocalidad().setNombreLoc(rs.getString("localidad"));
              e.getDireccion().getLocalidad().getProvincia().setIdProv(rs.getInt("Provincia_idProvincia"));
              e.getDireccion().getLocalidad().getProvincia().setNombreProv(rs.getString("provincia"));
            
            }
                    } catch (SQLException ex) {
            Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return e;
    
    }

    public Empleado buscarXLEG(int leg) {
    
        Empleado empl= null;
        String sql="select idEmpleado, nombre, dni, telefono "
                + "from empleado where legajo=?";
        try {
            PreparedStatement p=Conexion.con.prepareStatement(sql);
            
            p.setInt(1, leg);
            ResultSet rs=p.executeQuery();
            if(rs.next()){
              empl=new Empleado();
              empl.setNombre(rs.getString("nombre"));
              empl.setDni(rs.getString("dni"));
              empl.setTelefono(rs.getString("telefono"));
              empl.setIdEmpleado(rs.getInt("idEmpleado"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empl;
    }

    
}
