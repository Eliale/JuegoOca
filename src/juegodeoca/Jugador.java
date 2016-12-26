/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegodeoca;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Eli
 */
public class Jugador {

    private String nombre;
    private int casilla_actual;
    private int casilla_anterior;
    private Color color;

    public Jugador() {
    }

    public Jugador(String nombre, Color color) {
        this.nombre = nombre;
        casilla_actual = 0;
        casilla_anterior = 0;
        this.color = color;
    }

    public int getCasilla_anterior() {
        return casilla_anterior;
    }

    public void setCasilla_anterior(int casilla_anterior) {
        this.casilla_anterior = casilla_anterior;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCasilla_actual() {
        return casilla_actual;
    }

    public void setCasilla_actual(int casilla_actual) {
        this.casilla_actual = casilla_actual;
    }
    
    

    public String[] Turno(Dado d, Pregunta[] preguntas, Comodin[] comodines, Premio[] premios, Castigo[] castigos) {
        String tiro[] = d.tira_dado();
        int avanza = Integer.parseInt(tiro[0]);
        JuegoForm.etiDado.setText("" + avanza);
        // En la tercera Posicion del arreglo devuelto ira la pregunta/comodin/premio o castigo generado con esto lo buscare :D
        String resultado[] = {"Fallo", avanza + "", ""};
        System.out.println("Resultado del tiro " + tiro[0] + " " + tiro[1]);
        // Pregunta

        if (avanza <= 3) {
            Pregunta pregunta_turno = GeneraPregunta(tiro[1], preguntas);
            if (pregunta_turno != null) {

                resultado[2] = pregunta_turno.getPregunta();
                String muestra = "";
                muestra = "Pregunta : " + (pregunta_turno.getPregunta()) + "?";
                Scanner lee = new Scanner(System.in);
                muestra += "\nPosibles Respuesta:\n";
                for (String respuestas_falsa : pregunta_turno.getRespuestas_falsas()) {
                    //System.out.println("Posible respuesta: " + respuestas_falsa);
                    muestra += "\n"+respuestas_falsa + "\n";
                }
                muestra += "\nIngrese Respuesta\n";
                // JOptionPane.showMessageDialog(null, muestra);

                String v = JOptionPane.showInputDialog(null, muestra,"Pregunta para : "+getNombre(),JOptionPane.PLAIN_MESSAGE);
                if (v.equalsIgnoreCase(pregunta_turno.getRespuesta_correcta())) {
                    resultado[0] = "Acierto";
                    setCasilla_actual(getCasilla_anterior() + avanza);
                    // System.out.println("CORRECTO ");
                    JOptionPane.showMessageDialog(null, "Correcto", "Respuesta Correcta", JOptionPane.OK_OPTION);
                } else {
                    setCasilla_actual(getCasilla_anterior());
                    //System.out.println("INCORRECTO ");
                    JOptionPane.showMessageDialog(null, "Incorrecto", "Respuesta incorrecta", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                System.err.println("error nos quedamos sin preguntas ");
                setCasilla_actual(getCasilla_anterior());

            }
        } else {
            // Aqui genera Comodin // Premio // Castigo
            switch (tiro[1]) {
                case "comodin": {
                    Comodin c = GeneraComodin(comodines);
                    resultado[2] = c.getClaveComodin();
                    JOptionPane.showMessageDialog(null, c.getTextoComodin(),"Comodin para " + getNombre(),JOptionPane.PLAIN_MESSAGE);
                    setCasilla_actual(getCasilla_anterior() + avanza);
                }
                break;
                case "premio": {
                    Premio p = GeneraPremio(premios);
                    resultado[2] = p.getClavePremio();
                    JOptionPane.showMessageDialog(null, p.getTextoPremio(),"Premio para " + getNombre(),JOptionPane.PLAIN_MESSAGE);
                    setCasilla_actual(getCasilla_anterior() + avanza);
                }
                break;
                case "castigo": {
                    Castigo cas = GeneraCastigo(castigos);
                    resultado[2] = cas.getClaveCastigo();
                    JOptionPane.showMessageDialog(null, cas.getTextCastigo(),"Castigo para " + getNombre(),JOptionPane.PLAIN_MESSAGE);
                    setCasilla_actual(getCasilla_anterior() + avanza);
                }
                break;
            }

            resultado[0] = tiro[1];
        }

        //System.out.println("Posicion  de jugador " + getNombre() + " : " + getCasilla_actual());
        setCasilla_anterior(getCasilla_actual());
        return resultado;
    }

    private Pregunta GeneraPregunta(String tematica, Pregunta[] preguntas) {
        ArrayList<Pregunta> lista = EncuentraPreguntasTematica(tematica, preguntas);
        int n = (int) (Math.random() * lista.size());

        System.out.println("TAMAÑO DE LISTA" + lista.size());
        System.out.println(n);
        if (lista.size() > 0) {
            return lista.get(n);
        } else {
            return null;
        }

    }

    private ArrayList<Pregunta> EncuentraPreguntasTematica(String tematica, Pregunta[] preguntas) {
        ArrayList<Pregunta> lista = new ArrayList<>();
        for (Pregunta pregunta : preguntas) {
            if (pregunta.getTematica().equalsIgnoreCase(tematica)) {
                lista.add(pregunta);
            }
        }
        return lista;
    }

    private Comodin GeneraComodin(Comodin[] comodines) {
        List<Comodin> lista = Arrays.asList(comodines);
        int n = (int) (Math.random() * lista.size());
        return lista.get(n);
    }

    private Premio GeneraPremio(Premio[] premios) {
        List<Premio> lista = Arrays.asList(premios);
        int n = (int) (Math.random() * lista.size());
        return lista.get(n);
    }

    private Castigo GeneraCastigo(Castigo[] castigos) {
        List<Castigo> lista = Arrays.asList(castigos);
        int n = (int) (Math.random() * lista.size());
        return lista.get(n);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
