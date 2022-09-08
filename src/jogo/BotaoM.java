package jogo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;

//classe de botões  onde cada um possui uma posição
public class BotaoM extends JButton {

    private int[] posicao;

    public BotaoM(int x, int y) {
        int[] pos = new int[2];
        pos[0] = x;
        pos[1] = y;
        this.posicao = pos;
        this.addActionListener(new MudaFigura());
    }
    //método que verifica quem está jogando e chama a função de mover e atualiza as imagens
    public class MudaFigura implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {  //tirar while de avatar e botar if aqui
            if (Jogo.getUltimoAJogar() == 1) {
                try {
                    botoesMover(Jogo.getAvatar1(), (BotaoM) event.getSource());
                } catch (IOException ex) {
                    Logger.getLogger(BotaoM.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedAudioFileException ex) {
                    Logger.getLogger(BotaoM.class.getName()).log(Level.SEVERE, null, ex);
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(BotaoM.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (Jogo.getUltimoAJogar() == 2) {
                try {
                    botoesMover(Jogo.getAvatar2(), (BotaoM) event.getSource());
                } catch (IOException ex) {
                    Logger.getLogger(BotaoM.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedAudioFileException ex) {
                    Logger.getLogger(BotaoM.class.getName()).log(Level.SEVERE, null, ex);
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(BotaoM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //método que implemnta as ações ao clicar no botao
        private void botoesMover(Avatar avatar, BotaoM b) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
            InputStream f = getClass().getResourceAsStream("/Musica/jump.wav");
            InputStream bufferedIn = new BufferedInputStream(f);
            AudioInputStream audio = AudioSystem.getAudioInputStream(bufferedIn);
            Clip clip = AudioSystem.getClip();
            int[] pos = avatar.getPosicao();
            int[] botao = posicao;
            int moveu = 0;
            if ((pos[0] == botao[0]) && (pos[1]) == (botao[1] - 1)) {  //botao a direita do avatar 
                try {
                    moveu = avatar.mover('d');
                } catch (Exception ex) {
                    Logger.getLogger(BotaoM.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (moveu == 1) { //caso de ter pedra
                    clip.open(audio);
                    clip.start();
                    if (Floresta.elementoNaPosicao(botao[0], botao[1]) instanceof Pedra) {
                        Jogo.janelaSetarElementos(Jogo.janelaGetBotaoM(pos[0], (pos[1] - 2)), pos[0], (pos[1] - 2));
                    }
                    Jogo.janelaSetarElementos(Jogo.janelaGetBotaoM(pos[0], (pos[1] - 1)), pos[0], (pos[1] - 1));
                    Jogo.janelaSetarAvatar(Jogo.janelaGetBotaoM(pos[0], pos[1]), avatar.ehAvatar1());
                }
            } else if ((pos[0] == botao[0]) && (pos[1]) == (botao[1] + 1)) {  //botao a esquerda do avatar
                try {
                    moveu = avatar.mover('a');
                } catch (Exception ex) {
                    Logger.getLogger(BotaoM.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (moveu == 1) {
                    clip.open(audio);
                    clip.start();
                    if (Floresta.elementoNaPosicao(botao[0], botao[1]) instanceof Pedra) {
                        Jogo.janelaSetarElementos(Jogo.janelaGetBotaoM(pos[0], (pos[1] + 2)), pos[0], (pos[1] + 2));
                    }
                    Jogo.janelaSetarElementos(Jogo.janelaGetBotaoM(pos[0], (pos[1] + 1)), pos[0], (pos[1] + 1));
                    Jogo.janelaSetarAvatar(Jogo.janelaGetBotaoM(pos[0], pos[1]), avatar.ehAvatar1());
                }
            } else if ((pos[1] == botao[1]) && (pos[0]) == (botao[0] - 1)) {  //botao embaixo do avatar 
                try {
                    moveu = avatar.mover('s');
                } catch (Exception ex) {
                    Logger.getLogger(BotaoM.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (moveu == 1) {
                    clip.open(audio);
                    clip.start();
                    if (Floresta.elementoNaPosicao(botao[0], botao[1]) instanceof Pedra) {
                        Jogo.janelaSetarElementos(Jogo.janelaGetBotaoM((pos[0] - 2), pos[1]), (pos[0] - 2), pos[1]);
                    }
                    Jogo.janelaSetarElementos(Jogo.janelaGetBotaoM((pos[0] - 1), pos[1]), (pos[0] - 1), pos[1]);
                    Jogo.janelaSetarAvatar(Jogo.janelaGetBotaoM(pos[0], pos[1]), avatar.ehAvatar1());
                }
            } else if ((pos[1] == botao[1]) && (pos[0]) == (botao[0] + 1)) {  //botao acima do avatar
                try {
                    moveu = avatar.mover('w');
                } catch (Exception ex) {
                    Logger.getLogger(BotaoM.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (moveu == 1) {
                    clip.open(audio);
                    clip.start();
                    if (Floresta.elementoNaPosicao(botao[0], botao[1]) instanceof Pedra) {
                        Jogo.janelaSetarElementos(Jogo.janelaGetBotaoM((pos[0] + 2), pos[1]), (pos[0] + 2), pos[1]);
                    }
                    Jogo.janelaSetarElementos(Jogo.janelaGetBotaoM((pos[0] + 1), pos[1]), (pos[0] + 1), pos[1]);
                    Jogo.janelaSetarAvatar(Jogo.janelaGetBotaoM(pos[0], pos[1]), avatar.ehAvatar1());
                }
            }   
        }

    }

}
