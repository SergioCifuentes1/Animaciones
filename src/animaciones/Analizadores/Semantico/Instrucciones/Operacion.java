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

/**
 *
 * @author sergio
 */
public class Operacion {
    private Operacion op1,op2;
    private String numId1;
    
    private int num1;
    private Integer tipo;

    
    public Operacion(Operacion op1, Operacion op2, int tipo) {
        this.op1=op1;
        this.op2=op2;
        this.tipo = tipo;
    }
    public Operacion(int num1) {
        System.out.println("Se inserta "+num1);
        this.num1 = num1;
        
    }
    public Operacion(String numId1) {
        this.numId1 = numId1;
        
    }

    public int getNum1() {
        return num1;
    }
    
   public Integer obtenerValor(AnalizadorSintacticoPintar asp){
       
       if (tipo!=null) {
           try{
           Integer nu1= op1.obtenerValor(asp);
           Integer nu2= op2.obtenerValor(asp);
       
           if (nu1!=null && nu2!=null) {
               switch (tipo) {
                   case 1:
                       return nu1+nu2;
                       
                    case 2:
                       return nu1-nu2;
                      
                    case 3:
                       return nu1*nu2;
                      
                    case 4:
                       return nu1/nu2;
                         
                   default:
                       throw new AssertionError();
               }
           }
           }catch(Exception e){
               asp.getIde().escribirEnOutput("Error Semantico Al Momento De Realizar Operacion Aritmetica\n");
               return null;
           }
       }else{
           if (numId1!=null) {
               if (asp.tablaDeSimbolos.obtenerExistencia(numId1,TablaDeSimbolos.TIPO_INT)!=null) {
                   Objeto ob = (Objeto)asp.tablaDeSimbolos.obtenerExistencia(numId1,TablaDeSimbolos.TIPO_INT);
                  
                  return (Integer)ob.getValor();
               }
           }else{
               return num1;
           }
       }
       return null;
   }
    
    
}
