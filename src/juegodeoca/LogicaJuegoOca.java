/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegodeoca;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Eli
 */
public class LogicaJuegoOca {

    private Jugador jugadores[];
    private List<Pregunta> ListaPreguntas;
    private List<Premio> ListaPremios;
    private List<Castigo> ListaCastigos;
    private List<Comodin> ListaComodines;

    public LogicaJuegoOca(Jugador[] jugadores) {
        this.jugadores = jugadores;
        ListaPreguntas = new ArrayList<>();
        ListaPremios = new ArrayList<>();
        ListaCastigos = new ArrayList<>();
        ListaComodines = new ArrayList<>();
    }

    public Jugador[] getJugadores() {
        return jugadores;
    }

    public void setJugadores(Jugador[] jugadores) {
        this.jugadores = jugadores;
    }

    private Jugador JugadorMejorPuntaje(Jugador[] jugadores) {
        Jugador jugador_puntaje_alto = new Jugador();
        // Suponemos que el de mayor puntaje es el primer jugador
        int mayor_puntaje = jugadores[0].getCasilla_actual();
        for (int i = 1; i < jugadores.length; i++) {
            if (mayor_puntaje < jugadores[i].getCasilla_actual()) {
                mayor_puntaje = jugadores[i].getCasilla_actual();
                jugador_puntaje_alto = jugadores[i];
            }
        }
        return jugador_puntaje_alto;
    }

    private boolean EstaEnPreguntas(String clv) {
        boolean esta = false;
        Iterator<Pregunta> it = ListaPreguntas.iterator();
        while (it.hasNext()) {
            Pregunta temPre = it.next();
            if (temPre.getPregunta().equalsIgnoreCase(clv)) {
                esta = true;
            }
        }
        return esta;
    }

    private boolean EstaEnComodin(String clv) {
        boolean esta = false;
        Iterator<Comodin> it = ListaComodines.iterator();
        while (it.hasNext()) {
            Comodin tempCom = it.next();
            if (tempCom.getClaveComodin().equalsIgnoreCase(clv)) {
                esta = true;
            }
        }
        return esta;
    }

    private boolean EstaEnPremios(String clv) {
        boolean esta = false;
        Iterator<Premio> it = ListaPremios.iterator();
        while (it.hasNext()) {
            Premio tempPrem = it.next();
            if (tempPrem.getClavePremio().equalsIgnoreCase(clv)) {
                esta = true;
            }
        }
        return esta;
    }

    private boolean EstaEnCastigos(String clv) {
        boolean esta = false;
        Iterator<Castigo> it = ListaCastigos.iterator();
        while (it.hasNext()) {
            Castigo tempC = it.next();
            if (tempC.getClaveCastigo().equalsIgnoreCase(clv)) {
                esta = true;
            }
        }
        return esta;
    }

    private List<Pregunta> convierteAListPre(Pregunta[] preguntas) {
        List<Pregunta> lista = new ArrayList<>();
        for (Pregunta pregunta : preguntas) {
            lista.add(pregunta);
        }
        return lista;
    }

    private List<Comodin> convierteAListComodin(Comodin[] comodines) {
        List<Comodin> lista = new ArrayList<>();
        for (Comodin comodin : comodines) {
            lista.add(comodin);
        }
        return lista;
    }

    private List<Castigo> convierteAListCastigo(Castigo[] castigos) {
        List<Castigo> lista = new ArrayList<>();
        for (Castigo castigo : castigos) {
            lista.add(castigo);
        }
        return lista;
    }

    private List<Premio> convierteAListPremio(Premio[] premios) {
        List<Premio> lista = new ArrayList<>();
        for (Premio premio : premios) {
            lista.add(premio);
        }
        return lista;
    }

