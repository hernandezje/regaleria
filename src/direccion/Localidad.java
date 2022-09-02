/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package direccion;

/**
 *
 * @author YOHANA HERNANDEZ
 */
public class Localidad {
    
    private int idLocalidad;
    private String nombreLoc;
    private Provincia provincia=new Provincia();
    
    public Localidad(){
        
    }

    public int getIdLocalidad() {
        return idLocalidad;
    }

    public void setIdLocalidad(int idLocalidad) {
        this.idLocalidad = idLocalidad;
    }

    public String getNombreLoc() {
        return nombreLoc;
    }

    public void setNombreLoc(String nombreLoc) {
        this.nombreLoc = nombreLoc;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }
    
   
    
    
    
}
