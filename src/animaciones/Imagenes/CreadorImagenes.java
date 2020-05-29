/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animaciones.Imagenes;

import animaciones.Analizadores.Semantico.Instrucciones.Pintar;
import animaciones.Objetos.Imagen;
import animaciones.Objetos.Lienzo;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.JPanel;

/**
 *
 * @author sergio
 */
public class CreadorImagenes {

    File imagenes = new File("./Imagenes");

    public void crearImagenes(ArrayList<Lienzo> lienzos,File pintar) {
        if (!imagenes.exists()) {
            imagenes.mkdir();
            imagenes.mkdirs();
        }
        
        String texto=obtenerDeclaraciones(pintar);
        for (int i = 0; i < lienzos.size(); i++) {
            texto+=escribirArchivoPintar(lienzos.get(i));
            try {
                crear(lienzos.get(i));
            } catch (IOException ex) {
                Logger.getLogger(CreadorImagenes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            FileWriter fw = new FileWriter(pintar);
            fw.write(texto);
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(CreadorImagenes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void crearImagenes(ArrayList<Lienzo> lienzos) {
        if (!imagenes.exists()) {
            imagenes.mkdir();
            imagenes.mkdirs();
        }
        
        for (int i = 0; i < lienzos.size(); i++) {
            
            try {
                crear(lienzos.get(i));
            } catch (IOException ex) {
                Logger.getLogger(CreadorImagenes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    
    private String obtenerDeclaraciones(File pintar){
        String text="";
        text+="VARS[\n";
        text+="int ejemplo = 10;\n";
        text+="]\n";
        return text;
    }
    
    private String escribirArchivoPintar(Lienzo lienzo){
        String text="";
        text+="INSTRUCCIONES("+lienzo.getId()+")[\n";
        ArrayList<Pintar> pintar = lienzo.obtenerPintarEditado();
        for (int i = 0; i < pintar.size(); i++) {
            text+="PINTAR(\""+pintar.get(i).getIdColor()+"\",\""+pintar.get(i).getIdImagen()+"\","+pintar.get(i).getX()+","+pintar.get(i).getY()+");\n";
        }
        text+="]\n";
        return text;
    }
    
    private void crear(Lienzo lienzo) throws IOException {
        String type;
        type = "png";
        File file = new File(imagenes.getPath() + "/" + lienzo.getId());
        if (!file.exists()) {
            file.mkdir();
            file.mkdirs();
        }
        ArrayList<Imagen> imagenesAimprimir = obtenerCantidad(lienzo.getTiempo().getImagenes(), lienzo.getTiempo().getIdInicio(), lienzo.getTiempo().getIdFin());
        for (int i = 0; i < imagenesAimprimir.size(); i++) {

            BufferedImage image = new BufferedImage(imagenesAimprimir.get(i).getImagen().getWidth(), imagenesAimprimir.get(i).getImagen().getHeight(),
                    BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = image.createGraphics();
            lienzo.getTiempo().getImagenes().get(i).getImagen().paint(g2);
            try {
                ImageIO.write(image, type, new File(file.getPath() + "/" + imagenesAimprimir.get(i).getId() + "." + type));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        switch (lienzo.getTipo()) {
            case Lienzo.TIPO_PNG:

                break;
            case Lienzo.TIPO_GIF:
                GifSequenceWriter writer = null;
                ImageOutputStream output = null;
                for (int i = 0; i < imagenesAimprimir.size(); i++) {
                    if (i == 0) {
                        BufferedImage first = null;
                        try {
                            first = ImageIO.read(new File(file.getPath() + "/" + imagenesAimprimir.get(i).getId() + "." + type));
                        } catch (IOException ex) {
                            Logger.getLogger(CreadorImagenes.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        output = new FileImageOutputStream(new File(file.getPath()  + ".gif"));

                        try {
                            writer = new GifSequenceWriter(output, first.getType(), imagenesAimprimir.get(i).getDuracion(), true);
                        } catch (IOException ex) {
                            Logger.getLogger(CreadorImagenes.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        writer.writeToSequence(first);
                    } else {
                        BufferedImage next = ImageIO.read(new File(file.getPath() + "/" + imagenesAimprimir.get(i).getId() + "." + type));
                        writer.writeToSequence(next);
                    }
                    File aEliminar =new File(file.getPath() + "/" + imagenesAimprimir.get(i).getId() + "." + type);
                    aEliminar.delete();
                }
                writer.close();
                output.close();
                file.delete();
                break;

            default:
                throw new AssertionError();
        }

    }

    public ArrayList<Imagen> obtenerCantidad(ArrayList<Imagen> imagenes, String inicio, String fin) {
        ArrayList<Imagen> panels = new ArrayList<>();
        for (int i = 0; i < imagenes.size(); i++) {

            if (i == imagenes.size() - 1 && !imagenes.get(i).getId().equals(fin)) {
                for (int j = 1; j < panels.size(); j++) {
                    panels.remove(j);
                }

                break;
            } else if (panels.size() > 0 && !imagenes.get(i).getId().equals(fin)) {
                panels.add(imagenes.get(i));
            } else if (imagenes.get(i).getId().equals(inicio)) {
                panels.add(imagenes.get(i));
            } else if (imagenes.get(i).getId().equals(fin)) {
                panels.add(imagenes.get(i));
                break;
            }

        }
        return panels;
    }
}
