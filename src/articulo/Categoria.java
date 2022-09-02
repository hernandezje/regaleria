/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package articulo;

import java.util.ArrayList;

/**
 *
 * @author yohan
 */
public class Categoria {
    
    private int idCategoria;
    private String descripcionCat;
    private ArrayList<Articulo> lista=new ArrayList<Articulo>();

    public Categoria() {
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getDescripcion() {
        return descripcionCat;
    }

    public void setDescripcion(String descripcion_cat) {
        this.descripcionCat = descripcion_cat;
    }

    public ArrayList<Articulo> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Articulo> lista) {
        this.lista = lista;
    }

    @Override
    public String toString() {
        return descripcionCat;
    }
    
    
    
    
}
