/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animaciones.Analizadores.Semantico.Instrucciones;

import animaciones.Analizadores.Semantico.Objeto;
import animaciones.Analizadores.Semantico.TablaDeSimbolos;
import animaciones.Analizadores.Sintactico.AnalizadorSintacticoPintar;
import java.util.ArrayList;

/**
 *
 * @author sergio
 */
public class If extends Instruccion{
    private CondicionLog condiciones;
    private String id;
    private ArrayList<Instruccion> instruccionesIf ;
    private ArrayList<Instruccion> instruccionesElse ;

    public If(CondicionLog condiciones, ArrayList<Instruccion> intruccionesIf, ArrayList<Instruccion> intruccionesElse) {
        this.condiciones = condiciones;
        this.instruccionesIf = intruccionesIf;
        this.instruccionesElse = intruccionesElse;
    }
    public If(String id, ArrayList<Instruccion> intruccionesIf, ArrayList<Instruccion> intruccionesElse) {
        this.id = id;
        this.instruccionesIf = intruccionesIf;
        this.instruccionesElse = intruccionesElse;
    }
    
    
    @Override
    public void ejecutar(AnalizadorSintacticoPintar asp, ArrayList<Pintar> pintar, String IdLienzo) {
        if (condiciones!=null) {
            Boolean con = condiciones.obtenerValor(asp);
            if (con!=null) {
                if (con) {
                    System.out.println("IF "+instruccionesIf.size());
                    for (int i = 0; i < instruccionesIf.size(); i++) {
                        instruccionesIf.get(i).ejecutar(asp, pintar, IdLienzo);
                    }
                }else{
                    
                    if (instruccionesElse!=null) {
                        System.out.println("ELSE "+instruccionesElse.size());
                        for (int i = 0; i < instruccionesElse.size(); i++) {
                        instruccionesElse.get(i).ejecutar(asp, pintar, IdLienzo);
                    } 
                    }
                   
                }
            }
            
        }else{
            
            if (asp.tablaDeSimbolos.obtenerExistencia(id,TablaDeSimbolos.TIPO_BOOLEAN)!=null) {
                   Objeto ob = (Objeto)asp.tablaDeSimbolos.obtenerExistencia(id,TablaDeSimbolos.TIPO_BOOLEAN);
                  Boolean bool=(Boolean)ob.getValor();
                  if (bool) {
                      System.out.println("IF333 "+instruccionesIf.size());
                    for (int i = 0; i < instruccionesIf.size(); i++) {
                        instruccionesIf.get(i).ejecutar(asp, pintar, IdLienzo);
                    }
                }else{
                    if (instruccionesElse!=null) {
                        for (int i = 0; i < instruccionesElse.size(); i++) {
                        instruccionesElse.get(i).ejecutar(asp, pintar, IdLienzo);
                    } 
                    }
                   
                }
               }else{
                 asp.getIde().escribirEnOutput("Error Semantico\n"+ id+" No es una variable Boolean\n");
            }
            
        }
    }
            
}
