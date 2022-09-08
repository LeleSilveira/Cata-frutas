package jogo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//classe que representa a janela principal do jogo
public class JanelaJogo {

    private JFrame frame;
    private JPanel painel;
    private JPanel painelStatus;
    private JPanel painelPontosV;
    private JPanel painelMatriz;
    private JPanel painelBotoes;
    private JLabel status1;
    private JLabel status2;
    private JLabel rodadas;
    private JLabel pontosV1;
    private JLabel pontosV2;
    private JButton botaoDados;
    private JButton acabarMovimento;
    private JButton usarFruta;
    private JButton pegarFruta;
    private GridBagConstraints gbc = new GridBagConstraints();
    private BotaoM botoesM[][] = new BotaoM[Floresta.getDimensaoFloresta()][Floresta.getDimensaoFloresta()];

    //cria a janela com todos seus elementos
    JanelaJogo() {
        frame = new JFrame();
        frame.setTitle("Cata-Frutas");
        frame.setLayout(new GridBagLayout());
        gbc.insets = new Insets(6, 8, 6, 8);

        painel = new JPanel();
        painel.setLayout(new BorderLayout());
        painel.setBorder(new EmptyBorder(0, 20, 0, 20)); //cima,esq,baixo,dir

        painelStatus = new JPanel();
        painelStatus.setLayout(new BorderLayout(130, 0));

        status1 = new JLabel("Jogador 1: 0 pontos");
        status1.setFont(new Font("Verdana", Font.PLAIN, 18));
        painelStatus.add(status1, BorderLayout.WEST);

        rodadas = new JLabel("Rodadas: 0");
        rodadas.setFont(new Font("Verdana", Font.PLAIN, 18));
        painelStatus.add(rodadas, BorderLayout.CENTER);

        status2 = new JLabel("Jogador 2: 0 pontos");
        status2.setFont(new Font("Verdana", Font.PLAIN, 18));
        painelStatus.add(status2, BorderLayout.EAST);

        painel.add(painelStatus, BorderLayout.NORTH);

        painelPontosV = new JPanel();
        painelPontosV.setLayout(new BorderLayout());

        pontosV1 = new JLabel("0 pontos de vitória");
        pontosV1.setFont(new Font("Verdana", Font.PLAIN, 18));
        painelPontosV.add(pontosV1, BorderLayout.WEST);

        pontosV2 = new JLabel("0 pontos de vitória");
        pontosV2.setFont(new Font("Verdana", Font.PLAIN, 18));
        painelPontosV.add(pontosV2, BorderLayout.EAST);

        painel.add(painelPontosV, BorderLayout.CENTER);
        adc(painel, 0, 0, 2, 1, 0);

        painelMatriz = new JPanel();
        int d = Floresta.getDimensaoFloresta();
        painelMatriz.setLayout(new GridLayout(d, d));
        this.setarElementosBotoes(painelMatriz, d);

        adc(painelMatriz, 1, 1, 2, 2, 20);

        painelBotoes = new JPanel();
        painelBotoes.setLayout(new BorderLayout(136, 0));
        painelBotoes.setBorder(new EmptyBorder(0, 40, 0, 40));  //cima,esq,baixo,dir

        botaoDados = new JButton("Jogar Dado");
        acabarMovimento = new JButton("Acabar Movimento");
        usarFruta = new JButton("Usar Uma Fruta");
        pegarFruta = new JButton("Pegar Fruta");
        botaoDados.addActionListener(new ButtonsListener());
        acabarMovimento.addActionListener(new ButtonsListener());
        usarFruta.addActionListener(new ButtonsListener());
        pegarFruta.addActionListener(new ButtonsListener());
        painelBotoes.add(botaoDados, BorderLayout.NORTH);
        painelBotoes.add(acabarMovimento, BorderLayout.CENTER);
        painelBotoes.add(usarFruta, BorderLayout.EAST);
        painelBotoes.add(pegarFruta, BorderLayout.WEST);

        adc(painelBotoes, 0, 3, 2, 1, 0);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(830, 830);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        Image icon = (new ImageIcon(getClass().getResource("/Frutas/Abacaxi.png"))).getImage();
        frame.setIconImage(icon);

    }
    
