/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegodeoca;

import java.io.Serializable;

/**
 *
 * @author Eli
 */
public class Premio implements Serializable{
    private String clavePremio;
    private String textoPremio;

    public Premio(String clavePremio, String textoPremio) {
        this.clavePremio = clavePremio;
        this.textoPremio = textoPremio;
    }

    public String getTextoPremio() {
        return textoPremio;
    }

    public void setTextoPremio(String textoPremio) {
        this.textoPremio = textoPremio;
    }

    public String getClavePremio() {
        return clavePremio;
    }

    public void setClavePremio(String clavePremio) {
        this.clavePremio = clavePremio;
    }
    
    
}
