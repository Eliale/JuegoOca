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
public class Castigo implements Serializable{
    private String claveCastigo;
    private String textCastigo;

    public Castigo(String claveCastigo, String textCastigo) {
        this.claveCastigo = claveCastigo;
        this.textCastigo = textCastigo;
    }

    public String getTextCastigo() {
        return textCastigo;
    }

    public void setTextCastigo(String textCastigo) {
        this.textCastigo = textCastigo;
    }

    public String getClaveCastigo() {
        return claveCastigo;
    }

    public void setClaveCastigo(String claveCastigo) {
        this.claveCastigo = claveCastigo;
    }
    
}
