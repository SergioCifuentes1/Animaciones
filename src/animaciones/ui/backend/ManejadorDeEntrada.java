/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animaciones.ui.backend;

import animaciones.ui.IDE;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author sergio
 */
public class ManejadorDeEntrada {

    private IDE ide;
    private File[] files;
    public final static String EXTENSION_LIENZO = ".lnz";
    public final static String EXTENSION_TIEMPO = ".tmp";
    public final static String EXTENSION_COLORES = ".clrs";
    public final static String EXTENSION_PINTAR = ".pnt";

    public ManejadorDeEntrada(IDE ide) {
        this.ide = ide;
        files = new File[4];
    }

    public boolean verificarFilesExistentes(){
        for (int i = 0; i < files.length; i++) {
            if (files[i]==null) {
                return false;
            }
        }
        return true;
    }
    public File obtenerFile(int index){
        return files[index];
    }
    public void abrirArchivo(File file) {
     
        try {
            String nombre = file.getName();
            BufferedReader br = new BufferedReader(new FileReader(file));
            String linea = br.readLine();
            String texto = "";
            while (linea != null) {
                texto += linea + "\n";
                linea = br.readLine();
            }

            if (file.getPath().endsWith(EXTENSION_LIENZO)) {
                files[0] = file;
                ide.setTxtAreaLienzo(nombre, texto);
                ide.moverTab(0);
            } else if (file.getPath().endsWith(EXTENSION_COLORES)) {
                files[1] = file;
                ide.moverTab(1);
                ide.setTxtAreaColor(nombre, texto);
            } else if (file.getPath().endsWith(EXTENSION_TIEMPO)) {
                files[2] = file;
                ide.moverTab(2);
                ide.setTxtAreaTiempo(nombre, texto);
            } else if (file.getPath().endsWith(EXTENSION_PINTAR)) {
                files[3] = file;
                ide.moverTab(3);
                ide.setTxtAreaPintar(nombre, texto);
            } else {
                JOptionPane.showMessageDialog(ide, "Extension Incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            Logger.getLogger(ManejadorDeEntrada.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardarFiles() {
        FileWriter fw;
        for (int i = 0; i < files.length; i++) {
            if (files[i] != null) {
                try {
                    fw = new FileWriter(files[i]);
                    fw.write(ide.obtenreTexto(i));
                    fw.close();
                } catch (IOException ex) {
                    Logger.getLogger(ManejadorDeEntrada.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }

    public void mostrarRuta(int index, JLabel label) {
        if (files[index] != null) {
            label.setText("Ruta: " + files[index].getPath());
        }
    }
}
