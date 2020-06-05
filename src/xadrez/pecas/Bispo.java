package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Bispo extends PecaXadrez {

    public Bispo(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor);
    }

    @Override
    public String toString() {
        return "B";
    }

    @Override
    public boolean[][] possivelMovimento() {
        boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao p = new Posicao(0, 0);

        // acima diagonal esquerda
        p.setValores(posicao.getlinha() - 1, posicao.getcoluna() - 1);
        while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().posicaoOcupada(p)) {
            mat[p.getlinha()][p.getcoluna()] = true;
            p.setValores(p.getlinha() - 1, p.getcoluna() - 1);
        }
        if (getTabuleiro().posicaoExiste(p) && pecaOponente(p)) {
            mat[p.getlinha()][p.getcoluna()] = true;
        }

        // acima diagonal direita
        p.setValores(posicao.getlinha() - 1, posicao.getcoluna() + 1);
        while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().posicaoOcupada(p)) {
            mat[p.getlinha()][p.getcoluna()] = true;
            p.setValores(p.getlinha() - 1, p.getcoluna() + 1);
        }
        if (getTabuleiro().posicaoExiste(p) && pecaOponente(p)) {
            mat[p.getlinha()][p.getcoluna()] = true;
        }

        // abaixo diagonal esquerda
        p.setValores(posicao.getlinha() + 1, posicao.getcoluna() + 1);
        while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().posicaoOcupada(p)) {
            mat[p.getlinha()][p.getcoluna()] = true;
            p.setValores(p.getlinha() + 1, p.getcoluna() + 1);
        }
        if (getTabuleiro().posicaoExiste(p) && pecaOponente(p)) {
            mat[p.getlinha()][p.getcoluna()] = true;
        }

        // abaixo diagonal direita
        p.setValores(posicao.getlinha() + 1, posicao.getcoluna() - 1);
        while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().posicaoOcupada(p)) {
            mat[p.getlinha()][p.getcoluna()] = true;
            p.setValores(p.getlinha() + 1, p.getcoluna() - 1);
        }
        if (getTabuleiro().posicaoExiste(p) && pecaOponente(p)) {
            mat[p.getlinha()][p.getcoluna()] = true;
        }
        return mat;
    }
}
