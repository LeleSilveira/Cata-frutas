package jogo;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

//classe de fruta do tipo abacate
public class Abacaxi extends Fruta {

    private static int totalDeAbacaxi = 3;  //fruta ouro

    public Abacaxi() {

        this.setIcone(new ImageIcon(getClass().getResource("/Frutas/AbacaxiG.png")));
    }

    public static void setTotalDeOuro(int totalDeOuro) {
        Abacaxi.totalDeAbacaxi = totalDeOuro;
    }

    public static int getTotalDeOuro() {
        return Abacaxi.totalDeAbacaxi;
    }

    @Override
    public String toString() {
        return "Fruta Abacaxi";
    }

    //método que recebe um avatar para mexer nas suas propriedades
    @Override
    public void consumir(Avatar avatar) { //assim pra morango tbm
        JOptionPane.showMessageDialog(Jogo.getJanelaJogo(), "Fruta Ouro não pode ser consumida ", "Aviso", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public ImageIcon getFrutaIcon() {
        return new ImageIcon(getClass().getResource("/Frutas/Abacaxi.png"));
    }

    //método que contém a string com a propriedade da fruta
    @Override
    public String stringProp() {
        return "Ouro";
    }

}
