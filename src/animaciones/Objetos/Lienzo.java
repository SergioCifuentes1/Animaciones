/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animaciones.Objetos;

import animaciones.Analizadores.Semantico.Instrucciones.Pintar;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

/**
 *
 * @author sergio
 */
public class Lienzo {
    public static final int TIPO_PNG=1;
    public static final int TIPO_GIF=2;
    private String id;
    private String nombre;
    private int tipo;
    private Color Fondo;
    private int tamaño;
    private Dimension dimension;
    private ArrayList<ColorParaLienzo> colores;
    private Tiempo tiempo;
    private ArrayList<Pintar> pintar;
    public static boolean verificarObligatoriosTam(Object[] atributosTam){
        for (int i = 0; i < atributosTam.length; i++) {
            if (atributosTam[i]==null) {
                return false;
            }
        }
        
        return true;
    }

    public void setPintar(ArrayList<Pintar> pintar) {
        this.pintar = pintar;
    }

    public ArrayList<Pintar> obtenerPintarEditado(){
       ArrayList<Pintar> pin = new ArrayList<>();
        for (int i = 0; i < tiempo.getImagenes().size(); i++) {
            pin.addAll(tiempo.getImagenes().get(i).obtenerPintar(Fondo));
        }
        
        return pin;
    }
    
    public ArrayList<Pintar> obtenerPintar(String imagen){
       ArrayList<Pintar> pin = new ArrayList<>();
        for (int i = 0; i < pintar.size(); i++) {
            if (pintar.get(i).getIdImagen().equals(imagen)) {
                pin.add(pintar.get(i));
            }
        }
        return pin;
    }
    public Color obtenerColo(String id){
        for (int i = 0; i < colores.size(); i++) {
            if (colores.get(i).getId().equals(id)) {
                return colores.get(i).getColor();
            }
        }
        return Fondo;
    }
    
    public ArrayList<ColorParaLienzo> getColores() {
        return colores;
    }

    public Tiempo getTiempo() {
        return tiempo;
    }
    
    public static Color construirColor(String colorStr){
        Color color = new Color(Integer.valueOf( colorStr.substring( 1, 3 ), 16 ),
                Integer.valueOf( colorStr.substring( 3, 5 ), 16 ),
                Integer.valueOf( colorStr.substring( 5, 7 ), 16 ) );
        return color;
    }
    public static Color construirColor(Object[] atributos){
        
        Color color = new Color((Integer)atributos[0],
                (Integer)atributos[1],
                (Integer)atributos[2]);
        
        return color;
    }
    public void agregarInicioyFin(String idInicio,String idFin){
        tiempo= new Tiempo(idInicio, idFin, new ArrayList<>());
    }
    public void agregarImagen(Imagen im){
        tiempo.getImagenes().add(im);
    }
    
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getTipo() {
        return tipo;
    }

    public Color getFondo() {
        return Fondo;
    }

    public int getTamaño() {
        return tamaño;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public Lienzo(Object[] atributos) {
        this.nombre = (String)atributos[0];
        this.tipo = (Integer)atributos[1];
        this.Fondo = (Color)atributos[2];
        this.tamaño = (Integer)atributos[3];
        this.dimension = (Dimension)atributos[4];
        colores= new ArrayList<>();
    }
    public void agregarColor(Color color, String id){
        colores.add(new ColorParaLienzo(color, id));
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
}
