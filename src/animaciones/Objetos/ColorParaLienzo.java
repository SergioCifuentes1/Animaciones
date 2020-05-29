/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animaciones.Objetos;

import java.awt.Color;

/**
 *
 * @author sergio
 */
public class ColorParaLienzo {
    private Color color;
    private String id;

    public ColorParaLienzo(Color color, String id) {
        this.color = color;
        this.id = id;
    }

    public Color getColor() {
        return color;
    }

    public String getId() {
        return id;
    }
    
    
    
}
