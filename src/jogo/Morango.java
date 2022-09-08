package jogo;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

//classe de fruta do tipo morango
public class Morango extends Fruta {

    private static int totalDeMorango=(Math.round((int) (Fruta.getTotalDeFrutasNaoOuro() * 75) / 100.0f));

    public Morango(){
        this.setIcone(new ImageIcon(getClass().getResource("/Frutas/MorangoG.png")));
    }

    public static void setTotalDeMorango(int porcentagem){
        Morango.totalDeMorango =  (Math.round((int) (Fruta.getTotalDeFrutasNaoOuro() * porcentagem) / 100.0f));
    }
    public static int getTotalDeMorango(){
        return totalDeMorango;
    }
    public static Fruta getMorango(){
        Morango morango= new Morango();
        return morango;
    }

    @Override
    public String toString() {
        return "Fruta Morango";
    }
    //método que recebe um avatar para mexer nas suas propriedades
    @Override
    public void consumir (Avatar avatar){
 JOptionPane.showMessageDialog(Jogo.getJanelaJogo(),"Essa fruta será consumida, mas não tem propriedades ", "Aviso", JOptionPane.INFORMATION_MESSAGE);            
       
    }


}
