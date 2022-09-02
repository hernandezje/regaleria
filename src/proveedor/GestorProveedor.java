/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proveedor;

import bd.Conexion;
import cliente.Cliente;
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
public class GestorProveedor {

    void eliminar(Proveedor proveedor) {
        String sql="delete from proveedor where idProveedor=?";
        try {
            PreparedStatement p = Conexion.con.prepareStatement(sql);
            p.setInt(1,proveedor.getIdProveedor());
            p.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    
    }

    void agregar(Proveedor prov) {
        String sql="insert into direccion (Localidad_idLocalidad, calle, numero) values(?,?,?)";
        try {
            PreparedStatement p = Conexion.con.prepareStatement(sql);
            p.setInt(1,prov.getDireccion().getLocalidad().getIdLocalidad());
            p.setString(2, prov.getDireccion().getCalle());
            p.setInt(3, prov.getDireccion().getNumero());
            p.execute();
            sql="select max(idDireccion) as ultimo from direccion";
            p=Conexion.con.prepareStatement(sql);
            ResultSet r =p.executeQuery();
            if (r.next()){
                prov.getDireccion().setIdDireccion(r.getInt("ultimo"));
                sql = "insert into proveedor (Direccion_idDireccion, cuit, razon_social, telefono, celular, correo) values(?,?,?,?,?,?)";
            p=Conexion.con.prepareStatement(sql);
            p.setInt(1, prov.getDireccion().getIdDireccion());
            p.setInt(2, prov.getCuit());
            p.setString(3, prov.getRazonSocial());
            p.setString(4, prov.getTel());
            p.setString(5, prov.getCelular());
            p.setString(6, prov.getCorreo());
            p.execute();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    
    }

    void modificar(Proveedor prov) {
        String sql="update direccion set "
               + "Localidad_idLocalidad =?,"
               + "calle=?, "
               + "numero=? "
               + "where idDireccion=?";
        try {
            PreparedStatement p = Conexion.con.prepareStatement(sql);
            p.setInt(1,prov.getDireccion().getLocalidad().getIdLocalidad());
            p.setString(2, prov.getDireccion().getCalle());
            p.setInt(3, prov.getDireccion().getNumero());
            p.setInt(4, prov.getDireccion().getIdDireccion());
            p.execute();
                sql = "update proveedor set "
                        + "Direccion_idDireccion=?, "
                        + "cuit=?, "
                        + "razon_social=?, "
                        + "telefono=?, "
                        + "celular=?, "
                        + "correo=? "
                        + "where idProveedor=?";
            p=Conexion.con.prepareStatement(sql);
            p.setInt(1, prov.getDireccion().getIdDireccion());
            p.setInt(2, prov.getCuit());
            p.setString(3, prov.getRazonSocial());
            p.setString(4, prov.getTel());
            p.setString(5, prov.getCelular());
            p.setString(6, prov.getCorreo());
            p.setInt(7, prov.getIdProveedor());
            p.execute();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public ArrayList<Proveedor> getLista() {
        String sql="SELECT prov.* ,d.calle,d.`Localidad_idLocalidad`,d.numero, \n"
              + "l.`Provincia_idProvincia`,l.nombre as localidad,p.nombre as provincia\n" 
              + "FROM proveedor prov,direccion d, localidad l, provincia p\n" 
              + "WHERE prov.`Direccion_idDireccion`=d.`idDireccion`\n" 
              + "AND d.`Localidad_idLocalidad`=l.`idLocalidad`\n" 
              + "AND l.`Provincia_idProvincia`=p.`idProvincia` ";
        ArrayList<Proveedor> lista=new ArrayList<>();
        try {
            PreparedStatement p=Conexion.con.prepareStatement(sql);
            ResultSet rs=p.executeQuery();
            while(rs.next()){
              Proveedor prov=new Proveedor();
              prov.setIdProveedor(rs.getInt("idProveedor"));
              prov.setCuit(rs.getInt("cuit"));
              prov.setRazonSocial(rs.getString("razon_social"));
              prov.setTel(rs.getString("telefono"));
              prov.setCelular(rs.getString("celular"));
              prov.setCorreo(rs.getString("correo"));
              prov.getDireccion().setCalle(rs.getString("calle"));
              prov.getDireccion().setIdDireccion(rs.getInt("Direccion_idDireccion"));
              prov.getDireccion().setNumero(rs.getInt("numero"));
              prov.getDireccion().getLocalidad().setIdLocalidad(rs.getInt("Localidad_idLocalidad"));
              prov.getDireccion().getLocalidad().setNombreLoc(rs.getString("localidad"));
              prov.getDireccion().getLocalidad().getProvincia().setIdProv(rs.getInt("Provincia_idProvincia"));
              prov.getDireccion().getLocalidad().getProvincia().setNombreProv(rs.getString("provincia"));
              lista.add(prov);
            }
                    } catch (SQLException ex) {
            Logger.getLogger(GestorProveedor.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return lista;
    
    }

    void recuperar(Proveedor prov) {
        String sql="SELECT prov.* ,d.calle,d.`Localidad_idLocalidad`,d.numero, \n"
              + "l.`Provincia_idProvincia`,l.nombre as localidad,p.nombre as provincia\n" 
              + "FROM proveedor prov,direccion d, localidad l, provincia p\n" 
              + "WHERE prov.`Direccion_idDireccion`=d.`idDireccion`\n" 
              + "AND d.`Localidad_idLocalidad`=l.`idLocalidad`\n" 
              + "AND l.`Provincia_idProvincia`=p.`idProvincia` "
              + "AND prov.idProveedor=?";
      
        try {
            PreparedStatement p=Conexion.con.prepareStatement(sql);
            
            p.setInt(1, prov.getIdProveedor());
            ResultSet rs=p.executeQuery();
            while(rs.next()){
                
              prov.setIdProveedor(rs.getInt("idProveedor"));
              prov.setCuit(rs.getInt("cuit"));
              prov.setRazonSocial(rs.getString("razon_social"));
              prov.setTel(rs.getString("telefono"));
              prov.setCelular(rs.getString("celular"));
              prov.setCorreo(rs.getString("correo"));
              prov.getDireccion().setCalle(rs.getString("calle"));
              prov.getDireccion().setIdDireccion(rs.getInt("Direccion_idDireccion"));
              prov.getDireccion().setNumero(rs.getInt("numero"));
              prov.getDireccion().getLocalidad().setIdLocalidad(rs.getInt("Localidad_idLocalidad"));
              prov.getDireccion().getLocalidad().setNombreLoc(rs.getString("localidad"));
              prov.getDireccion().getLocalidad().getProvincia().setIdProv(rs.getInt("Provincia_idProvincia"));
              prov.getDireccion().getLocalidad().getProvincia().setNombreProv(rs.getString("provincia"));
            
            }
                    } catch (SQLException ex) {
            Logger.getLogger(GestorProveedor.class.getName()).log(Level.SEVERE, null, ex);
            
        }

 }    

    public Proveedor buscarXCUIT(int cuit) {
    Proveedor proveedor= null;
        String sql="select idProveedor, cuit, razon_social, telefono, celular, correo "
                + "from proveedor where cuit=?";
        try {
            PreparedStatement p=Conexion.con.prepareStatement(sql);
            
            p.setInt(1, cuit);
            ResultSet rs=p.executeQuery();
            if(rs.next()){
              proveedor=new Proveedor();
              proveedor.setCuit(rs.getInt("cuit"));
              proveedor.setRazonSocial(rs.getString("razon_social"));
              proveedor.setTel(rs.getString("telefono"));
              proveedor.setCelular(rs.getString("celular"));
              proveedor.setCorreo(rs.getString("correo"));
              proveedor.setIdProveedor(rs.getInt("idProveedor"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return proveedor;
    }

    
}
