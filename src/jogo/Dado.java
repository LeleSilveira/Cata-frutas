package jogo;

//classe que representa os dados utilizados no jogo e gera valores aleatórios entre 1 e 6
public class Dado {

    private int valor;

    public Dado() {
        this.valor = 1 + (int) (Math.random() * ((6 - 1) + 1));
    }

    public int getValor() {
        return valor;
    }
}
