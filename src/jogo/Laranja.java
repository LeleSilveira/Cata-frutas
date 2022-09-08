package jogo;

import javax.swing.ImageIcon;

//classe de fruta do tipo laranja
public class Laranja extends Fruta {

    private static int totalDeLaranja = (Math.round((int) (Fruta.getTotalDeFrutasNaoOuro() * 15) / 100.0f));

    public Laranja() {
        this.setIcone(new ImageIcon(getClass().getResource("/Frutas/LaranjaG.png")));
    }

    public static void setTotalDeLaranja(int porcentagem) {
        Laranja.totalDeLaranja = (Math.round((int) (Fruta.getTotalDeFrutasNaoOuro() * porcentagem) / 100.0f));
    }

    public static int getTotalDeLaranja() {
        return totalDeLaranja;
    }

    @Override
    public String toString() {
        return "Fruta Laranja";
    }
    //método que recebe um avatar para mexer nas suas propriedades
    @Override
    public void consumir(Avatar avatar) {
        this.anulaBichada(avatar);

        Jogo.JanelaSetCorStatus1();
        Jogo.JanelaSetCorStatus2();

        avatar.jogarDado();
    }
    //método que executa a propriedade de laranja
    public void anulaBichada(Avatar avatar) {
        avatar.anulaBichada();
    }
    //método que contém a string com a propriedade da fruta
    @Override
    public String stringProp() {
        return "Antídoto";
    }

    @Override
    public ImageIcon getFrutaIcon() {
        return new ImageIcon(getClass().getResource("/Frutas/Laranja.png"));
    }

}