    public Jugador Game(String temas[], Pregunta[] preguntas, Comodin[] comodines, Premio[] premios, Castigo[] castigos, Dado d) {
        ListaPreguntas = convierteAListPre(preguntas);
        ListaCastigos = convierteAListCastigo(castigos);
        ListaComodines = convierteAListComodin(comodines);
        ListaPremios = convierteAListPremio(premios);

        int i = 0;
        while (JugadorMejorPuntaje(getJugadores()).getCasilla_actual() <= 50) {
            // Tiro para cada jugador en la lista
            String jugadorturno = getJugadores()[i].getNombre();
            // System.out.println("Turno de jogador " + getJugadores()[i].getNombre());
            JuegoForm.etiNombreJugador.setText("" + getJugadores()[i].getNombre());
            JOptionPane.showMessageDialog(null, "Tira : " + jugadorturno, "Turno del jugador", JOptionPane.OK_OPTION);

            String[] temp = new String[2];
            // Tira
            Pregunta[] Array_pregunta = new Pregunta[ListaPreguntas.size()];
            Array_pregunta = ListaPreguntas.toArray(Array_pregunta);
            Comodin[] Array_comodin = new Comodin[ListaComodines.size()];
            Array_comodin = ListaComodines.toArray(Array_comodin);
            Premio[] Array_premio = new Premio[ListaPremios.size()];
            Array_premio = ListaPremios.toArray(Array_premio);
            Castigo[] Array_castigo = new Castigo[ListaCastigos.size()];
            Array_castigo = ListaCastigos.toArray(Array_castigo);

            temp = getJugadores()[i].Turno(d, Array_pregunta, Array_comodin, Array_premio, Array_castigo);
            String res = "Tipo de tiro: " + temp[0] + "\n avanza : " + temp[1] + "\n";
            // JOptionPane.showMessageDialog(null, res, "Resultado del turno", JOptionPane.PLAIN_MESSAGE);

            if (EstaEnCastigos(temp[2])) {
                for (int j = 0; j < ListaCastigos.size(); j++) {
                    if (ListaCastigos.get(j).getClaveCastigo().equalsIgnoreCase(temp[2])) {
                        if (ListaCastigos.size() > 1) {
                            ListaCastigos.remove(j);
                        } else {
                            // Si se acaban los castigos volvemos a empezar 
                            ListaCastigos = convierteAListCastigo(castigos);
                        }
                    }
                }
            }
            if (EstaEnPremios(temp[2])) {
                for (int j = 0; j < ListaPremios.size(); j++) {
                    if (ListaPremios.get(j).getClavePremio().equalsIgnoreCase(temp[2])) {
                        if (ListaPremios.size() > 1) {
                            ListaPremios.remove(j);
                        } else {
                            // Si se acaban los premios volvemos a empezar 
                            ListaPremios = convierteAListPremio(premios);
                        }
                    }
                }
            }
            if (EstaEnComodin(temp[2])) {
                for (int j = 0; j < ListaComodines.size(); j++) {
                    if (ListaComodines.get(j).getClaveComodin().equalsIgnoreCase(temp[2])) {
                        if (ListaComodines.size() > 1) {
                            ListaComodines.remove(j);
                        } else {
                            // Si se acaban los castigos volvemos a empezar 
                            ListaComodines = convierteAListComodin(comodines);
                        }
                    }
                }
            }
            if (EstaEnPreguntas(temp[2])) {
                for (int j = 0; j < ListaPreguntas.size(); j++) {
                    if (ListaPreguntas.get(j).getPregunta().equalsIgnoreCase(temp[2])) {
                        if (ListaPreguntas.size() > 1) {
                            ListaPreguntas.remove(j);
                        } else {
                            // Si se acaban los castigos volvemos a empezar 
                            ListaPreguntas = convierteAListPre(preguntas);
                        }
                    }
                }
            }

            String va_ganando = JugadorMejorPuntaje(getJugadores()).getNombre();
            if (va_ganando != null) {
                JuegoForm.etiJugadorPuntero.setText(JugadorMejorPuntaje(getJugadores()).getNombre());
                //JOptionPane.showMessageDialog(null, va_ganando,"Pugador con mejor puntaje" ,JOptionPane.OK_OPTION);
            }

            //Esta es la casilla que se debe pintar
            int casilla = (getJugadores()[i].getCasilla_actual());
            pinta_casillero(casilla,getJugadores()[i].getColor());
            i++;
            if (i == getJugadores().length) {
                i = 0;
            }

        }
        return JugadorMejorPuntaje(getJugadores());
    }

