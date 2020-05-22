package xadrez;

import tabuleiro.Tabuleiro;

//É onde tera as regras do jogo

public class PartidaXadrez {
    private Tabuleiro tabuleiro; //importar tabuleiro aqui

    public  PartidaXadrez(){
        tabuleiro = new Tabuleiro(8, 8); //dimensão do tabuleiro
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
}
