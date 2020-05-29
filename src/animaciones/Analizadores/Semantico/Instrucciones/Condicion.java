/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animaciones.Analizadores.Semantico.Instrucciones;

import animaciones.Analizadores.Semantico.Objeto;
import animaciones.Analizadores.Semantico.TablaDeSimbolos;
import animaciones.Analizadores.Sintactico.AnalizadorSintacticoPintar;
import animaciones.Analizadores.Sintactico.Inicializacion;
import java.util.Objects;

/**
 *
 * @author sergio
 */
public class Condicion {

    protected static final int IGUAL_IGUAL = 1;
    protected static final int MENOR = 2;
    protected static final int MAYOR = 3;
    protected static final int MAYOR_IGUAL = 4;
    protected static final int MENOR_IGUAL = 5;
    protected static final int DIFERENTE = 6;
    private CondicionLog con;
    private Operacion operacion1, operacion2;
    private Integer tipo;
    private boolean condicion;
    private String id;

    public Condicion(String id) {
        this.id = id;
    }

    public Condicion(boolean condicion) {
        this.condicion = condicion;
    }

    public Condicion(Operacion operacion1, Operacion operacion2, Integer tipo) {
        this.operacion1 = operacion1;
        this.operacion2 = operacion2;
        this.tipo = tipo;
    }

    public Boolean obtenerValor(AnalizadorSintacticoPintar asp) {
        if (tipo != null) {
            
            Integer op1 = operacion1.obtenerValor(asp);
            Integer op2 = operacion2.obtenerValor(asp);
            System.out.println("COMPARAR "+op1+ "  "+tipo+"  "+op2);
            System.out.println("A Comparar "+tipo+op1+op2);
            if (op1 != null && op2 != null) {
                switch (tipo) {
                    case Inicializacion.IGUAL_IGUAL:
                          return  Objects.equals(op1, op2);
                        
                    case Inicializacion.MENOR:
                         return  op1<op2;
                    case Inicializacion.MAYOR:
                         return  op1>op2;
                        
                    case Inicializacion.MAYOR_IGUAL:
                         return  op1>=op2;
                    case Inicializacion.MENOR_IGUAL:
                         return  op1<=op2;
                        
                    case Inicializacion.DIFERENTE:
                         return  !Objects.equals(op1, op2);
                    default:
                        return  null;
                }
            } else {
                return null;
            }

        } else {
              if (id!=null) {
               if (asp.tablaDeSimbolos.obtenerExistencia(id,TablaDeSimbolos.TIPO_BOOLEAN)!=null) {
                   Objeto ob = (Objeto)asp.tablaDeSimbolos.obtenerExistencia(id,TablaDeSimbolos.TIPO_BOOLEAN);
                  
                  return (Boolean)ob.getValor();
               }else{
                   asp.getIde().escribirEnOutput("Variable Boolean "+id+" no existe");
               }
           }else{
               return condicion;
           }
        }
        
        return null;
    }

}
