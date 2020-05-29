/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animaciones.Analizadores.Semantico.Instrucciones;

import animaciones.Analizadores.Lexico.AnalizadorLexicoPintar;
import animaciones.Analizadores.Sintactico.AnalizadorSintacticoPintar;
import java.util.ArrayList;

/**
 *
 * @author sergio
 */
public abstract class Instruccion {
    private int tipo;
   public static final int TIPO_SENTENCIA=1;
   public static final int TIPO_PINTAR=2;
   public static final int TIPO_IF=3;
   public static final int TIPO_WHILE=4;
   
   public abstract void ejecutar(AnalizadorSintacticoPintar asp,ArrayList<Pintar> pintar,String IdLienzo);
   
}
