package jogo;

import javax.swing.ImageIcon;

//classe abstrata que define um elemento com posição e ícone com seus gets e sets
public abstract class Elementos {

    private int[] posicao;
    private ImageIcon icone;

    public Elementos() {
        posicao = new int[2];
    }

    public int[] getPosicao() {
        return posicao;
    }

    public void setPosicao(int x, int y) {
        this.posicao[0] = x;
        this.posicao[1] = y;
    }

    public ImageIcon getIcone() {
        return this.icone;
    }

    public void setIcone(ImageIcon icone) {
        this.icone = icone;
    }

}
