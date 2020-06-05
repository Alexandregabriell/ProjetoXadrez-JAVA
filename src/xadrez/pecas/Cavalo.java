package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Cavalo extends PecaXadrez {

    public Cavalo(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor);
    }

    @Override
    public String toString() {
        return "C";
    }

    private boolean podeMover(Posicao posicao) { // metodo para mover o rei
        PecaXadrez p = (PecaXadrez) getTabuleiro().peca(posicao);
        return p == null || p.getCor() != getCor(); // verificando se pode mover(nula ou peca adversario)
    }

    @Override
    public boolean[][] possivelMovimento() {
        boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao p = new Posicao(0, 0);

        // acima
        p.setValores(posicao.getlinha() - 1, posicao.getcoluna() - 2);
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
            mat[p.getlinha()][p.getcoluna()] = true;
        }

        // abaixo
        p.setValores(posicao.getlinha() - 2, posicao.getcoluna() - 1);
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
            mat[p.getlinha()][p.getcoluna()] = true;
        }

        // esquerda
        p.setValores(posicao.getlinha() - 2, posicao.getcoluna() + 1);
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
            mat[p.getlinha()][p.getcoluna()] = true;
        }

        // direita
        p.setValores(posicao.getlinha() - 1, posicao.getcoluna() + 2);
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
            mat[p.getlinha()][p.getcoluna()] = true;
        }

        // acima diagonal esquerda
        p.setValores(posicao.getlinha() + 1, posicao.getcoluna() + 2);
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
            mat[p.getlinha()][p.getcoluna()] = true;
        }

        // acima diagonal direita
        p.setValores(posicao.getlinha() + 2, posicao.getcoluna() + 1);
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
            mat[p.getlinha()][p.getcoluna()] = true;
        }

        // abaixo diagonal esquerda
        p.setValores(posicao.getlinha() + 2, posicao.getcoluna() - 1);
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
            mat[p.getlinha()][p.getcoluna()] = true;
        }

        // abaixo diagonal direita
        p.setValores(posicao.getlinha() + 2, posicao.getcoluna() - 2);
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
            mat[p.getlinha()][p.getcoluna()] = true;
        }
        return mat;
    }

}
