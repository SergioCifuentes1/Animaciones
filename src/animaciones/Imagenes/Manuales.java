/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animaciones.Imagenes;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author sergio
 */
public class Manuales {
    private static final String USARIO_PATH="./Manuales/Usuario.pdf";
    private static final String TECNICO_PATH="./Manuales/Tecnico.pdf";
    public void abrirManualDeUsuario(){
        if (Desktop.isDesktopSupported()) {
    try {
        File myFile = new File(USARIO_PATH);
        Desktop.getDesktop().open(myFile);
    } catch (IOException ex) {
        // no application registered for PDFs
    }
}
    }
    
    public void abrirManualDeTecnico(){
        if (Desktop.isDesktopSupported()) {
    try {
        File myFile = new File(TECNICO_PATH);
        Desktop.getDesktop().open(myFile);
    } catch (IOException ex) {
        // no application registered for PDFs
    }
}
    }
}