    public void pinta_casillero(int n,Color color) {
        
        if (JuegoForm.btn1.getText().equalsIgnoreCase(n + "")) {
            JuegoForm.btn1.setBackground(color);
            
        }
        if (JuegoForm.btn2.getText().equalsIgnoreCase(n + "")) {
            JuegoForm.btn2.setBackground(color);
        }
        if (JuegoForm.btn3.getText().equalsIgnoreCase(n + "")) {
            JuegoForm.btn3.setBackground(color);
        }
        if (JuegoForm.btn4.getText().equalsIgnoreCase(n + "")) {
            JuegoForm.btn4.setBackground(color);
        }
        if (JuegoForm.btn5.getText().equalsIgnoreCase(n + "")) {
           JuegoForm.btn5.setBackground(color);
        }
        if (JuegoForm.btn6.getText().equalsIgnoreCase(n + "")) {
            JuegoForm.btn6.setBackground(color);
        }
        if (JuegoForm.btn7.getText().equalsIgnoreCase(n + "")) {
           JuegoForm.btn7.setBackground(color);
        }
        if (JuegoForm.btn8.getText().equalsIgnoreCase(n + "")) {
            JuegoForm.btn8.setBackground(color);
        }
        if (JuegoForm.btn9.getText().equalsIgnoreCase(n + "")) {
            JuegoForm.btn9.setBackground(color);
        }
        if (JuegoForm.btn10.getText().equalsIgnoreCase(n + "")) {
            JuegoForm.btn10.setBackground(color);
        }
        if (JuegoForm.btn11.getText().equalsIgnoreCase(n + "")) {
           JuegoForm.btn11.setBackground(color);
        }
        if (JuegoForm.btn12.getText().equalsIgnoreCase(n + "")) {
            JuegoForm.btn12.setBackground(color);
        }
        if (JuegoForm.btn13.getText().equalsIgnoreCase(n + "")) {
            JuegoForm.btn13.setBackground(color);
        }
        if (JuegoForm.btn14.getText().equalsIgnoreCase(n + "")) {
            JuegoForm.btn14.setBackground(color);
        }
        if (JuegoForm.btn15.getText().equalsIgnoreCase(n + "")) {
            JuegoForm.btn15.setBackground(color);
        }
        if (JuegoForm.btn16.getText().equalsIgnoreCase(n + "")) {
            JuegoForm.btn16.setBackground(color);
        }
        if (JuegoForm.btn17.getText().equalsIgnoreCase(n + "")) {
            JuegoForm.btn17.setBackground(color);
        }
        if (JuegoForm.btn18.getText().equalsIgnoreCase(n + "")) {
            JuegoForm.btn18.setBackground(color);
        }
        if (JuegoForm.btn19.getText().equalsIgnoreCase(n + "")) {
            JuegoForm.btn19.setBackground(color);
        }
        if (JuegoForm.btn20.getText().equalsIgnoreCase(n + "")) {
            JuegoForm.btn20.setBackground(color);
        }
        if (JuegoForm.btn21.getText().equalsIgnoreCase(n + "")) {
            JuegoForm.btn21.setBackground(color);
        }
        if (JuegoForm.btn22.getText().equalsIgnoreCase(n + "")) {
            JuegoForm.btn22.setBackground(color);
        }
        if (JuegoForm.btn23.getText().equalsIgnoreCase(n + "")) {
            JuegoForm.btn23.setBackground(color);
        }
        if (JuegoForm.btn24.getText().equalsIgnoreCase(n + "")) {
            JuegoForm.btn24.setBackground(color);
        }
        if (JuegoForm.btn25.getText().equalsIgnoreCase(n + "")) {
            JuegoForm.btn25.setBackground(color);
        }
        
       if (JuegoForm.btn26.getText().equalsIgnoreCase(n + "")) {
            JuegoForm.btn26.setBackground(color);
            
        }
        if (JuegoForm.btn27.getText().equalsIgnoreCase(n + "")) {
            JuegoForm.btn27.setBackground(color);
        }
        if (JuegoForm.btn28.getText().equalsIgnoreCase(n + "")) {
            JuegoForm.btn28.setBackground(color);
        }
        if (JuegoForm.btn29.getText().equalsIgnoreCase(n + "")) {
            JuegoForm.btn29.setBackground(color);
        }
        if (JuegoForm.btn30.getText().equalsIgnoreCase(n + "")) {
           JuegoForm.btn30.setBackground(color);
        }
        if (JuegoForm.btn31.getText().equalsIgnoreCase(n + "")) {
            JuegoForm.btn31.setBackground(color);
        }
        if (JuegoForm.btn32.getText().equalsIgnoreCase(n + "")) {
           JuegoForm.btn32.setBackground(color);
        }
        if (JuegoForm.btn33.getText().equalsIgnoreCase(n + "")) {
            JuegoForm.btn33.setBackground(color);
        }
        if (JuegoForm.btn34.getText().equalsIgnoreCase(n + "")) {
            JuegoForm.btn34.setBackground(color);
        }
        if (JuegoForm.btn35.getText().equalsIgnoreCase(n + "")) {
            JuegoForm.btn35.setBackground(color);
        }
        if (JuegoForm.btn36.getText().equalsIgnoreCase(n + "")) {
           JuegoForm.btn36.setBackground(color);
        }
        if (JuegoForm.btn37.getText().equalsIgnoreCase(n + "")) {
            JuegoForm.btn37.setBackground(color);
        }
        if (JuegoForm.btn38.getText().equalsIgnoreCase(n + "")) {
            JuegoForm.btn38.setBackground(color);
        }
        if (JuegoForm.btn39.getText().equalsIgnoreCase(n + "")) {
            JuegoForm.btn39.setBackground(color);
        }
        if (JuegoForm.btn40.getText().equalsIgnoreCase(n + "")) {
            JuegoForm.btn40.setBackground(color);
        }
        if (JuegoForm.btn41.getText().equalsIgnoreCase(n + "")) {
            JuegoForm.btn41.setBackground(color);
        }
        if (JuegoForm.btn42.getText().equalsIgnoreCase(n + "")) {
            JuegoForm.btn42.setBackground(color);
        }
        if (JuegoForm.btn43.getText().equalsIgnoreCase(n + "")) {
            JuegoForm.btn43.setBackground(color);
        }
        if (JuegoForm.btn44.getText().equalsIgnoreCase(n + "")) {
            JuegoForm.btn44.setBackground(color);
        }
        if (JuegoForm.btn45.getText().equalsIgnoreCase(n + "")) {
            JuegoForm.btn45.setBackground(color);
        }
        if (JuegoForm.btn46.getText().equalsIgnoreCase(n + "")) {
            JuegoForm.btn46.setBackground(color);
        }
        if (JuegoForm.btn47.getText().equalsIgnoreCase(n + "")) {
            JuegoForm.btn47.setBackground(color);
        }
        if (JuegoForm.btn48.getText().equalsIgnoreCase(n + "")) {
            JuegoForm.btn48.setBackground(color);
        }
        if (JuegoForm.btn49.getText().equalsIgnoreCase(n + "")) {
            JuegoForm.btn49.setBackground(color);
        }
        if (JuegoForm.btn50.getText().equalsIgnoreCase(n + "")) {
            JuegoForm.btn50.setBackground(color);
        }
        
    }
}
