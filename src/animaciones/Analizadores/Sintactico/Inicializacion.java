/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animaciones.Analizadores.Sintactico;

import animaciones.Analizadores.Semantico.TablaDeSimbolos;
import java.util.ArrayList;

/**
 *
 * @author sergio
 */
public class Inicializacion {
    public static final int IGUAL_IGUAL=1;
    public static final int MENOR=2;
    public static final int MAYOR=3;
    public static final int MAYOR_IGUAL=4;
    public static final int MENOR_IGUAL=5;
    public static final int DIFERENTE=6;
    public static final int AND=1;
    public static final int OR=2;
    private String id;
    private Object valor;
    private boolean error=false;
    public boolean isError() {
        return error;
        
    }

    public void setError(boolean error) {
        this.error = error;
        try {
            
        } catch (Exception e) {
        }
    }

    public Inicializacion(String id, Object valor) {
        this.id = id;
        
        this.valor = valor;
        
    }

    public String getId() {
        return id;
    }

    public Object getValor() {
        return valor;
    }
    public static boolean verificarTipo(int tipo,Object valor){
        switch (tipo) {
            case 4:
                try {
                    Integer valro1= (Integer)valor;
                    return true;
                } catch (Exception e) {
                    return false;
                }
          
            case 5:
                try {
                    String valro1= (String)valor;
                    return true;
                } catch (Exception e) {
                    return false;
                }
                
                
            case 6:
                try {
                    Boolean valro1= (Boolean)valor;
                    return true;
                } catch (Exception e) {
                    return false;
                }
            default:
                throw new AssertionError();
        }
    }
    public static boolean realizarOperacionRelacional(int num1,int num2,int tipo){
        switch (tipo) {
            case 1: return num1==num2;
            case 2: return num1<num2;
            case 3: return num1>num2;
            case 4: return num1>=num2;
            case 5: return num1<=num2;
            case 6: return num1!=num2;
           
        }
         return false;
    }
    
        public static boolean realizarOperacionLogica(boolean  bool1,boolean  bool2,int tipo){
        switch (tipo) {
            case 1: return bool1&&bool2;
            case 2: return  bool1||bool2;           
        }
         return false;
    }
    
}
