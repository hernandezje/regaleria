/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import direccion.Direccion;
import java.sql.Date;

/**
 *
 * @author yohan
 */
public class Cliente {
    private int idCliente;
    private String nombre;
    private String dni;
    private String telefono;
    private Date fecha_alta;
    private Direccion direccion= new Direccion();
    
    
    public Cliente(){
        
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni(){
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFecha_alta() {
        return fecha_alta;
    }

    public void setFecha_alta(java.util.Date fecha_alta) {
        this.fecha_alta = new java.sql.Date(fecha_alta.getTime());
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    
    
    
            
}
