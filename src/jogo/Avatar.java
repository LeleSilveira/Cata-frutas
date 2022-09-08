package jogo;

import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * Classe que configura os atributos e métodos dos personagens
 *
 * @author Letícia
 */
public class Avatar extends Elementos {

    private int força;
    private int pontos;
    private int pontosDeVitoria = 0;
    private boolean podeMover = true;
    private Mochila mochila;
    private boolean estaNaArvore = false;
    private int rodadaQueColheu = -10;
    private boolean PodeMoverProx = true;

    //cria o avatar e sua mochila e seta sua posição 
    public Avatar(int numero) {
        if (numero == 1) {
            this.setIcone(new ImageIcon(getClass().getResource("/Personagens/BoyG.png")));
        } else if (numero == 2) {
            this.setIcone(new ImageIcon(getClass().getResource("/Personagens/GirlG.png")));
        }
        mochila = new Mochila();
        boolean ok = false;
        while (ok == false) {
            Random aleatorio = new Random();
            int x = aleatorio.nextInt(Floresta.getDimensaoFloresta());
            int y = aleatorio.nextInt(Floresta.getDimensaoFloresta());
            if (Floresta.elementoNaPosicao(x, y) instanceof Pedra) {
                continue;
            } else {
                this.setPosicao(x, y);
                ok = true;
            }
        }

    }
    //método chamado quando um avatar empurra o outro, calcula e faz cair as frutas da mochila
    private void empurrar(char letra, Avatar outroAvatar) {
        System.out.println("Empurrando outro avatar");
        if (letra == 'a') {
            this.mudaPosicao(0, 1);
        }
        if (letra == 'd') {
            this.mudaPosicao(0, -1);
        }
        if (letra == 'w') {
            this.mudaPosicao(1, 0);
        }
        if (letra == 's') {
            this.mudaPosicao(-1, 0);
        }
        int[] pos2 = outroAvatar.getPosicao();
        int f_a = this.força;
        int f_d = outroAvatar.força;
        int empurrao = (int) (Math.round(log(2, (f_a + 1))) - Math.round(log(2, (f_d + 1))));
        int quantFrutas = Math.max(0, empurrao);
        System.out.println(quantFrutas + " tem que cair");
        JOptionPane.showMessageDialog(Jogo.getJanelaJogo(), "Avatar empurrado\nForça jogador que empurrou: " + f_a + "\nForça do jogador empurrado: " + f_d + "\n" + quantFrutas + " vão cair da mochila", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        if (quantFrutas != 0) {
            for (int i = 0; i < quantFrutas; i++) {
                Fruta fruta = outroAvatar.mochila.retirarFruta();

                fruta.setFoiRecolhida(false);
                MatrizFrutas.adcionarFrutaCaida(pos2[0], pos2[1], fruta);
            }
            this.força -= quantFrutas;
        }
    }
    //método chamado quando o avatar pega uma fruta, adiciona ela na mochila, e se for ouro confere se 
    //já recolheu suficiente para ganhar
    public void pegar(int x, int y) { 
        if(MatrizFrutas.elementoNaPosicao(x, y).getFoiRecolhida()==true){
            
        }
        else{
        if (this.pontos != 0) {
            if (mochila.estaCheia() == true) {
                JOptionPane.showMessageDialog(Jogo.getJanelaJogo(), "Mochila ja está em sua capacidade máxima", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                this.pontos--;
                this.força++;
                this.atualizaJanelaPontos();
                if (MatrizFrutas.elementoNaPosicao(x, y).ehBichada() == true) {
                    JOptionPane.showMessageDialog(Jogo.getJanelaJogo(), "Ela vai pra sua mochila e funciona normalmente,\nmas você não pode se mover na proxima rodada", "Fruta bichada!", 0, new ImageIcon(getClass().getResource("/Cenario/bicho.png")));
                    this.PodeMoverProx = false;
                }
                mochila.adicionarFruta(MatrizFrutas.elementoNaPosicao(x, y));
                MatrizFrutas.elementoNaPosicao(x, y).setFoiRecolhida(true);
                System.out.println("Fruta na mochila. Pontos:" + this.pontos + " Força:" + this.força);
                JOptionPane.showMessageDialog(Jogo.getJanelaJogo(), "Fruta adcionada na mochila\nForça: " + this.força, "Aviso", JOptionPane.INFORMATION_MESSAGE);
                if (this.ehAvatar1() == true) {
                    Jogo.janelaSetarAvatar(Jogo.janelaGetBotaoM(x, y), true);
                } else {
                    Jogo.janelaSetarAvatar(Jogo.janelaGetBotaoM(x, y), false);
                }
                if (MatrizFrutas.elementoNaPosicao(x, y) instanceof Abacaxi) {
                    this.AumentaPontosDeVitoria();
                    this.atualizaJanelaPontos();
                    int meta = (int) (Math.floor((float) Abacaxi.getTotalDeOuro() / 2.0f) + 1);
                    if (this.getPontosDeVitoria() == meta) {
                        System.out.println("Voce ganhou");
                        JOptionPane.showMessageDialog(Jogo.getJanelaJogo(), "Você ganhou o jogo", "Parabéns!", 0, new ImageIcon(getClass().getResource("/Cenario/trofeu1.png")));
                        Jogo.feharJanela();
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(Jogo.getJanelaJogo(), "Você não tem pontos para pegar a fruta", "Aviso", JOptionPane.INFORMATION_MESSAGE);

        }
    }
    }
    //método chamado quando o botão usar é clicado
    public void usar() {
        mochila.imprimir();

    }
    //método chamado quando um botão adjacente ao avatar é clicado, dependendo da direção sua posição
    //é atualizada e, dependendo do elemento  que está ali diferentes ações são implementadas
    public int mover(char letra) throws Exception {
        if ((this.podeMover == true) && (this.pontos != 0)) {
            int[] pos = this.getPosicao();
//        System.out.println("Sua posição:"+pos[0]+","+pos[1]+"\nAperte AWSD para se mover, U para usar uma fruta e N se deseja para de se mover");
            //char letra= (char)System.in.read();

            if (letra == 'a') {
                System.out.println("Moveu para esquerda");
                this.mudaPosicao(0, -1);
            }
            if (letra == 'd') {
                System.out.println("Moveu para direita");
                this.mudaPosicao(0, 1);
            }
            if (letra == 'w') {
                System.out.println("Moveu para cima");
                this.mudaPosicao(-1, 0);
            }
            if (letra == 's') {
                System.out.println("Moveu para baixo");
                this.mudaPosicao(1, 0);
            }
            if (letra == 'u') {
                this.usar();
            }
            if (letra == 'n') {
                this.acabar();

            }

            if (Floresta.elementoNaPosicao(pos[0], pos[1]) instanceof Grama) {
                pontos--;
                this.atualizaJanelaPontos();
                System.out.println("Célula com grama   Pontos:" + this.pontos);
                if (this.estaNaArvore == true) {
                    this.estaNaArvore = false;
                }
            } else if (Floresta.elementoNaPosicao(pos[0], pos[1]) instanceof Arvore) {
                pontos--;
                this.atualizaJanelaPontos();   //popup qual o tipo e aviso
                System.out.println("Célula com árvore   Pontos:" + this.pontos);
                JOptionPane.showMessageDialog(Jogo.getJanelaJogo(), Floresta.elementoNaPosicao(pos[0], pos[1]) + "\nSe permanecer mais uma rodada cairá uma fruta\n(Intervalo de 10 rodadas quando cair)", "Árvore", 0, ((Arvore) Floresta.elementoNaPosicao(pos[0], pos[1])).getTipoFruta().getFrutaIcon());
                if (this.estaNaArvore == false) {
                    this.estaNaArvore = true;
                }
            } else if (Floresta.elementoNaPosicao(pos[0], pos[1]) instanceof Pedra) {
                if ((this.pontos < 3) || posicaoInvalidaPedra(pos[0], pos[1], letra) == 1) { //posicao que vai mudar tiver pedra ou for quina
                    if (letra == 'a') {
                        this.mudaPosicao(0, 1);
                    }
                    if (letra == 'd') {
                        this.mudaPosicao(0, -1);
                    }
                    if (letra == 'w') {
                        this.mudaPosicao(1, 0);
                    }
                    if (letra == 's') {
                        this.mudaPosicao(-1, 0);
                    }
                    System.out.println("Nao tem pontuação suficiente para isso");//popup  função adc item na janela
                    JOptionPane.showMessageDialog(Jogo.getJanelaJogo(), "Não dá pra pular essa pedra", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    return 0;
                } else {
                    this.pontos = this.pontos - 3;
                    this.atualizaJanelaPontos();
                    System.out.println("Pulou a pedra   Pontos:" + this.pontos);
                    if (letra == 'a') {
                        this.mudaPosicao(0, -1);
                    }
                    if (letra == 'd') {
                        this.mudaPosicao(0, 1);
                    }
                    if (letra == 'w') {
                        this.mudaPosicao(-1, 0);
                    }
                    if (letra == 's') {
                        this.mudaPosicao(1, 0);
                    }

                    if (this.estaNaArvore == true) {
                        this.estaNaArvore = false;
                    }
                }
            }

            if ((MatrizFrutas.elementoNaPosicao(pos[0], pos[1]) instanceof Fruta) && (MatrizFrutas.elementoNaPosicao(pos[0], pos[1]).getFoiRecolhida() == false)) {
                System.out.println("Célula com fruta. Deseja pegar a fruta" + MatrizFrutas.elementoNaPosicao(pos[0], pos[1]).toString() + " S/N?");
                this.atualizaJanelaPontos();
            }

            //Se mover para uma posição que seja a mesma do outro avatar
            if (this.ehAvatar1() == true) {
                int[] pos2 = Jogo.getAvatar2().getPosicao();
                if ((pos[0] == pos2[0]) && (pos[1] == pos2[1])) {
                    empurrar(letra, Jogo.getAvatar2());
                    return 0;
                }

            } else if (this.ehAvatar1() == false) {
                int[] pos2 = Jogo.getAvatar1().getPosicao();
                if ((pos[0] == pos2[0]) && (pos[1] == pos2[1])) {
                    empurrar(letra, Jogo.getAvatar1());
                    return 0;
                }

            }
            return 1;
        } else {
            System.out.println("Você não pode se mexer"); //colocar um popup
            JOptionPane.showMessageDialog(Jogo.getJanelaJogo(), "Você não pode se mover", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            this.pontos = 0;
            atualizaJanelaPontos();
            this.podeMover = true;
            return 0;
        }
    }
    //método chamado quando o botão jogar dado é clicado, gera uma nova pontuação e zera a do outro
    public void jogarDado() {
        Dado dado1 = new Dado();
        Dado dado2 = new Dado();
        this.pontos = dado1.getValor() + dado2.getValor();
        Jogo.setTotalDeRodadas((float) (Jogo.getTotalDeRodadas() + 0.5));
        Jogo.JanelaRodadas("Rodadas: " + (int) Math.floor(Jogo.getTotalDeRodadas()));
        if (this.ehAvatar1() == true) {
            Jogo.getAvatar2().zerarPontos();
            if ((int) Math.floor(Jogo.getTotalDeRodadas()) != 0) {
                Jogo.JanelaSetCorStatus2();
            }
            Jogo.JanelaSetCorStatus1();
            Jogo.JanelaSetStatus1("Jogador 1: " + this.pontos + " pontos");
            Jogo.JanelaSetStatus2("Jogador 2: " + Jogo.getAvatar2().getPontos() + " pontos");
            Jogo.setUltimoAJogar(1);
        } else if (this.ehAvatar1() == false) {
            Jogo.getAvatar1().zerarPontos();
            if ((int) Math.floor(Jogo.getTotalDeRodadas()) != 0) {
                Jogo.JanelaSetCorStatus1();
            }
            Jogo.JanelaSetCorStatus2();
            Jogo.JanelaSetStatus1("Jogador 1: " + Jogo.getAvatar1().getPontos() + " pontos");
            Jogo.JanelaSetStatus2("Jogador 2: " + this.pontos + " pontos");
            Jogo.setUltimoAJogar(2);
        }
        if (this.PodeMoverProx == false) {
            this.podeMover = false;
            this.PodeMoverProx = true;
        }
        int[] pos = this.getPosicao();
        System.out.println("Dado1:" + dado1.getValor() + "  Dado2:" + dado2.getValor() + "   Pontos:" + this.pontos);
        if ((this.estaNaArvore == true) && (Math.abs(this.rodadaQueColheu - (int) Math.floor(Jogo.getTotalDeRodadas())) >= 10)) {
           if((MatrizFrutas.elementoNaPosicao(pos[0],pos[1]) instanceof Abacaxi)==false||(MatrizFrutas.elementoNaPosicao(pos[0],pos[1]).getFoiRecolhida()==true)){
            this.rodadaQueColheu = (int) Math.floor(Jogo.getTotalDeRodadas());
            ((Arvore) Floresta.elementoNaPosicao(pos[0], pos[1])).cairFrutas(); 
            System.out.println("Uma " + ((Arvore) Floresta.elementoNaPosicao(pos[0], pos[1])).nomeDaFruta() + " caiu. Deseja pegar S/N?");
        }
        }
    }

    public int getPontosDeVitoria() {
        return this.pontosDeVitoria;
    }

    public int getPontos() {
        return this.pontos;
    }

    public void AumentaPontosDeVitoria() {
        this.pontosDeVitoria++;
    }

    public void dobraForca() {
        this.força = (this.força * 2);
        atualizaJanelaPontos();
        JOptionPane.showMessageDialog(Jogo.getJanelaJogo(), "Força: " + this.força, "Aviso", JOptionPane.INFORMATION_MESSAGE);

    }

    public void dobraPontos() {
        this.pontos = (this.pontos * 2);
        atualizaJanelaPontos();
        JOptionPane.showMessageDialog(Jogo.getJanelaJogo(), "Pontos dobrados", "Aviso", JOptionPane.INFORMATION_MESSAGE);

    }

    public void anulaBichada() {
        this.podeMover = true;
        JOptionPane.showMessageDialog(Jogo.getJanelaJogo(), "O movimento está liberado", "Aviso", JOptionPane.INFORMATION_MESSAGE);

    }

    public void setPodeMover(boolean permissao) {
        this.podeMover = permissao;
    }

    public void zerarPontos() {
        this.pontos = 0;
    }

    public void acabar() {
        this.pontos = 0;
        atualizaJanelaPontos();
        System.out.println("Avatar nao deseja se mover\nPontos:" + this.pontos);
    }

    public void mudaPosicao(int x, int y) {
        int[] pos = this.getPosicao();
        if (((pos[0] + x) >= 0) && ((pos[0] + x) < Floresta.getDimensaoFloresta()) && ((pos[1] + y) >= 0) && ((pos[1] + y) < Floresta.getDimensaoFloresta())) {
            this.setPosicao(pos[0] + x, pos[1] + y);
        } else {
            System.out.println("Erro. Posição inválida");
        }
    }

    public boolean ehAvatar1() {
        if (this == Jogo.getAvatar1()) {
            return true;
        } else {
            return false;
        }
    }

    private static double log(double base, double valor) {
        return Math.log(valor) / Math.log(base);
    }
    //método que muda as informações no painel
    public void atualizaJanelaPontos() {
        if (this.ehAvatar1() == true) {
            Jogo.JanelaSetStatus1("Jogador 1: " + this.pontos + " pontos");
            Jogo.JanelaSetPontosV1(this.pontosDeVitoria + " pontos de vitória");
        } else if (this.ehAvatar1() == false) {
            Jogo.JanelaSetStatus2("Jogador 2: " + this.pontos + " pontos");
            Jogo.JanelaSetPontosV2(this.pontosDeVitoria + " pontos de vitória");
        }
    }
    // confere se a posição ao pular a pedra é válida
    public int posicaoInvalidaPedra(int posx, int posy, char letra) {
        if (letra == 'w') {
            if (posx == 0) {
                return 1;
            } else if (Floresta.elementoNaPosicao((posx - 1), posy) instanceof Pedra) {
                return 1;
            }
        } else if (letra == 's') {
            if (posx == Floresta.getDimensaoFloresta() - 1) {
                return 1;
            } else if (Floresta.elementoNaPosicao((posx + 1), posy) instanceof Pedra) {
                return 1;
            }
        } else if (letra == 'a') {
            if (posy == 0) {
                return 1;
            } else if (Floresta.elementoNaPosicao(posx, (posy - 1)) instanceof Pedra) {
                return 1;
            }
        } else if (letra == 'd') {
            if (posy == Floresta.getDimensaoFloresta() - 1) {
                return 1;
            } else if (Floresta.elementoNaPosicao(posx, (posy + 1)) instanceof Pedra) {
                return 1;
            }
        }
        return 0;
    }

    public Mochila getMochila() {
        return this.mochila;
    }

    public void setMochila(Mochila mochila) {
        this.mochila = mochila;
    }
   //método chamado se os dois avatares forem setados na mesma posição
    void outraposicao() {
        boolean ok = false;
        while (ok == false) {
            Random aleatorio = new Random();
            int x = aleatorio.nextInt(Floresta.getDimensaoFloresta());
            int y = aleatorio.nextInt(Floresta.getDimensaoFloresta());
            if (Floresta.elementoNaPosicao(x, y) instanceof Pedra) {
                continue;
            }
            int[] pos = Jogo.getAvatar1().getPosicao();
            if ((pos[0] == x) && (pos[1] == y)) { //avatar2 não pode ter a mesma posição que avatar1
                continue;
            } else {
                this.setPosicao(x, y);
                ok = true;
            }
        }
    }

    public int getForça() {
        return this.força;
    }

    public void setForça(int força) {
        this.força = força;
    }

}
