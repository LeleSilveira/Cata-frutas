package jogo;

import javax.swing.ImageIcon;
//classe de fruta do tipo abacate
public class Abacate extends Fruta {

    private static int totalDeAbacate = (Math.round((int) (Fruta.getTotalDeFrutasNaoOuro() * 5) / 100.0f));

    public Abacate() {
        this.setIcone(new ImageIcon(getClass().getResource("/Frutas/AbacateG.png")));
    }

    public static void setTotalDeAbacate(int porcentagem) {
        Abacate.totalDeAbacate = (Math.round((int) (Fruta.getTotalDeFrutasNaoOuro() * porcentagem) / 100.0f));
    }

    public static int getTotalDeAbacate() {
        return totalDeAbacate;
    }

    @Override
    public String toString() {
        return "Fruta Abacate";
    }
    //método que recebe um avatar para mexer nas suas propriedades
    @Override
    public void consumir(Avatar avatar) {
        this.dobraForca(avatar);
    }
    //método que executa a propriedade de abacate
    public void dobraForca(Avatar avatar) {
        avatar.dobraForca();
    }
    //método que contém a string com a propriedade da fruta
    @Override
    public String stringProp() {
        return "Força";
    }

    @Override
    public ImageIcon getFrutaIcon() {
        return new ImageIcon(getClass().getResource("/Frutas/Abacate.png"));
    }

}
