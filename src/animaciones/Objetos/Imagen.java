/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animaciones.Objetos;

import animaciones.Analizadores.Semantico.Instrucciones.Pintar;
import animaciones.ui.Casilla;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author sergio
 */
public class Imagen {
    private String id;
    private int duracion;
    private int indice;
    private JPanel imagen;
     private ArrayList<Casilla> casillas;
    public Imagen(String id, int duracion, int indice) {
        this.id = id;
        this.duracion = duracion;
        this.indice = indice;
        casillas= new  ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public JPanel getImagen() {
        return imagen;
    }

    public ArrayList<Casilla> getCasillas() {
        return casillas;
    }

    public void setImagen(JPanel imagen) {
        this.imagen = imagen;
    }

    public int getDuracion() {
        return duracion;
    }

    public int getIndice() {
        return indice;
    }
    
    public ArrayList<Pintar> obtenerPintar(Color fondo){
        ArrayList<Pintar> pin = new ArrayList<>();
            for (int i = 0; i < casillas.size(); i++) {
                if (!casillas.get(i).getColor().equals(fondo)) {
                    pin.add(new Pintar(id,casillas.get(i).getIdColor(),casillas.get(i).getCoordenadas()[0], casillas.get(i).getCoordenadas()[1]));
                }
        }
            return pin;
        
    }
    
}
