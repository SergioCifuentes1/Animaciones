/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animaciones.Analizadores.Semantico;

/**
 *
 * @author sergio
 */
public class Objeto {
    private String id;
    private int tipo;
    private Object valor;
    private String idPert=null;
    
    public Objeto(String id, int tipo, Object valor) {
        this.id = id;
        this.tipo = tipo;
        this.valor = valor;
        
    }
    public Objeto(String id, int tipo, Object valor,String id_lienzoPerteneciente) {
        this.id = id;
        this.tipo = tipo;
        this.valor = valor;
        this.idPert=id_lienzoPerteneciente;
    }

    public String getIdPert() {
        return idPert;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    public void setIdPert(String idPert) {
        this.idPert = idPert;
    }
    
    public String getId() {
        return id;
    }

    public int getTipo() {
        return tipo;
    }

    public Object getValor() {
        return valor;
    }
    
    
    
}
