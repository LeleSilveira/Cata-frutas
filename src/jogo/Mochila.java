package jogo;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

//classe que define a mochila do avatar
public class Mochila {

    private int quantidade;
    private static int capacidade = 3;
    private Fruta[] frutas;
    private JButton[] botoesFrutas;
    private int indice = 0;
    private JFrame lista;

    public Mochila() {
        frutas = new Fruta[capacidade];
        botoesFrutas = new JButton[capacidade];
    }

    //método que adiciona uma fruta na mochila
    public void adicionarFruta(Fruta fruta) {
        for (int i = 0; i < Mochila.capacidade; i++) {
            if (frutas[i] == null) {
                frutas[i] = fruta;
                JButton b = new JButton(fruta.stringProp());
                b.setIcon(fruta.getFrutaIcon());
                b.addActionListener(new ListaListener());
                botoesFrutas[i] = b;
                break;
            }
        }
    }

    //método que retira fruta da mochila quando o avatar for empurrado
    public Fruta retirarFruta() {
        int i = 0;
        while (frutas[i] != null) {
            i++;
        }
        Fruta fruta = frutas[i - 1];
        frutas[i - 1] = null;
        JButton b = botoesFrutas[i - 1];
        botoesFrutas[i - 1] = null;
        lista = new JFrame("Mochila");
        lista.setVisible(false);
        lista.remove(b);

        return fruta;
    }

    //método que retira a fruta da mochla quando ela for clicada
    public Fruta retirarFrutaNaPos(int i) {
        Fruta fruta = frutas[i];
        frutas[i] = null;
        JButton b = botoesFrutas[i];
        botoesFrutas[i] = null;
        lista.remove(b);
        if (Jogo.getUltimoAJogar() == 1) {
            Jogo.getAvatar1().setForça(Jogo.getAvatar1().getForça() - 1);
        } else if (Jogo.getUltimoAJogar() == 2) {
            Jogo.getAvatar2().setForça(Jogo.getAvatar2().getForça() - 1);
        }
        lista.dispose();
        return fruta;
    }

    //método que cria uma  frame para imprimir a mochila na tela
    public void imprimir() {
        lista = new JFrame("Mochila");
        lista.setLayout(new GridLayout(1, Mochila.capacidade));
        lista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        lista.setSize(500, 120); //largura pode depender do capacidade da mochila
        lista.setVisible(true);
        lista.setFocusable(true);
        lista.setLocationRelativeTo(null);
        lista.setResizable(false);
        Image icon = (new ImageIcon(getClass().getResource("/Frutas/Abacaxi.png"))).getImage();
        lista.setIconImage(icon);
        int i = 0;
        for (i = 0; i < Mochila.capacidade; i++) {
            if (frutas[i] != null) {
                System.out.println(i + 1 + "-" + frutas[i].toString());
                lista.add(botoesFrutas[i]);
            }
        }

    }

    public int getCapacidade() {
        return Mochila.capacidade;
    }

    public static void setCapacidade(int c) {
        Mochila.capacidade = c;
    }

    public Fruta[] getFrutas() {
        return this.frutas;
    }

    public boolean estaCheia() {
        for (int i = 0; i < Mochila.capacidade; i++) {
            if (frutas[i] == null) {
                return false;
            }
        }
        return true;
    }

    //método que implementa a ação de consumir a fruta na mochila e retirá-la dela
    public class ListaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            for (int i = 0; i < Mochila.capacidade; i++) {
                if (botoesFrutas[i] == event.getSource()) {
                    if (Jogo.getUltimoAJogar() == 1) {
                        frutas[i].consumir(Jogo.getAvatar1()); //dps apagar a fruta
                        if ((frutas[i] instanceof Abacaxi) == false) {
                            retirarFrutaNaPos(i);
                        }
                    } else if (Jogo.getUltimoAJogar() == 2) {
                        frutas[i].consumir(Jogo.getAvatar2());
                        if ((frutas[i] instanceof Abacaxi) == false) {
                            retirarFrutaNaPos(i);
                        }
                    }

                }
            }

        }
    }

}
