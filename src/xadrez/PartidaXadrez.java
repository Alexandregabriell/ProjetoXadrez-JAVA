package xadrez;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.pecas.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//É onde tera as regras do jogo

public class PartidaXadrez {
    private int turno;
    private Cor jogadorAtual;
    private final Tabuleiro tabuleiro; //importar tabuleiro aqui
    private boolean check;
    private boolean checkMate;

    private final List<Peca> pecasNoTabuleiro = new ArrayList<>(); // lista de pecas no tabuleiro
    private final List<Peca> pecasCapturada = new ArrayList<>();// lista de pecas capturadas

    public PartidaXadrez() {
        tabuleiro = new Tabuleiro(8, 8); //dimensão do tabuleiro
        turno = 1; // representado partida que esta
        jogadorAtual = Cor.WHITE;
        iniciarPartida(); //chamando a peca
    }

    public int getTurno() {
        return turno;
    }

    public Cor getJogadorAtual() {
        return jogadorAtual;
    }

    public boolean getCheck() {
        return check;
    }

    public boolean getCheckMate() {
        return checkMate;
    }

    public PecaXadrez[][] getPecas() { // retorna uma matriz de peças
        PecaXadrez[][] mat = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
        for (int i = 0; i < tabuleiro.getLinhas(); i++) {//percorrendo as linhas da matriz
            for (int j = 0; j < tabuleiro.getColunas(); j++) {//percorrendo as colunas da matriz
                mat[i][j] = (PecaXadrez) tabuleiro.peca(i, j);
            }
        }
        return mat;
    }

    public boolean[][] possivelMovimento(PosicaoXadrez origemPosicao) {
        Posicao posicao = origemPosicao.toPosicao(); // posicao de matriz normal
        validaPosicaoOrigem(posicao); // validando posicao de origem
        return tabuleiro.peca(posicao).possivelMovimento(); // retorna possivel movimento da peca escolhida
    }

    // Metodo para retirar a peca da posicao de origem e colocar na posicao de destino
    public PecaXadrez movimentoXadrez(PosicaoXadrez origemPosicao, PosicaoXadrez destinoPosicao) {
        Posicao origem = origemPosicao.toPosicao();// convertendo para posicao da matriz
        Posicao destino = destinoPosicao.toPosicao();
        validaPosicaoOrigem(origem);//validando posicao de origem
        validaPosicaoDestino(origem, destino);
        Peca pecaCapturada = movendoPeca(origem, destino); //recebe resultado da operaca movendo peca, que vai realizar o movimento da peca

        if (testCheck(jogadorAtual)) { // testando se o jogador atual ficou em check
            desfazerMovimento(origem, destino, pecaCapturada);
            throw new ExcecaoXadrez(" VOCE NAO PODE SE COLOCAR EM CHECK ");
        }

        check = (testCheck(oponente(jogadorAtual))) ? true : false; // testando se o oponente ficou em check

        if (testCheckMate(oponente(jogadorAtual))) {
            checkMate = true;
        }
        else {
            proximoTurno();
        }

        return (PecaXadrez)pecaCapturada;
    }

    // removendo pecas
    public Peca movendoPeca(Posicao origem, Posicao destino) {
        PecaXadrez p = (PecaXadrez) tabuleiro.removePeca(origem); // removendo peca de origem
        p.incrementoMoveCount(); // contador para aumentar o movimento
        Peca pecaCapturada = tabuleiro.removePeca(destino); // removendo possivel peca de destino
        tabuleiro.entradaPeca(p, destino); // colocando a peca na posicao de destino a peca que estava na posicao de origem

        if (pecaCapturada != null) {
            pecasNoTabuleiro.remove(pecaCapturada); // removendo peca desta lista
            pecasCapturada.add(pecaCapturada); // adicionando em pecas capturadas
        }

        return pecaCapturada;
    }

    private void desfazerMovimento(Posicao origem, Posicao destino, Peca pecaCapturada) {
        PecaXadrez p = (PecaXadrez) tabuleiro.removePeca(destino);
        p.decrementoMoveCount(); // contador para diminuir um movimento
        tabuleiro.entradaPeca(p, origem);

        if (pecaCapturada != null) {
            tabuleiro.entradaPeca(pecaCapturada, destino);
            pecasCapturada.remove(pecaCapturada);
            pecasNoTabuleiro.add(pecaCapturada);
        }
    }

    // verificando se existe peca na posicao de origem
    private void validaPosicaoOrigem(Posicao posicao) {
        if (!tabuleiro.posicaoOcupada(posicao)) {
            throw new ExcecaoXadrez(" NÃO EXISTE PECA NA POSICAO DE ORIGEM ");
        }
        if (jogadorAtual != ((PecaXadrez) tabuleiro.peca(posicao)).getCor()) {
            throw new ExcecaoXadrez(" A PECA ESCOLHIDA É DO ADVERSARIO");
        }

        if (!tabuleiro.peca(posicao).existeMovimentoPossivel()) { // testando se ha movimentos possiveis para a peca escolhida
            throw new ExcecaoXadrez(" Não existe movimentos possíveis para a peça escolhida. ");
        }
    }

    private void validaPosicaoDestino(Posicao origem, Posicao destino) {
        if (!tabuleiro.peca(origem).possivelMovimento(destino)) {
            throw new ExcecaoXadrez(" A peça selecionada não pode ser movida para a posição escolhida. ");
        }
    }

