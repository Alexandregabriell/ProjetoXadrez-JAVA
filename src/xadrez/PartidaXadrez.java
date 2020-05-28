package xadrez;

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
    //metodo para intanciar as coordenadas do xadrez[coluna][linha], e nao da matriz[linha][coluna]
    private void entradaNovaPeca(char coluna, int linha, PecaXadrez peca){
        tabuleiro.entradaPeca(peca, new posicaoXadrez(coluna, linha).toPosicao());//instanciando com os novos dados e convertendo para a posicao de matriz
    }
    // colocando as pecas no tabuleiro
    //instanciando as pecas de xadrez
    private void iniciarPartida(){
        entradaNovaPeca('b', 6, new Torre(tabuleiro, Cor.WHITE)); // posicao das pecas na posicao do xadrez
        entradaNovaPeca('e', 8, new Rei(tabuleiro, Cor.BLACK));
        entradaNovaPeca('e', 1, new Torre(tabuleiro, Cor.BLACK));
    }
}
