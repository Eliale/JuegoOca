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
public class Pregunta implements Serializable{
    private String pregunta;
    private String respuesta_correcta;
    private String respuestas_falsas [];
    private String tematica;

    public Pregunta(String pregunta, String respuesta_correcta, String tematica,String[] respuestas_incorrectas) {
        this.pregunta = pregunta;
        this.respuesta_correcta = respuesta_correcta;     
        this.tematica = tematica;
        this.respuestas_falsas = respuestas_incorrectas;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuesta_correcta() {
        return respuesta_correcta;
    }

    public void setRespuesta_correcta(String respuesta_correcta) {
        this.respuesta_correcta = respuesta_correcta;
    }

    public String[] getRespuestas_falsas() {
        return respuestas_falsas;
    }

    public void setRespuestas_falsas(String[] respuestas_falsas) {
        this.respuestas_falsas = respuestas_falsas;
    }

    @Override
    public String toString() {
        return "Pregunta{" + "pregunta=" + pregunta + ", respuesta_correcta=" + respuesta_correcta + ", respuestas_falsas=" + respuestas_falsas + ", tematica=" + tematica + '}';
    }

    
    
}