    private void proximoTurno() {
        turno++;
        jogadorAtual = (jogadorAtual == Cor.WHITE) ? Cor.BLACK : Cor.WHITE; // condicao ternaria, trocando cor
    }

    private Cor oponente(Cor cor) {
        return (cor == Cor.WHITE) ? Cor.BLACK : Cor.WHITE;
    }

    private PecaXadrez rei(Cor cor) {
        List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == cor).collect(Collectors.toList());
        for (Peca p : list) {
            if (p instanceof Rei) {
                return (PecaXadrez) p;
            }
        }
        throw new IllegalStateException("Não a Rei " + cor + " no Tabuleiro");
    }

    private boolean testCheck(Cor cor) {
        Posicao posicaoRei = rei(cor).getPosicaoXadrez().toPosicao();
        List<Peca> pecasOponente = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == oponente(cor)).collect(Collectors.toList());
        for (Peca p : pecasOponente) {
            boolean[][] mat = p.possivelMovimento();
            if (mat[posicaoRei.getlinha()][posicaoRei.getcoluna()]) {
                return true;
            }
        }
        return false;
    }

    private boolean testCheckMate(Cor cor) {
        if (!testCheck(cor)) {
            return false;
        }
        List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == cor).collect(Collectors.toList());
        for (Peca p : list) {
            boolean[][] mat = p.possivelMovimento();
            for (int i=0; i<tabuleiro.getLinhas(); i++) {
                for (int j=0; j<tabuleiro.getColunas(); j++) {
                    if (mat[i][j]) {
                        Posicao origem = ((PecaXadrez)p).getPosicaoXadrez().toPosicao();
                        Posicao destino = new Posicao(i, j);
                        Peca pecaCapturada = movendoPeca(origem, destino);
                        boolean testCheck = testCheck(cor);
                        desfazerMovimento(origem, destino, pecaCapturada);
                        if (!testCheck) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    //metodo para intanciar as coordenadas do xadrez[coluna][linha], e nao da matriz[linha][coluna]
    private void entradaNovaPeca(char coluna, int linha, PecaXadrez peca) {
        tabuleiro.entradaPeca(peca, new PosicaoXadrez(coluna, linha).toPosicao());//instanciando com os novos dados e convertendo para a posicao de matriz
        pecasNoTabuleiro.add(peca); // colocando peca na lista
    }

    // colocando as pecas no tabuleiro
    //instanciando as pecas de xadrez
    private void iniciarPartida() {
        // posicao das pecas na posicao do xadrez
        entradaNovaPeca('a', 1, new Torre(tabuleiro, Cor.WHITE));
        entradaNovaPeca('b', 1, new Cavalo(tabuleiro, Cor.WHITE));
        entradaNovaPeca('c', 1, new Bispo(tabuleiro, Cor.WHITE));
        entradaNovaPeca('d', 1, new Rainha(tabuleiro, Cor.WHITE));
        entradaNovaPeca('e', 1, new Rei(tabuleiro, Cor.WHITE));
        entradaNovaPeca('f', 1, new Bispo(tabuleiro, Cor.WHITE));
        entradaNovaPeca('g', 1, new Cavalo(tabuleiro, Cor.WHITE));
        entradaNovaPeca('h', 1, new Torre(tabuleiro, Cor.WHITE));
        entradaNovaPeca('a', 2, new Peao(tabuleiro, Cor.WHITE));
        entradaNovaPeca('b', 2, new Peao(tabuleiro, Cor.WHITE));
        entradaNovaPeca('c', 2, new Peao(tabuleiro, Cor.WHITE));
        entradaNovaPeca('d', 2, new Peao(tabuleiro, Cor.WHITE));
        entradaNovaPeca('e', 2, new Peao(tabuleiro, Cor.WHITE));
        entradaNovaPeca('f', 2, new Peao(tabuleiro, Cor.WHITE));
        entradaNovaPeca('g', 2, new Peao(tabuleiro, Cor.WHITE));
        entradaNovaPeca('h', 2, new Peao(tabuleiro, Cor.WHITE));

        entradaNovaPeca('a', 8, new Torre(tabuleiro, Cor.BLACK));
        entradaNovaPeca('b', 8, new Cavalo(tabuleiro, Cor.BLACK));
        entradaNovaPeca('c', 8, new Bispo(tabuleiro, Cor.BLACK));
        entradaNovaPeca('d', 8, new Rainha(tabuleiro, Cor.BLACK));
        entradaNovaPeca('e', 8, new Rei(tabuleiro, Cor.BLACK));
        entradaNovaPeca('f', 8, new Bispo(tabuleiro, Cor.BLACK));
        entradaNovaPeca('g', 8, new Cavalo(tabuleiro, Cor.BLACK));
        entradaNovaPeca('h', 8, new Torre(tabuleiro, Cor.BLACK));
        entradaNovaPeca('a', 7, new Peao(tabuleiro, Cor.BLACK));
        entradaNovaPeca('b', 7, new Peao(tabuleiro, Cor.BLACK));
        entradaNovaPeca('c', 7, new Peao(tabuleiro, Cor.BLACK));
        entradaNovaPeca('d', 7, new Peao(tabuleiro, Cor.BLACK));
        entradaNovaPeca('e', 7, new Peao(tabuleiro, Cor.BLACK));
        entradaNovaPeca('f', 7, new Peao(tabuleiro, Cor.BLACK));
        entradaNovaPeca('g', 7, new Peao(tabuleiro, Cor.BLACK));
        entradaNovaPeca('h', 7, new Peao(tabuleiro, Cor.BLACK));
    }
}