    //método que adiciona os outros paineis no painel principal 
    public void adc(JPanel p, int x, int y, int largura, int altura, int pesoy) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = largura;
        gbc.gridheight = altura;
        gbc.weightx = 1;
        gbc.weighty = pesoy;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        frame.add(p, gbc);
    }
    
    //método que cria os botoes para cada posição da matriz 
    private void setarElementosBotoes(JPanel painelMatriz, int d) {
        for (int i = 0; i < d; i++) {
            for (int j = 0; j < d; j++) {
                botoesM[i][j] = new BotaoM(i, j);
                setarElementos(botoesM[i][j], i, j);

                int[] pos = Jogo.getAvatar1().getPosicao();
                if ((pos[0] == i) && (pos[1] == j)) {
                    this.setarAvatar(botoesM[i][j], true);
                }
                int[] pos2 = Jogo.getAvatar2().getPosicao();
                if ((pos2[0] == i) && (pos2[1] == j)) {
                    this.setarAvatar(botoesM[i][j], false);
                }
                painelMatriz.add(botoesM[i][j]);
            }
        }
    }
    //dependendo do botão clicado implementa uma ação diferente para o avatar
    public class ButtonsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == botaoDados) {
                if (Jogo.getUltimoAJogar() == 1) {
                    Jogo.getAvatar2().jogarDado();
                } else if (Jogo.getUltimoAJogar() == 2) {
                    Jogo.getAvatar1().jogarDado();
                }

            } else if (event.getSource() == acabarMovimento) {
                if (Jogo.getUltimoAJogar() == 1) {
                    Jogo.getAvatar1().acabar();
                } else if (Jogo.getUltimoAJogar() == 2) {
                    Jogo.getAvatar2().acabar();
                }
            } else if (event.getSource() == usarFruta) {
                if (Jogo.getUltimoAJogar() == 1) {
                    Jogo.getAvatar1().usar();
                } else if (Jogo.getUltimoAJogar() == 2) {
                    Jogo.getAvatar2().usar();
                }
            } else if (event.getSource() == pegarFruta) {
                if (Jogo.getUltimoAJogar() == 1) {
                    int[] pos = Jogo.getAvatar1().getPosicao();
                    Jogo.getAvatar1().pegar(pos[0], pos[1]);
                } else if (Jogo.getUltimoAJogar() == 2) {
                    int[] pos = Jogo.getAvatar2().getPosicao();
                    Jogo.getAvatar2().pegar(pos[0], pos[1]);
                }
            }

        }
    }

    public void setStatus1(String status) {
        status1.setText(status);
    }

    public void setStatus2(String status) {
        status2.setText(status);
    }

    public void setPontosV1(String status) {
        pontosV1.setText(status);
    }

    public void setPontosV2(String status) {
        pontosV2.setText(status);
    }

    public void setRodadas(String status) {
        rodadas.setText(status);
    }

    public void setCorStatus1() {
        if (status1.getForeground() == Color.green) {
            status1.setForeground(Color.black);
        } else {
            status1.setForeground(Color.green);
        }
    }

    public void setCorStatus2() {
        if (status2.getForeground() == Color.green) {
            status2.setForeground(Color.black);
        } else {
            status2.setForeground(Color.green);
        }
    }
    //método que seta a imagem do botao de acordo com elementos presentes naquela posição
    public void setarAvatar(BotaoM b, boolean ehAvatar1) {
        ImageIcon icone = null;
        if (ehAvatar1 == true) {
            int[] pos = Jogo.getAvatar1().getPosicao();
            //System.out.println("Pos avatar:"+pos[0]+pos[1]); 
            if (Floresta.elementoNaPosicao(pos[0], pos[1]) instanceof Arvore) {
                if ((MatrizFrutas.elementoNaPosicao(pos[0], pos[1]) == null) || MatrizFrutas.elementoNaPosicao(pos[0], pos[1]).getFoiRecolhida() == true) {
                    icone = new ImageIcon(getClass().getResource("/Personagens/BoyA.png"));
                } else if (MatrizFrutas.elementoNaPosicao(pos[0], pos[1]) instanceof Abacaxi) {
                    icone = new ImageIcon(getClass().getResource("/Personagens/BoyAO.png"));
                } else if (MatrizFrutas.elementoNaPosicao(pos[0], pos[1]) instanceof Abacate) {
                    icone = new ImageIcon(getClass().getResource("/Personagens/BoyAA.png"));
                } else if (MatrizFrutas.elementoNaPosicao(pos[0], pos[1]) instanceof Laranja) {
                    icone = new ImageIcon(getClass().getResource("/Personagens/BoyAL.png"));
                } else if (MatrizFrutas.elementoNaPosicao(pos[0], pos[1]) instanceof Melancia) {
                    icone = new ImageIcon(getClass().getResource("/Personagens/BoyAME.png"));
                } else if (MatrizFrutas.elementoNaPosicao(pos[0], pos[1]) instanceof Morango) {
                    icone = new ImageIcon(getClass().getResource("/Personagens/BoyAM.png"));
                }
            } else if (Floresta.elementoNaPosicao(pos[0], pos[1]) instanceof Grama) {
                if ((MatrizFrutas.elementoNaPosicao(pos[0], pos[1]) == null) || MatrizFrutas.elementoNaPosicao(pos[0], pos[1]).getFoiRecolhida() == true) {
                    icone = new ImageIcon(getClass().getResource("/Personagens/BoyG.png"));
                } else if (MatrizFrutas.elementoNaPosicao(pos[0], pos[1]) instanceof Abacaxi) {
                    icone = new ImageIcon(getClass().getResource("/Personagens/BoyO.png"));
                } else if (MatrizFrutas.elementoNaPosicao(pos[0], pos[1]) instanceof Abacate) {
                    icone = new ImageIcon(getClass().getResource("/Personagens/BoyAB.png"));
                } else if (MatrizFrutas.elementoNaPosicao(pos[0], pos[1]) instanceof Laranja) {
                    icone = new ImageIcon(getClass().getResource("/Personagens/BoyL.png"));
                } else if (MatrizFrutas.elementoNaPosicao(pos[0], pos[1]) instanceof Melancia) {
                    icone = new ImageIcon(getClass().getResource("/Personagens/BoyME.png"));
                } else if (MatrizFrutas.elementoNaPosicao(pos[0], pos[1]) instanceof Morango) {
                    icone = new ImageIcon(getClass().getResource("/Personagens/BoyM.png"));
                }
            }
        } else if (ehAvatar1 == false) {
            int[] pos = Jogo.getAvatar2().getPosicao();
            if (Floresta.elementoNaPosicao(pos[0], pos[1]) instanceof Arvore) {
                if ((MatrizFrutas.elementoNaPosicao(pos[0], pos[1]) == null) || MatrizFrutas.elementoNaPosicao(pos[0], pos[1]).getFoiRecolhida() == true) {
                    icone = new ImageIcon(getClass().getResource("/Personagens/GirlA.png"));
                } else if (MatrizFrutas.elementoNaPosicao(pos[0], pos[1]) instanceof Abacaxi) {
                    icone = new ImageIcon(getClass().getResource("/Personagens/GirlAO.png"));
                } else if (MatrizFrutas.elementoNaPosicao(pos[0], pos[1]) instanceof Abacate) {
                    icone = new ImageIcon(getClass().getResource("/Personagens/GirlAA.png"));
                } else if (MatrizFrutas.elementoNaPosicao(pos[0], pos[1]) instanceof Laranja) {
                    icone = new ImageIcon(getClass().getResource("/Personagens/GirlAL.png"));
                } else if (MatrizFrutas.elementoNaPosicao(pos[0], pos[1]) instanceof Melancia) {
                    icone = new ImageIcon(getClass().getResource("/Personagens/GirlAME.png"));
                } else if (MatrizFrutas.elementoNaPosicao(pos[0], pos[1]) instanceof Morango) {
                    icone = new ImageIcon(getClass().getResource("/Personagens/GirlAM.png"));
                }
            } else if (Floresta.elementoNaPosicao(pos[0], pos[1]) instanceof Grama) {
                if ((MatrizFrutas.elementoNaPosicao(pos[0], pos[1]) == null) || MatrizFrutas.elementoNaPosicao(pos[0], pos[1]).getFoiRecolhida() == true) {
                    icone = new ImageIcon(getClass().getResource("/Personagens/GirlG.png"));
                } else if (MatrizFrutas.elementoNaPosicao(pos[0], pos[1]) instanceof Abacaxi) {
                    icone = new ImageIcon(getClass().getResource("/Personagens/GirlO.png"));
                } else if (MatrizFrutas.elementoNaPosicao(pos[0], pos[1]) instanceof Abacate) {
                    icone = new ImageIcon(getClass().getResource("/Personagens/GirlAB.png"));
                } else if (MatrizFrutas.elementoNaPosicao(pos[0], pos[1]) instanceof Laranja) {
                    icone = new ImageIcon(getClass().getResource("/Personagens/GirlL.png"));
                } else if (MatrizFrutas.elementoNaPosicao(pos[0], pos[1]) instanceof Melancia) {
                    icone = new ImageIcon(getClass().getResource("/Personagens/GirlME.png"));
                } else if (MatrizFrutas.elementoNaPosicao(pos[0], pos[1]) instanceof Morango) {
                    icone = new ImageIcon(getClass().getResource("/Personagens/GirlM.png"));
                }
            }
        }
        b.setIcon(icone);
        
    }
   //método que seta a imagem do botão dependendo dos elementos naquela posição
    public void setarElementos(BotaoM b, int x, int y) {
        ImageIcon icone = null;
        if (Floresta.elementoNaPosicao(x, y) instanceof Pedra) {
            icone = Floresta.elementoNaPosicao(x, y).getIcone();
        } else if (Floresta.elementoNaPosicao(x, y) instanceof Arvore) {
            if ((MatrizFrutas.elementoNaPosicao(x, y) == null) || MatrizFrutas.elementoNaPosicao(x, y).getFoiRecolhida() == true) {
                icone = Floresta.elementoNaPosicao(x, y).getIcone();
            } else if (MatrizFrutas.elementoNaPosicao(x, y) instanceof Abacaxi) {
                icone = new ImageIcon(getClass().getResource("/Cenario/ArvoreO.png"));
            } else if (MatrizFrutas.elementoNaPosicao(x, y) instanceof Abacate) {
                icone = new ImageIcon(getClass().getResource("/Cenario/ArvoreA.png"));
            } else if (MatrizFrutas.elementoNaPosicao(x, y) instanceof Laranja) {
                icone = new ImageIcon(getClass().getResource("/Cenario/ArvoreL.png"));
            } else if (MatrizFrutas.elementoNaPosicao(x, y) instanceof Melancia) {
                icone = new ImageIcon(getClass().getResource("/Cenario/ArvoreME.png"));
            } else if (MatrizFrutas.elementoNaPosicao(x, y) instanceof Morango) {
                icone = new ImageIcon(getClass().getResource("/Cenario/ArvoreM.png"));
            }
        } else if (Floresta.elementoNaPosicao(x, y) instanceof Grama) {
            if ((MatrizFrutas.elementoNaPosicao(x, y) == null) || MatrizFrutas.elementoNaPosicao(x, y).getFoiRecolhida() == true) {
                icone = Floresta.elementoNaPosicao(x, y).getIcone();
            } else {
                icone = MatrizFrutas.elementoNaPosicao(x, y).getIcone();
            }
        }
        b.setIcon(icone);
        //adcResize(b,icone);

    }

    public BotaoM getBotaoM(int x, int y) {
        return botoesM[x][y];
    }

    public JFrame getFrame() {
        return this.frame;
    }

    public void fecharJanela() {
        this.frame.dispose();
    }

}

