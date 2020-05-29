/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animaciones.Analizadores.Semantico.Instrucciones;

import animaciones.Analizadores.Sintactico.AnalizadorSintacticoPintar;
import animaciones.Analizadores.Sintactico.Inicializacion;

/**
 *
 * @author sergio
 */
public class CondicionLog {
    private Condicion condicion1,condicion2;
    private Integer tipo;

    public CondicionLog(Condicion condicion1) {
        this.condicion1 = condicion1;
    }

    public CondicionLog(Condicion condicion1, Condicion condicion2, Integer tipo) {
        this.condicion1 = condicion1;
        this.condicion2 = condicion2;
        this.tipo = tipo;
    }
    
    public Boolean obtenerValor(AnalizadorSintacticoPintar asp){
        if (tipo!=null) {
            Boolean b1=condicion1.obtenerValor(asp);
            Boolean b2=condicion2.obtenerValor(asp);
            
            if (b1!=null&&b2!=null) {
                switch (tipo) {
                case Inicializacion.AND:
                        return condicion1.obtenerValor(asp)&&condicion2.obtenerValor(asp);
                   
                case Inicializacion.OR:
                   
                        return condicion1.obtenerValor(asp)||condicion2.obtenerValor(asp);
                default:
                    throw new AssertionError();
            }
            }else{
                return null;
            }
            
        }else{
            return condicion1.obtenerValor(asp);
        }
    }
}
