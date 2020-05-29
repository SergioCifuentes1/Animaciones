/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animaciones.Objetos;

import java.util.ArrayList;

/**
 *
 * @author sergio
 */
public class Tiempo {
    private String idInicio;
    private String idFin;
    private ArrayList<Imagen> imagenes;
    
    public Tiempo(String idInicio, String idFin, ArrayList<Imagen> imagenes) {
        this.idInicio = idInicio;
        this.idFin = idFin;
        this.imagenes = imagenes;
    }

    public String getIdInicio() {
        return idInicio;
    }

    public String getIdFin() {
        return idFin;
    }

    public ArrayList<Imagen> getImagenes() {
        return imagenes;
    }
    
    
}
