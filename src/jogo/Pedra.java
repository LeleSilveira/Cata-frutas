package jogo;

import javax.swing.ImageIcon;

//classe do tipo pedra que possui o atributo static de quantidade com seus gets e sets
public class Pedra extends Elementos {

    private static int quantidade = (Math.round((int) (Math.pow(Floresta.getDimensaoFloresta(), 2) * 25) / 100.0f));

    public Pedra() {
        this.setIcone(new ImageIcon(getClass().getResource("/Cenario/pedraG.png")));
    }

    public static int getQuantidade() {
        return quantidade;
    }

    public static void setQuantidade(int quantidade) {
        Pedra.quantidade = (int) Math.round((Math.pow(Floresta.getDimensaoFloresta(), 2) *quantidade) / 100.0f);//editado
        int quantGrama=(int) (Math.pow(Floresta.getDimensaoFloresta(),2)-Pedra.getQuantidade())-Arvore.getQuantidade();
        Grama.setQuantidadeN(quantGrama);
    }

    @Override
    public String toString() {
        return "Pedra";
    }
}
