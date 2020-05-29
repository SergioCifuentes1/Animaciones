/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animaciones.Analizadores.Semantico.Instrucciones;

import animaciones.Analizadores.Semantico.Objeto;
import animaciones.Analizadores.Semantico.TablaDeSimbolos;
import animaciones.Analizadores.Sintactico.AnalizadorSintacticoPintar;
import java.awt.Dimension;
import java.util.ArrayList;

/**
 *
 * @author sergio
 */
public class Pintar extends Instruccion {

    private String imagen;
    private String lienzo;
    private String color;
    private String idImagen;
    private String idColor;
    private Operacion opxInicio;
    private Operacion opxFin;
    private Operacion opyInicio;
    private Operacion opyFin;
    private int X;
    private int Y;

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public Pintar(String idImagen, String idColor, int X, int Y) {
        this.idImagen = idImagen;
        this.idColor = idColor;
        this.X = X;
        this.Y = Y;
    }

    public String getIdImagen() {
        return idImagen;
    }

    public String getIdColor() {
        return idColor;
    }
    
    public Pintar(String imagen, String color, Operacion opxInicio, Operacion opxFin, Operacion opyInicio, Operacion opyFin) {
        if (imagen.startsWith("\"") || imagen.startsWith("”") || imagen.startsWith("“")) {
            this.imagen = imagen;
        } else {
            idImagen = imagen;
        }
        if (color.startsWith("\"") || color.startsWith("”") || color.startsWith("“")) {
            this.color = color;
        } else {
            idColor = color;
        }
        this.opxInicio = opxInicio;
        this.opxFin = opxFin;
        this.opyInicio = opyInicio;
        this.opyFin = opyFin;
    }

    @Override
    public void ejecutar(AnalizadorSintacticoPintar asp, ArrayList<Pintar> pintar, String IdLienzo) {
        String idIm = null;
        String idCol = null;
        boolean error = false;
        if (imagen == null) {
            if (asp.tablaDeSimbolos.obtenerExistencia(idImagen, TablaDeSimbolos.TIPO_STRING) != null) {
                Objeto ob = (Objeto) asp.tablaDeSimbolos.obtenerExistencia(idImagen, TablaDeSimbolos.TIPO_STRING);
                String aux = ((String) ob.getValor());
                idIm = aux.substring(1, aux.length() - 1);
            } else {
                asp.getIde().escribirEnOutput("Error Semantico\nLa variable String " + idImagen + " no existe\n");
                error = true;
            }
                  
            
            if (idIm!=null&&asp.tablaDeSimbolos.obtenerExistencia(idIm, TablaDeSimbolos.TIPO_IMAGEN,IdLienzo) == null) {
                error = true;
            }
             
        } else {
            idIm = imagen.substring(1, imagen.length() - 1);
        }
        if (!error) {
            if (color == null) {
                if (asp.tablaDeSimbolos.obtenerExistencia(idColor, TablaDeSimbolos.TIPO_STRING) != null) {
                Objeto ob = (Objeto) asp.tablaDeSimbolos.obtenerExistencia(idColor, TablaDeSimbolos.TIPO_STRING);
                String aux = ((String) ob.getValor());
                idCol = aux.substring(1, aux.length() - 1);
            } else {
                asp.getIde().escribirEnOutput("Error Semantico\nLa variable String " + idImagen + " no existe\n");
                error = true;
            }
                  
            
            if (asp.tablaDeSimbolos.obtenerExistencia(idCol, TablaDeSimbolos.TIPO_COLOR,IdLienzo) == null) {
                error = true;
            }
            
                
                
                
                
                
            } else {
                idCol = color.substring(1, color.length() - 1);
            }
            
            if (!error) {
                
                Integer inicioX=opxInicio.obtenerValor(asp);
                Integer inicioY=opyInicio.obtenerValor(asp);
                Integer finX=opxInicio.obtenerValor(asp);
                Integer finY=opyInicio.obtenerValor(asp);
                if (inicioX!=null||inicioY!=null) {
                    if (opxFin!=null&&opxFin.obtenerValor(asp)!=null) {
                        finX=opxFin.obtenerValor(asp);
                    }else if(opxFin!=null){
                        finX=null;
                        asp.getIde().escribirEnOutput("Error Semantico\nLas coordenadas del pintar no son Enteras\n");
                    }
                    if (opyFin!=null&&opyFin.obtenerValor(asp)!=null) {
                        finY=opyFin.obtenerValor(asp);
                    }else if(opyFin!=null){
                        finY=null;
                        asp.getIde().escribirEnOutput("Error Semantico\nLas coordenadas del pintar no son Enteras\n");
                    }
                    
                    if (finX!=null&&finY!=null) {
                        ArrayList<Dimension> dimensiones=obtenerDimensiones(inicioX, inicioY, finX, finY);
                    
                        for (int i = 0; i < dimensiones.size(); i++) {
                    
                            pintar.add(new Pintar(idIm, idCol, dimensiones.get(i).width,dimensiones.get(i).height));
                        }
                        
                    }
                    
                    
                    
                }else{
                    asp.getIde().escribirEnOutput("Error Semantico\nLas coordenadas del pintar no son Enteras\n");
                }
            }
        }
        if (error ) {
            asp.errorRecuperable=true;
        }
    }
    
    public ArrayList<Dimension> obtenerDimensiones(int inicioX,int inicioY,int finX,int finY){
        ArrayList<Dimension> dimenciones= new ArrayList<>();
        for (int i = inicioX; i < finX+1; i++) {
            for (int j = inicioY; j < finY+1; j++) {
                dimenciones.add(new Dimension(i, j));
            }
        }
        return dimenciones;
    }
}
