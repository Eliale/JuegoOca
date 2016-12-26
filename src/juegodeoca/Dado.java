/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegodeoca;

/**
 *
 * @author Eli
 */
public class Dado {

    private String tematicas[];

    public Dado(String[] tematicas) {
        if (tematicas.length == 6) {
            this.tematicas = tematicas;
        }
    }

    public String[] tira_dado() {
        String array[] = new String[2];
        int tiro = (int) (Math.random() * 6 + 1);
        array[0] = "" + tiro;
        array[1] = getTematicas()[tiro - 1];
        return array;
    }

    public String[] getTematicas() {
        return tematicas;
    }

    public void setTematicas(String[] tematicas) {
        this.tematicas = tematicas;
    }

}
