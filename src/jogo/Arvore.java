package jogo;

import java.util.Scanner;
import javax.swing.ImageIcon;

//classe que define o tipo arvore com atributos estáticos de tipo de fruta e quantidade de árvores
public class Arvore extends Elementos {

    private static Fruta tipoFruta = Morango.getMorango();
    private static int quantidade = (Math.round((Floresta.getDimensaoFloresta() * Floresta.getDimensaoFloresta() * 25) / 100));

    public Arvore() {
        this.setIcone(new ImageIcon(getClass().getResource("/Cenario/arvoreG.png")));
    }
    //método chamado quando o avatar está debaixo da árvore e uma fruta deve cair
    public void cairFrutas() {
        int[] pos = this.getPosicao();
        MatrizFrutas.adcFruta(tipoFruta, pos[0], pos[1]);
        if (Jogo.getUltimoAJogar() == 1) {
            Jogo.janelaSetarAvatar(Jogo.janelaGetBotaoM(pos[0], pos[1]), true);
        } else if (Jogo.getUltimoAJogar() == 2) {
            Jogo.janelaSetarAvatar(Jogo.janelaGetBotaoM(pos[0], pos[1]), false);
        }

    }

    public String nomeDaFruta() {
        return Arvore.tipoFruta.toString();
    }

    public static int getQuantidade() {
        return quantidade;
    }

    public static void setQuantidade(int quantidade) {
        Arvore.quantidade = (int) Math.round((Math.pow(Floresta.getDimensaoFloresta(), 2) * quantidade) / 100.0f);//editado
        int quantGrama = (int) (Math.pow(Floresta.getDimensaoFloresta(), 2) - Pedra.getQuantidade()) - Arvore.getQuantidade();
        Grama.setQuantidadeN(quantGrama);
    }

    public Fruta getTipoFruta() {
        return this.tipoFruta;
    }
  //método que recebe um int e seta o tipo de arvore
    public static void setTipoArvore(int tipoLista) {
        if (tipoLista == 1) {
            Abacaxi frutaOuro = new Abacaxi();
            Arvore.tipoFruta = frutaOuro;
        }
        if (tipoLista == 2) {
            Melancia melancia = new Melancia();
            Arvore.tipoFruta = melancia;
        }
        if (tipoLista == 3) {
            Abacate abacate = new Abacate();
            Arvore.tipoFruta = abacate;
        }
        if (tipoLista == 4) {
            Laranja laranja = new Laranja();
            Arvore.tipoFruta = laranja;
        }
        if (tipoLista == 5) {
            Morango morango = new Morango();
            Arvore.tipoFruta = morango;
        }
    }

    @Override
    public String toString() {
        return "Arvore de " + this.nomeDaFruta();
    }

}
