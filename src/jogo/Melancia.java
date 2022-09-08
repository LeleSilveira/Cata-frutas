package jogo;

import javax.swing.ImageIcon;

//classe de fruta do tipo melancia
public class Melancia extends Fruta {
    private static int totalDeMelancia=(Math.round((int) (Fruta.getTotalDeFrutasNaoOuro() * 5) / 100.0f));

    public Melancia(){
        this.setIcone(new ImageIcon(getClass().getResource("/Frutas/MelanciaG.png")));
    }

    public static void setTotalDeMelancia(int porcentagem){
        Melancia.totalDeMelancia= (Math.round((int) (Fruta.getTotalDeFrutasNaoOuro() * porcentagem) / 100.0f));
    }

    public static int getTotalDeMelancia(){
        return totalDeMelancia;
    }

    @Override
    public String toString() {
        return "Fruta Melancia";
    }
    //método que recebe um avatar para mexer nas suas propriedades
    @Override
    public void consumir (Avatar avatar){
        this.dobraPontos(avatar);
    }
    //método que executa a propriedade de melancia
    public void dobraPontos(Avatar avatar){
        avatar.dobraPontos();
    }
    //método que contém a string com a propriedade da fruta
    public String stringProp() {
      return "Agilidade";
    }
    @Override
    public ImageIcon getFrutaIcon() {
     return new ImageIcon(getClass().getResource("/Frutas/Melancia.png"));
    }

}
