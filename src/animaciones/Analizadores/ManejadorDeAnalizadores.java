/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animaciones.Analizadores;

import animaciones.Analizadores.Lexico.AnalizadorLexicoColores;
import animaciones.Analizadores.Lexico.AnalizadorLexicoLienzo;
import animaciones.Analizadores.Lexico.AnalizadorLexicoPintar;
import animaciones.Analizadores.Lexico.AnalizadorLexicoTiempo;
import animaciones.Analizadores.Semantico.TablaDeSimbolos;
import animaciones.Analizadores.Sintactico.*;
import animaciones.Objetos.Lienzo;
import animaciones.ui.IDE;
import animaciones.ui.backend.ManejadorDeEntrada;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sergio
 */
public class ManejadorDeAnalizadores {
private boolean analizado=false;
    private TablaDeSimbolos tds;
    public void analizar(IDE ide, ManejadorDeEntrada me) {
        analizado=false;
        if (true/*me.verificarFilesExistentes()*/) {
            ide.borrarOutput();
            ide.escribirEnOutput("Empezando Analisis...\n");
            ide.escribirEnOutput("Analisis De Archivo Lienzo\n");
             tds = new TablaDeSimbolos();
            try {
                BufferedReader br = new BufferedReader(new FileReader(me.obtenerFile(0)));
                 tds = new TablaDeSimbolos();
                AnalizadorLexicoLienzo all = new AnalizadorLexicoLienzo(br);
                AnalizadorSintacticoLienzo analizadorSintacticoLienzo = new AnalizadorSintacticoLienzo(all);
                analizadorSintacticoLienzo.setFrame(ide);
                analizadorSintacticoLienzo.setTabla(tds);
                analizadorSintacticoLienzo.parse();
                if (!analizadorSintacticoLienzo.error) {
                    if (analizadorSintacticoLienzo.errorRecuperable) {
                        ide.escribirEnOutput("Existen Errores en el Archivo .lnz pero fue posible recuperarse\n\n");
                    } else {
                        ide.escribirEnOutput("Archivo .lnz Analizado Correctamente\n\n");
                    }

                    ide.escribirEnOutput("Analisis De Archivo Color\n");
                    br = new BufferedReader(new FileReader(me.obtenerFile(1)));
                    AnalizadorLexicoColores alc = new AnalizadorLexicoColores(br);
                    AnalizadorSintacticoColores asc = new AnalizadorSintacticoColores(alc);
                    asc.setFrame(ide);
                    asc.setTabla(tds);
                    asc.parse();
                    if (!asc.error) {
                        if (asc.errorRecuperable) {
                            ide.escribirEnOutput("Existen Errores en el Archivo .clrs pero fue posible recuperarse\n");
                        } else {
                            ide.escribirEnOutput("Archivo .clrs Analizado Correctamente\n\n");
                        }
                        ide.escribirEnOutput("Analisis De Archivo Tiempo\n");
                        br = new BufferedReader(new FileReader(me.obtenerFile(2)));
                        AnalizadorLexicoTiempo alt = new AnalizadorLexicoTiempo(br);
                        AnalizadorSintacticoTiempo ast = new AnalizadorSintacticoTiempo(alt);
                        ast.setFrame(ide);
                        ast.setTabla(tds);
                        ast.parse();
                        if (!ast.error) {
                            if (ast.errorRecuperable) {
                                ide.escribirEnOutput("Existen Errores en el Archivo .tmp pero fue posible recuperarse\n");
                            } else {
                                ide.escribirEnOutput("Archivo .tmp Analizado Correctamente\n\n");
                            }
                            ide.escribirEnOutput("Analisis De Archivo Pintar\n");
                            br = new BufferedReader(new FileReader(me.obtenerFile(3)));
                            AnalizadorLexicoPintar alp = new AnalizadorLexicoPintar(br);
                            AnalizadorSintacticoPintar asp = new AnalizadorSintacticoPintar(alp);
                            asp.setFrame(ide);
                            asp.setTabla(tds);
                            asp.parse();
                            if (!ast.error) {
                                if (ast.errorRecuperable) {
                                    ide.escribirEnOutput("Existen Errores en el Archivo .pnt pero fue posible recuperarse\n");
                                } else {
                                    ide.escribirEnOutput("Archivo .pnt Analizado Correctamente\n\n");
                                }
                                    ide.escribirEnOutput("Listo Para Generar\n");
                                
                                analizado=true;
                            } else {
                                ide.escribirEnOutput("Error: No fue posible recuperarse del Arhcivo .pnt\n");
                            }
                        } else {
                            ide.escribirEnOutput("Error: No fue posible recuperarse del Arhcivo .tmp\n");
                        }
                    } else {
                        ide.escribirEnOutput("Error: No fue posible recuperarse del Arhcivo .clrs\n");
                    }

                } else {
                    ide.escribirEnOutput("Error: No fue posible recuperarse del Arhcivo .lnz\n");
                }
                
                
                System.out.println("TOTAL DE ELEMENTO EN LA TABLA:" + tds.getObjetos().size());
                for (int i = 0; i < tds.getObjetos().size(); i++) {
                    System.out.println("ID: "+tds.getObjetos().get(i).getId());
                    System.out.println("TIPO: "+tds.getObjetos().get(i).getTipo());
                    System.out.println("VALOR: "+tds.getObjetos().get(i).getValor());
                    
                    System.out.println("================");
                }

            } catch (FileNotFoundException ex) {
                Logger.getLogger(ManejadorDeAnalizadores.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(ManejadorDeAnalizadores.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            ide.escribirEnOutput("Debes Abrir los Cuatro Archivos Para Analizar\n");
        }
    }

    public ArrayList<Lienzo> obtenerLienzos(){
        ArrayList<Lienzo> lienzos = new ArrayList<>();
        for (int i = 0; i < tds.getObjetos().size(); i++) {
   
            if (tds.getObjetos().get(i).getTipo()==TablaDeSimbolos.TIPO_LIENZO) {
                lienzos.add((Lienzo)tds.getObjetos().get(i).getValor());
            }
        }
        return lienzos;
    }
    
    public boolean isAnalizado() {
        return analizado;
    }

}
