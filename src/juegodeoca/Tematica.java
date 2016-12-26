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
public class Tematica implements Serializable{
    private String NombreTematica;
    private String Estatus;

    public Tematica(String NombreTematica, String Estatus) {
        this.NombreTematica = NombreTematica;
        this.Estatus = Estatus;
    }
    
    
    public String getNombreTematica() {
        return NombreTematica;
    }

    public void setNombreTematica(String NombreTematica) {
        this.NombreTematica = NombreTematica;
    }

    public String getEstatus() {
        return Estatus;
    }

    public void setEstatus(String Estatus) {
        this.Estatus = Estatus;
    }
    
}
