package jogo;

import java.util.Random;

//classe que define uma matriz de frutas para posicioná-las no jogo
public class MatrizFrutas {

    private static Fruta matrizFrutas[][]; //atributo geral da classe
    private static int frutasSetadas = 0;
    
    //instancia a matrzi de frutas e todas as frutas de acordo com sua quantidade correspondente
    public MatrizFrutas() {
        matrizFrutas = new Fruta[Floresta.getDimensaoFloresta()][Floresta.getDimensaoFloresta()];
        int quantOuro = Abacaxi.getTotalDeOuro();
        int quantMelancia = Melancia.getTotalDeMelancia();
        int quantAbacate = Abacate.getTotalDeAbacate();
        int quantLaranja = Laranja.getTotalDeLaranja();
        //int quantMorango= Morango.getTotalDeMorango();
        int x;
        int y;
        int i = 0;

        while (i < quantOuro) {
            Abacaxi ouro = new Abacaxi();
            Random aleatorio = new Random();
            x = aleatorio.nextInt(Floresta.getDimensaoFloresta());
            y = aleatorio.nextInt(Floresta.getDimensaoFloresta());
            if (podeOcupar(matrizFrutas, x, y) == 1) {
                adcFruta(ouro, x, y);
                i++;
            }
        }
        i = 0;
        while (i < quantMelancia) {
            Melancia melancia = new Melancia();
            Random aleatorio = new Random();
            x = aleatorio.nextInt(Floresta.getDimensaoFloresta());
            y = aleatorio.nextInt(Floresta.getDimensaoFloresta());
            if (podeOcupar(matrizFrutas, x, y) == 1) {
                adcFruta(melancia, x, y);
                frutasSetadas++;
                i++;
            }
        }
        i = 0;
        while (i < quantAbacate) {
            Abacate abacate = new Abacate();
            Random aleatorio = new Random();
            x = aleatorio.nextInt(Floresta.getDimensaoFloresta());
            y = aleatorio.nextInt(Floresta.getDimensaoFloresta());
            if (podeOcupar(matrizFrutas, x, y) == 1) {
                adcFruta(abacate, x, y);
                frutasSetadas++;
                i++;
            }
        }
        i = 0;
        while (i < quantLaranja) {
            Laranja laranja = new Laranja();
            Random aleatorio = new Random();
            x = aleatorio.nextInt(Floresta.getDimensaoFloresta());
            y = aleatorio.nextInt(Floresta.getDimensaoFloresta());
            if (podeOcupar(matrizFrutas, x, y) == 1) {
                adcFruta(laranja, x, y);
                frutasSetadas++;
                i++;
            }
        }
        i = 0;
        int quantMorango = Fruta.getTotalDeFrutasNaoOuro() - frutasSetadas;
        while (i < quantMorango) {
            Morango morango = new Morango();
            Random aleatorio = new Random();
            x = aleatorio.nextInt(Floresta.getDimensaoFloresta());
            y = aleatorio.nextInt(Floresta.getDimensaoFloresta());
            if (podeOcupar(matrizFrutas, x, y) == 1) {
                adcFruta(morango, x, y);
                frutasSetadas++;
                i++;
            }
        }
        i = 0;
        int total = Fruta.getTotalDeFrutasNaoOuro() + Abacaxi.getTotalDeOuro();
        int totalBichada = (Math.round((total * 30) / 100.0f));
        while (i < totalBichada) {
            Random aleatorio = new Random();
            x = aleatorio.nextInt(Floresta.getDimensaoFloresta());
            y = aleatorio.nextInt(Floresta.getDimensaoFloresta());
            if (matrizFrutas[x][y] != null) {
                matrizFrutas[x][y].setBichada();
                i++;
            }
        }

    }
    //verifica se pode ocupar aquela posição na matriz da floresta
    private static int podeOcupar(Fruta matriz[][], int x, int y) {
        if (x >= 0 && y >= 0 && x < Floresta.getDimensaoFloresta() && y < Floresta.getDimensaoFloresta()) {
            if ((matriz[x][y] == null) && ((Floresta.elementoNaPosicao(x, y) instanceof Grama) || (Floresta.elementoNaPosicao(x, y) instanceof Arvore))) {
                return 1;
            } else {
                return 0;
            }
        }
        return 0;
    }

    public static Fruta elementoNaPosicao(int x, int y) {
        return MatrizFrutas.matrizFrutas[x][y];
    }
    //método que procura uma posição livre e adiciona uma fruta caida da mochila e seta sua imagem
    public static void adcionarFrutaCaida(int x, int y, Fruta fruta) { //recebe a posição do avatar empurrado
        if (podeOcupar(matrizFrutas, x - 1, y) == 1) {
            matrizFrutas[x - 1][y] = fruta;
            fruta.setPosicao(x - 1, y);
        } else if (podeOcupar(matrizFrutas, x, y - 1) == 1) {
            matrizFrutas[x][y - 1] = fruta;
            fruta.setPosicao(x, y - 1);
        } else if (podeOcupar(matrizFrutas, x + 1, y) == 1) {
            matrizFrutas[x + 1][y] = fruta;
            fruta.setPosicao(x + 1, y);
        } else if (podeOcupar(matrizFrutas, x, y + 1) == 1) {
            matrizFrutas[x][y + 1] = fruta;
            fruta.setPosicao(x, y + 1);
        } else if (podeOcupar(matrizFrutas, x, y + 2) == 1) {
            matrizFrutas[x][y + 2] = fruta;
            fruta.setPosicao(x, y + 2);
        } else if (podeOcupar(matrizFrutas, x - 2, y) == 1) {
            matrizFrutas[x - 2][y] = fruta;
            fruta.setPosicao(x - 2, y);
        }
        int[] pos = fruta.getPosicao();
        int[] pos1 = Jogo.getAvatar1().getPosicao();
        int[] pos2 = Jogo.getAvatar2().getPosicao();
        boolean ok = false;
        System.out.println("Pos que " + fruta + " caiu: " + pos[0] + pos[1]);
        if ((pos[0] == pos1[0]) && (pos[1] == pos1[1])) { //se cair em avatar1
            Jogo.janelaSetarAvatar(Jogo.janelaGetBotaoM(pos[0], pos[1]), true);
            ok = true;
        } else if ((pos[0] == pos2[0]) && (pos[1] == pos2[1])) { //se cair em avatar2
            Jogo.janelaSetarAvatar(Jogo.janelaGetBotaoM(pos[0], pos[1]), false);
            ok = true;
        } else {
            Jogo.janelaSetarElementos(Jogo.janelaGetBotaoM(pos[0], pos[1]), pos[0], pos[1]); //seta frutas na grama
        }
    }
    //método que adiciona uma fruta na matriz
    public static void adcFruta(Fruta fruta, int x, int y) {
        matrizFrutas[x][y] = fruta; 
        fruta.setPosicao(x, y);
    }

}
