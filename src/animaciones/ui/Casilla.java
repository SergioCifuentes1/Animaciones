package animaciones.ui;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author sergio
 */
public class Casilla extends JPanel {

    public static final int CASILLA_ANCHO = 50;
    public static final int CASILLA_ALTO = 50;
    private int[] coordenadas;
    private PanelLienzo pl;
    private Color color;
    private String idColor;

    public Casilla(Color color,int tam,PanelLienzo pl) {
        this.pl=pl;
        this.setSize(tam, tam);
        this.color = color;
        this.setBackground(this.color);
        this.setOpaque(true);
        this.addMouseListener(new java.awt.event.MouseAdapter() {

          

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PanelMouseClicked(evt);
            }
        });
    }
    
    public void agregarPl(PanelLienzo pl){
        this.pl=pl;
        this.addMouseListener(new java.awt.event.MouseAdapter() {

          

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PanelMouseClicked(evt);
            }
        });
    }
    
        public Casilla(Color color,int tam) {
        this.setVisible(true);
        this.setSize(tam, tam);
        this.color = color;
        this.setBackground(this.color);
        this.setOpaque(true);

    }
//Identificador de pieza que existe en la casilla

    private void PanelMouseClicked(java.awt.event.MouseEvent evt) {
        if (pl.isBorrador()) {
            this.color=pl.getLienzo().getFondo();
            this.setColor(color, null);
            this.setBackground(color);
            this.validate();
            this.repaint();
        }else{
            this.color=pl.getColorActual();
            this.setColor(color, pl.idColor);
            this.setBackground(color);
            this.validate();
            this.repaint();
            
        }
    }

    public Color getColor() {
        return color;
    }

    public String getIdColor() {
        return idColor;
    }
    
    
    public void setCoordenadas(int coordenadasAncho, int coordenadasAlto) {
        this.coordenadas = new int[2];
        coordenadas[0] = coordenadasAncho;
        coordenadas[1] = coordenadasAlto;
    }

    public int[] getCoordenadas() {
        return coordenadas;
    }


    public void setColor(Color color,String id) {
        this.color = color;
        this.idColor =id;
        this.setBackground(color);

    }

//Metodo que funciona para el mouseEntered de las casillas
    public void regresarColor() {
        this.setBackground(color);
    }
//En caso de que la casilla sea el camino de alguna pieza

}
