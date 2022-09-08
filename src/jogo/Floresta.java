package jogo;

import java.util.Random;

//classe que define a matriz da floresta 
public class Floresta {

    private static Elementos tabuleiro[][];
    private static int dimensaoFloresta=3;
    
    //instancia a matriz e coloca a quantidade devida de cada elemento nela
    public Floresta() {
        this.tabuleiro = new Elementos[this.dimensaoFloresta][this.dimensaoFloresta];
        int quantPedras = Pedra.getQuantidade();
        int quantGrama = Grama.getQuantidade();
        int quantArv = Arvore.getQuantidade();
        int x;
        int y;
        int i = 0;

        while (i < quantPedras) {
            Pedra pedra = new Pedra();
            Random aleatorio = new Random();
            x = aleatorio.nextInt(this.dimensaoFloresta);
            y = aleatorio.nextInt(this.dimensaoFloresta);
            if (posicaoOcupada(tabuleiro, x, y) != 1) {
                tabuleiro[x][y] = pedra;
                pedra.setPosicao(x, y);
                i++;
            }
        }
        i = 0;

        while (i < quantArv) {
            Arvore arvore = new Arvore();
            Random aleatorio = new Random();
            x = aleatorio.nextInt(this.dimensaoFloresta);
            y = aleatorio.nextInt(this.dimensaoFloresta);
            if (posicaoOcupada(tabuleiro, x, y) != 1) {
                tabuleiro[x][y] = arvore;
                arvore.setPosicao(x, y);
                i++;
            }
        }
        i = 0;

       
        while (i < quantGrama) {
            Grama grama = new Grama();
            Random aleatorio = new Random();
            x = aleatorio.nextInt(this.dimensaoFloresta);
            y = aleatorio.nextInt(this.dimensaoFloresta);
            if (posicaoOcupada(tabuleiro, x, y) != 1) {
                tabuleiro[x][y] = grama;
                grama.setPosicao(x, y);
                i++;
            }
        }
        i = 0;
      
    }

    //método que verifica se a posição da matriz já está ocupada por outro elemento
    private int posicaoOcupada(Elementos tabuleiro[][], int x, int y) {
        if (tabuleiro[x][y] != null) {
            return 1;
        } else {
            return 0;
        }
    }

    public static Elementos elementoNaPosicao(int x, int y) {
        return Floresta.tabuleiro[x][y];
    }

    public static Elementos[][] getTabuleiro() {
        return tabuleiro;
    }

    public static int getDimensaoFloresta() {
        return dimensaoFloresta;
    }
    public static void setDimensaoFloresta(int dimensao){
        Floresta.dimensaoFloresta=dimensao;
    }
    //método que imprime a matriz no console
    public static void imprime() {
        for (int i = 0; i < Floresta.dimensaoFloresta; i++) {
            for (int j = 0; j < Floresta.dimensaoFloresta; j++) {
                System.out.print(Floresta.elementoNaPosicao(i, j).toString() + "  ");
            }
            System.out.print("\n");
        }
    }
}
