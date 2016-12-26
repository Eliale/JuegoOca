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

public class Estado{
    private final int ARRIBA=0, DERECHA=1, IZQUIERDA=2, ABAJO=3;
    private int[][] estado;
    private int i, j;

    public Estado(){
        this.estado = new int[][]{
            new int[]{1, 2, 3},
            new int[]{4, 5, 6},
            new int[]{7, 8, 0}
        };
        i = 2; j = 2;
    }

    public int[][] getValores(){
        return estado;
    }

    public void setValores(int[][] estado, int i, int j){
        this.estado = estado;
        this.i = i;
        this.j = j;
    }

    public Estado Mover(int direccion){
        int ni = i, nj = j;

        switch(direccion){
            case ARRIBA: ni--; break;
            case DERECHA:  nj++; break;
            case IZQUIERDA:  nj--; break;
            case ABAJO: ni++; break;  
        }

        if(ni < this.estado[0].length && ni >= 0){
            if(nj < this.estado.length && nj >= 0){
                Estado nuevo_estado = new Estado();
                int[][] valores = new int[this.estado[0].length][this.estado.length];
                for(int x = 0; x < this.estado[0].length; x++){
                    for(int y = 0; y < this.estado.length; y++){
                        valores[x][y] = this.estado[x][y];
                    }
                }
                int aux = valores[i][j];
                valores[i][j] = valores[ni][nj];
                valores[ni][nj] = aux;
                nuevo_estado.setValores(valores, ni, nj);
                return nuevo_estado;
            }
        }
        return null;
    }

    public boolean EncontradoIgual(Estado estado){
        for(int x = 0; x < this.estado[0].length; x++){
            for(int y = 0; y < this.estado.length; y++){
                if(this.estado[x][y] != estado.getValores()[x][y]){
                    return false; 
                }
            }
        }
        return true;
    }

    public void imprimir(Estado estado){
        for(int[] fila: estado.getValores()){
            for(int valor: fila){
                System.out.print(valor);
            }
            System.out.println();
        }
        System.out.println("___");
    } 

    public void imprimir(){
        this.imprimir(this);
    }

}
