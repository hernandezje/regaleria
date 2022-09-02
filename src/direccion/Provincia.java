/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package direccion;

import java.util.ArrayList;

/**
 *
 * @author YOHANA HERNANDEZ
 */
public class Provincia {
    
    private int idProv;
    private String nombreProv;
    private ArrayList<Localidad> lista=new ArrayList<Localidad>();

    public Provincia() {
    }

    public int getIdProv() {
        return idProv;
    }

    public void setIdProv(int idProv) {
        this.idProv = idProv;
    }

    public String getNombreProv() {
        return nombreProv;
    }

    public void setNombreProv(String nombreProv) {
        this.nombreProv = nombreProv;
    }

    public ArrayList<Localidad> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Localidad> lista) {
        this.lista = lista;
        
    }

    @Override
    public String toString() {
        return nombreProv ;
    }
    
    
    
}
