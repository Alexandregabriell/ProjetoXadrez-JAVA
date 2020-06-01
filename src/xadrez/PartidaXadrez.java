package xadrez;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

//É onde tera as regras do jogo

public class PartidaXadrez {
    private Tabuleiro tabuleiro; //importar tabuleiro aqui

    public PartidaXadrez() {
        tabuleiro = new Tabuleiro(8, 8); //dimensão do tabuleiro
        iniciarPartida(); //chamando a peca
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

    public boolean[][] possivelMovimento(posicaoXadrez origemPosicao) {
        Posicao posicao = origemPosicao.toPosicao(); // posicao de matriz normal
        validaPosicaoOrigem(posicao); // validando posicao de origem
        return tabuleiro.peca(posicao).possivelMovimento(); // retorna possivel movimento da peca escolhida
    }

    // Metodo para retirar a peca da posicao de origem e colocar na posicao de destino
    public PecaXadrez movimentoXadrez(posicaoXadrez origemPosicao, posicaoXadrez destinoPosicao) {
        Posicao origem = origemPosicao.toPosicao();// convertendo para posicao da matriz
        Posicao destino = destinoPosicao.toPosicao();
        validaPosicaoOrigem(origem);//validando posicao de origem
        validaPosicaoDestino(origem, destino);
        Peca capturaPeca = movendoPeca(origem, destino); //recebe resultado da operaca movendo peca, que vai realizar o movimento da peca
        return (PecaXadrez) capturaPeca;
    }

    // removendo pecas
    public Peca movendoPeca(Posicao origem, Posicao destino) {
        Peca p = tabuleiro.removePeca(origem); // removendo peca de origem
        Peca capturaPreca = tabuleiro.removePeca(destino); // removendo possivel peca de origem
        tabuleiro.entradaPeca(p, destino); // colocando a peca na posicao de destino
        return capturaPreca;
    }

    // verificando se existe peca na posicao de origem
    private void validaPosicaoOrigem(Posicao posicao) {
        if (!tabuleiro.posicaoOcupada(posicao)) {
            throw new excecaoXadrez(" NÃO EXISTE PECA NA POSICAO DE ORIGEM ");
        }
        if (!tabuleiro.peca(posicao).existeMovimentoPossivel()) { // testando se ha movimentos possiveis para a peca escolhida
            throw new excecaoXadrez(" Não existe movimentos possíveis para a peça escolhida. ");
        }
    }

    private void validaPosicaoDestino(Posicao origem, Posicao destino) {
        if (!tabuleiro.peca(origem).possivelMovimento(destino)) {
            throw new excecaoXadrez(" A peça escolhida não pode ser movida para a posição escolhida. ");
        }
    }
        //metodo para intanciar as coordenadas do xadrez[coluna][linha], e nao da matriz[linha][coluna]
        private void entradaNovaPeca( char coluna, int linha, PecaXadrez peca){
            tabuleiro.entradaPeca(peca, new posicaoXadrez(coluna, linha).toPosicao());//instanciando com os novos dados e convertendo para a posicao de matriz
        }


        // colocando as pecas no tabuleiro
        //instanciando as pecas de xadrez
        private void iniciarPartida () {
            // posicao das pecas na posicao do xadrez
            entradaNovaPeca('c', 1, new Torre(tabuleiro, Cor.WHITE));
            entradaNovaPeca('c', 2, new Torre(tabuleiro, Cor.WHITE));
            entradaNovaPeca('d', 2, new Torre(tabuleiro, Cor.WHITE));
            entradaNovaPeca('e', 2, new Torre(tabuleiro, Cor.WHITE));
            entradaNovaPeca('e', 1, new Torre(tabuleiro, Cor.WHITE));
            entradaNovaPeca('d', 1, new Rei(tabuleiro, Cor.WHITE));

            entradaNovaPeca('c', 7, new Torre(tabuleiro, Cor.BLACK));
            entradaNovaPeca('c', 8, new Torre(tabuleiro, Cor.BLACK));
            entradaNovaPeca('d', 7, new Torre(tabuleiro, Cor.BLACK));
            entradaNovaPeca('e', 7, new Torre(tabuleiro, Cor.BLACK));
            entradaNovaPeca('e', 8, new Torre(tabuleiro, Cor.BLACK));
            entradaNovaPeca('d', 8, new Rei(tabuleiro, Cor.BLACK));
        }
    }