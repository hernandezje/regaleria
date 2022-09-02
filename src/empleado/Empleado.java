/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empleado;

import direccion.Direccion;
import java.sql.Date;

/**
 *
 * @author yohan
 */
public class Empleado {
    private int idEmpleado;
    private Cargo cargo= new Cargo();
    private Direccion direccion= new Direccion();
    private int legajo;
    private String nombre;
    private String dni;
    private String telefono;
    private Date fecha_nac;
    private Date fecha_ingreso;

    public Empleado() {
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
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

    public Date getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(java.util.Date fecha_nac) {
        this.fecha_nac = new java.sql.Date(fecha_nac.getTime());
    }

    public Date getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(java.util.Date fecha_ingreso) {
        this.fecha_ingreso = new java.sql.Date(fecha_ingreso.getTime());
    }

    
    
}
