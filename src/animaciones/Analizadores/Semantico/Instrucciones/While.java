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
public class While extends Instruccion{
private CondicionLog condiciones;
    private String id;
    private ArrayList<Instruccion> instruccionesWhile ;

    public While(CondicionLog condiciones, ArrayList<Instruccion> instruccionesWhile) {
        this.condiciones = condiciones;
        this.instruccionesWhile = instruccionesWhile;
    }

    public While(String id, ArrayList<Instruccion> instruccionesWhile) {
        this.id = id;
        this.instruccionesWhile = instruccionesWhile;
    }
    
    
    
    @Override
    public void ejecutar(AnalizadorSintacticoPintar asp, ArrayList<Pintar> pintar, String IdLienzo) {
       if (condiciones!=null) {
            Boolean con = condiciones.obtenerValor(asp);
           System.out.println("WHILE1 "+con+"  "+instruccionesWhile.size());
            while (con!=null&&con) {
               for (int i = 0; i < instruccionesWhile.size(); i++) {
                        instruccionesWhile.get(i).ejecutar(asp, pintar, IdLienzo);
                    }
               con = condiciones.obtenerValor(asp);
           }
            
            
        }else{
            
            if (asp.tablaDeSimbolos.obtenerExistencia(id,TablaDeSimbolos.TIPO_BOOLEAN)!=null) {
                   Objeto ob = (Objeto)asp.tablaDeSimbolos.obtenerExistencia(id,TablaDeSimbolos.TIPO_BOOLEAN);
                  Boolean bool=(Boolean)ob.getValor();
                  System.out.println("WHILE2 "+bool+"  "+instruccionesWhile.size());
                  while (bool!=null&&bool) {
               for (int i = 0; i < instruccionesWhile.size(); i++) {
                        instruccionesWhile.get(i).ejecutar(asp, pintar, IdLienzo);
                    }
               bool = condiciones.obtenerValor(asp);
           }
               }else{
                 asp.getIde().escribirEnOutput("Error Semantico\n"+ id+" No es una variable Boolean\n");
            }
            
        }
    }
    
}
