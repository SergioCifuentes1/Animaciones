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
public class Sentencia extends Instruccion{
    private String id;
    private Operacion op;
    private String idValor;
    private String string;
    private CondicionLog condicionLog;

    public Sentencia(String id, Operacion op) {
        this.id = id;
        this.op = op;
       
        
    }

    public Sentencia(String id, String string) {
        this.id = id;
        this.string = string;
    }

    public Sentencia(String id, CondicionLog condicionLog) {
        this.id = id;
        this.condicionLog = condicionLog;
    }
    
        public Sentencia(String id, String id2,String aux) {
        this.id = id;
        this.idValor= id2;
    }
    @Override
    public void ejecutar(AnalizadorSintacticoPintar asp,ArrayList<Pintar> pintar,String IdLienzo){
        Integer tipo=asp.tablaDeSimbolos.obtenerTipo(id);
        if (tipo!=null) {
            switch (tipo) {
                case TablaDeSimbolos.TIPO_INT:
                    if (op!=null) { 
                        
                        Integer op1 = op.obtenerValor(asp);
                        if (op1!=null) {
                             asp.tablaDeSimbolos.InsertarValor(id,tipo,op.obtenerValor(asp));
                        }else{
                            asp.getIde().escribirEnOutput("No se pudo asignarle el valor a "+id);
                        }
                       
                        
                    }else if(idValor!=null){
                        if (asp.tablaDeSimbolos.obtenerExistencia(idValor,tipo)!=null) {  
                            
                            Integer valorNuevo = (Integer)((Objeto)asp.tablaDeSimbolos.obtenerExistencia(idValor,tipo)).getValor();
                            
                            if (valorNuevo!=null) {
                                asp.tablaDeSimbolos.InsertarValor(id,tipo,valorNuevo);
                            }else{
                                asp.getIde().escribirEnOutput("Error Semantico\nLa variable que se le desea asignar a "+idValor+" es nula\n");
                            }
                        }else{
                            asp.getIde().escribirEnOutput("Error Semantico\nLa variable int "+idValor+" no existe\n");
                        }
                        
                    }else{
                        asp.getIde().escribirEnOutput("Error Semantico\nLexema: "+id+" ,Tipos Diferentes para Sentencia\n");
                    }
                    
                    break;
                case TablaDeSimbolos.TIPO_STRING:
                    
                    if (string!=null) { 
                        
                        
                             asp.tablaDeSimbolos.InsertarValor(id,tipo,string);
                        
                       
                        
                    }else if(idValor!=null){
                        if (asp.tablaDeSimbolos.obtenerExistencia(idValor,tipo)!=null) {  
                            
                            String valorNuevo = (String)((Objeto)asp.tablaDeSimbolos.obtenerExistencia(idValor,tipo)).getValor();
                            
                            if (valorNuevo!=null) {
                                asp.tablaDeSimbolos.InsertarValor(id,tipo,valorNuevo);
                            }else{
                                asp.getIde().escribirEnOutput("Error Semantico\nLa variable que se le desea asignar a "+idValor+" es nula\n");
                            }
                        }else{
                            asp.getIde().escribirEnOutput("Error Semantico\nLa variable String "+idValor+" no existe\n");
                        }
                        
                    }else{
                        asp.getIde().escribirEnOutput("Error Semantico\nLexema: "+id+" ,Tipos Diferentes para Sentencia\n");
                    }
                    
                    
                    
                    
                    
                    
                    break;
                case TablaDeSimbolos.TIPO_BOOLEAN:
                    
                    if (condicionLog!=null) { 
                        
                        Boolean op1 = condicionLog.obtenerValor(asp);
                        if (op1!=null) {
                             asp.tablaDeSimbolos.InsertarValor(id,tipo,op1);
                        }else{
                            asp.getIde().escribirEnOutput("No se pudo asignarle el valor a "+id);
                        }
                       
                        
                    }else if(idValor!=null){
                        if (asp.tablaDeSimbolos.obtenerExistencia(idValor,tipo)!=null) {  
                            
                            Boolean valorNuevo = (Boolean)((Objeto)asp.tablaDeSimbolos.obtenerExistencia(idValor,tipo)).getValor();
                            
                            if (valorNuevo!=null) {
                                asp.tablaDeSimbolos.InsertarValor(id,tipo,valorNuevo);
                            }else{
                                asp.getIde().escribirEnOutput("Error Semantico\nLa variable que se le desea asignar a "+idValor+" es nula\n");
                            }
                            
                        }else{
                            asp.getIde().escribirEnOutput("Error Semantico\nLa variable int "+idValor+" no existe\n");
                        }
                        
                    }else{
                        asp.getIde().escribirEnOutput("Error Semantico\nLexema: "+id+" ,Tipos Diferentes para Sentencia\n");
                    }
                    
                    
                    break;    
                default:
                    asp.getIde().escribirEnOutput("Error Semantico\n"+id+" no existe\n");
            }
        }else{
            asp.getIde().escribirEnOutput("Error Semantico\n"+id+" no existe\n");
        }
   }
    
}
