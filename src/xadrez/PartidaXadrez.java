package xadrez;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

//É onde tera as regras do jogo

public class PartidaXadrez {
    private Tabuleiro tabuleiro; //importar tabuleiro aqui

    public  PartidaXadrez(){
        tabuleiro = new Tabuleiro(8, 8); //dimensão do tabuleiro
        iniciarPartida(); //chamando a peca
    }

    public PecaXadrez[][] getPecas(){ // retorna uma matriz de peças
        PecaXadrez[][] mat = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
        for (int i=0; i<tabuleiro.getLinhas(); i++ ){//percorrendo as linhas da matriz
            for (int j=0; j<tabuleiro.getColunas(); j++ ){//percorrendo as colunas da matriz
                mat[i][j] = (PecaXadrez)tabuleiro.peca(i, j);
            }
        }
        return mat;
    }
    // colocando as pecas no tabuleiro
    private void iniciarPartida(){
        tabuleiro.entradaPeca(new Torre(tabuleiro, Cor.WHITE), new Posicao(2, 1)); // posicao da camada tabela
        tabuleiro.entradaPeca(new Rei(tabuleiro, Cor.BLACK), new Posicao(3, 1));
        tabuleiro.entradaPeca(new Torre(tabuleiro, Cor.BLACK), new Posicao(0, 4));
    }
}
