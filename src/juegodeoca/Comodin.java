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
public class Comodin implements Serializable{

    private String claveComodin;
    private String textoComodin;

    public Comodin(String claveComodin, String textoComodin) {
        this.claveComodin = claveComodin;
        this.textoComodin = textoComodin;
    }

    public String getTextoComodin() {
        return textoComodin;
    }

    public void setTextoComodin(String textoComodin) {
        this.textoComodin = textoComodin;
    }

    public String getClaveComodin() {
        return claveComodin;
    }

    public void setClaveComodin(String claveComodin) {
        this.claveComodin = claveComodin;
    }

}
