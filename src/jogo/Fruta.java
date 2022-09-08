package jogo;

import javax.swing.ImageIcon;

//classe geral do tipo Fruta que contém atributos totais de quantidade e particulares de se foi recolhida
//ou se a fruta é bichada, junto com seus gets e sets
public class Fruta extends Elementos {

    private boolean ehBichada;
    private static int totalDeFrutas = (Fruta.totalDeFrutasNaoOuro) + (Abacaxi.getTotalDeOuro());
    private static int totalDeFrutasBichadas = (Math.round((Fruta.totalDeFrutas * 30) / 100.0f));
    private static int totalDeFrutasNaoOuro = (Math.round((Grama.getQuantidade() * 20) / 100.0f));
    private boolean foiRecolhida = false;

    public Fruta() {
    }

    public static int getTotalDeFrutas() {
        return totalDeFrutas;
    }

    public static int getTotalDeFrutasNaoOuro() {
        return totalDeFrutasNaoOuro;
    }

    public void setFoiRecolhida(boolean estado) {
        this.foiRecolhida = estado;
    }

    public boolean getFoiRecolhida() {
        return this.foiRecolhida;
    }

    public void consumir(Avatar avatar) {

    }

    public String stringProp() {
        return " ";
    }

    public ImageIcon getFrutaIcon() {
        return (new ImageIcon(getClass().getResource("/Frutas/Morango.png")));

    }

    public static void setTotalDeFrutasBichadas(int porcentagem) {
        Fruta.totalDeFrutasBichadas = Math.round((porcentagem / 100.0f) * Fruta.getTotalDeFrutas());
    }

    public static void setTotalDeFrutasNaoOuro(int porcentagem) {//porcentagem em relação ao n° de grama
        Fruta.totalDeFrutasNaoOuro = (Math.round((Grama.getQuantidade() * porcentagem) / 100.0f));
    }

    public static int getTotalDeFrutasBichadas() {
        return Fruta.totalDeFrutasBichadas;
    }

    public void setBichada() {
        this.ehBichada = true;
    }

    public boolean ehBichada() {
        return this.ehBichada;
    }

}
