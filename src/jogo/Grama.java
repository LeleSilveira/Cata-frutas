package jogo;

import javax.swing.ImageIcon;

//classe do tipo grama com atributo de quantidade e seus gets e sets
public class Grama extends Elementos {

    private static int quantidade = Math.round((Floresta.getDimensaoFloresta() * Floresta.getDimensaoFloresta() * 50) / 100.0f);

    public Grama() {

        this.setIcone(new ImageIcon(getClass().getResource("/Cenario/grama.png")));
    }

    public static int getQuantidade() {
        return quantidade;
    }

    public static void setQuantidade(int quantidade) {
        Grama.quantidade = (int) Math.round((Math.pow(Floresta.getDimensaoFloresta(), 2) * quantidade) / 100.0f);//editado
    }
    //método que seta a quantidade em numero 
    public static void setQuantidadeN(int quantidade) {
        Grama.quantidade = quantidade;//editado
    }
    
    //define a string de grama para saida no console, verficando se tem alguma fruta nela
    @Override
    public String toString() {
        int[] pos = this.getPosicao();
        if (MatrizFrutas.elementoNaPosicao(pos[0], pos[1]) != null) {
            if (MatrizFrutas.elementoNaPosicao(pos[0], pos[1]).getFoiRecolhida() == false) {
                return "Grama+" + MatrizFrutas.elementoNaPosicao(pos[0], pos[1]).toString();
            }
        }

        return "Grama";

    }
}
