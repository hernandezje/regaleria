/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import proveedor.*;
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
public class GestorCliente {

    void eliminar(Cliente cli) {
        String sql="delete from cliente where idCliente=?";
        try {
            PreparedStatement p = Conexion.con.prepareStatement(sql);
            p.setInt(1,cli.getIdCliente());
            p.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    
    }

    void agregar(Cliente cli) {
        String sql="insert into direccion (Localidad_idLocalidad, calle, numero) values(?,?,?)";
        try {
            PreparedStatement p = Conexion.con.prepareStatement(sql);
            p.setInt(1,cli.getDireccion().getLocalidad().getIdLocalidad());
            p.setString(2, cli.getDireccion().getCalle());
            p.setInt(3, cli.getDireccion().getNumero());
            p.execute();
            sql="select max(idDireccion) as ultimo from direccion";
            p=Conexion.con.prepareStatement(sql);
            ResultSet r =p.executeQuery();
            if (r.next()){
                cli.getDireccion().setIdDireccion(r.getInt("ultimo"));
                sql = "insert into cliente (Direccion_idDireccion, nombre, dni, telefono, fecha_alta) values(?,?,?,?,?)";
            p=Conexion.con.prepareStatement(sql);
            p.setInt(1, cli.getDireccion().getIdDireccion());
            p.setString(2, cli.getNombre());
            p.setString(3, cli.getDni());
            p.setString(4, cli.getTelefono());
            p.setDate(5, new java.sql.Date(cli.getFecha_alta().getTime()));
            p.execute();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    
    }

    void modificar(Cliente cli) {
        String sql="update direccion set "
               + "Localidad_idLocalidad =?,"
               + "calle=?, "
               + "numero=? "
               + "where idDireccion=?";
        try {
            PreparedStatement p = Conexion.con.prepareStatement(sql);
            p.setInt(1,cli.getDireccion().getLocalidad().getIdLocalidad());
            p.setString(2, cli.getDireccion().getCalle());
            p.setInt(3, cli.getDireccion().getNumero());
            p.setInt(4, cli.getDireccion().getIdDireccion());
            p.execute();
                sql = "update cliente set "
                        + "Direccion_idDireccion=?, "
                        + "nombre=?, "
                        + "dni=?, "
                        + "telefono=?, "
                        + "fecha_alta=? "
                        + "where idCliente=?";
            p=Conexion.con.prepareStatement(sql);
            p.setInt(1, cli.getDireccion().getIdDireccion());
            p.setString(2, cli.getNombre());
            p.setString(3, cli.getDni());
            p.setString(4, cli.getTelefono());
            p.setDate(5, new java.sql.Date(cli.getFecha_alta().getTime()));
            p.setInt(6, cli.getIdCliente());
            p.execute();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public ArrayList<Cliente> getLista() {
        String sql="SELECT c.* ,d.calle,d.`Localidad_idLocalidad`,d.numero, \n"
              + "l.`Provincia_idProvincia`,l.nombre as localidad,p.nombre as provincia\n" 
              + "FROM cliente c,direccion d, localidad l, provincia p\n" 
              + "WHERE c.`Direccion_idDireccion`=d.`idDireccion`\n" 
              + "AND d.`Localidad_idLocalidad`=l.`idLocalidad`\n" 
              + "AND l.`Provincia_idProvincia`=p.`idProvincia` ";
        ArrayList<Cliente> lista=new ArrayList<>();
        try {
            PreparedStatement p=Conexion.con.prepareStatement(sql);
            ResultSet rs=p.executeQuery();
            while(rs.next()){
              Cliente cli=new Cliente();
              cli.setIdCliente(rs.getInt("idCliente"));
              cli.setNombre(rs.getString("nombre"));
              cli.setTelefono(rs.getString("telefono"));
              cli.setDni(rs.getString("dni"));
              cli.setFecha_alta(rs.getDate("fecha_alta"));
              cli.getDireccion().setCalle(rs.getString("calle"));
              cli.getDireccion().setIdDireccion(rs.getInt("Direccion_idDireccion"));
              cli.getDireccion().setNumero(rs.getInt("numero"));
              cli.getDireccion().getLocalidad().setIdLocalidad(rs.getInt("Localidad_idLocalidad"));
              cli.getDireccion().getLocalidad().setNombreLoc(rs.getString("localidad"));
              cli.getDireccion().getLocalidad().getProvincia().setIdProv(rs.getInt("Provincia_idProvincia"));
              cli.getDireccion().getLocalidad().getProvincia().setNombreProv(rs.getString("provincia"));
              lista.add(cli);
            }
                    } catch (SQLException ex) {
            Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return lista;
    
    }

    void recuperar(Cliente cli) {
        String sql="SELECT c.* ,d.calle,d.`Localidad_idLocalidad`,d.numero, \n"
              + "l.`Provincia_idProvincia`,l.nombre as localidad,p.nombre as provincia\n" 
              + "FROM cliente c,direccion d, localidad l, provincia p\n" 
              + "WHERE c.`Direccion_idDireccion`=d.`idDireccion`\n" 
              + "AND d.`Localidad_idLocalidad`=l.`idLocalidad`\n" 
              + "AND l.`Provincia_idProvincia`=p.`idProvincia` "
              + "AND c.idCliente=?";
        try {
            PreparedStatement p=Conexion.con.prepareStatement(sql);
            
            p.setInt(1, cli.getIdCliente());
            ResultSet rs=p.executeQuery();
            while(rs.next()){
                
              cli.setNombre(rs.getString("nombre"));
              cli.setTelefono(rs.getString("telefono"));
              cli.setDni(rs.getString("dni"));
              cli.setFecha_alta(rs.getDate("fecha_alta"));
              cli.setIdCliente(rs.getInt("idCliente"));
              cli.getDireccion().setCalle(rs.getString("calle"));
              cli.getDireccion().setIdDireccion(rs.getInt("Direccion_idDireccion"));
              cli.getDireccion().setNumero(rs.getInt("numero"));
              cli.getDireccion().getLocalidad().setIdLocalidad(rs.getInt("Localidad_idLocalidad"));
              cli.getDireccion().getLocalidad().setNombreLoc(rs.getString("localidad"));
              cli.getDireccion().getLocalidad().getProvincia().setIdProv(rs.getInt("Provincia_idProvincia"));
              cli.getDireccion().getLocalidad().getProvincia().setNombreProv(rs.getString("provincia"));
              
            
            }
                    } catch (SQLException ex) {
            Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
            
        }

 }    

    public Cliente buscarXDNI(String dni) {
        Cliente cliente= null;
        String sql="select idCliente, nombre, dni, telefono "
                + "from cliente where dni=?";
        try {
            PreparedStatement p=Conexion.con.prepareStatement(sql);
            
            p.setString(1, dni);
            ResultSet rs=p.executeQuery();
            if(rs.next()){
              cliente=new Cliente();
              cliente.setNombre(rs.getString("nombre"));
              cliente.setTelefono(rs.getString("telefono"));
              cliente.setDni(rs.getString("dni"));
              cliente.setIdCliente(rs.getInt("idCliente"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cliente;
    }

    Cliente buscarClienteXDNI(String dni) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
