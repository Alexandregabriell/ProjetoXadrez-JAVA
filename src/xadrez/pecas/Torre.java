package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Torre extends PecaXadrez {
    public Torre(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor); // passando a chamada para a super classe
    }

    @Override
    public String toString(){
        return "T";
    }

    @Override
    public boolean[][] possivelMovimento() {
        boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
        Posicao p = new Posicao(0, 0); // posicao auxiliar

        // testando posicoes livres
        // acima
        p.setValores(posicao.getlinha() - 1, posicao.getcoluna());
        while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().posicaoOcupada(p)) {
            mat[p.getlinha()][p.getcoluna()] = true;
            p.setlinha(p.getlinha() - 1);
        }
        if (getTabuleiro().posicaoExiste(p) && pecaOponente(p)) { // verificando se a uma peca do oponente
            mat[p.getlinha()][p.getcoluna()] = true;
        }

        // esquerda
        p.setValores(posicao.getlinha(), posicao.getcoluna() - 1);
        while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().posicaoOcupada(p)) {
            mat[p.getlinha()][p.getcoluna()] = true;
            p.setcoluna(p.getcoluna() - 1);
        }
        if (getTabuleiro().posicaoExiste(p) && pecaOponente(p)) {
            mat[p.getlinha()][p.getcoluna()] = true;
        }

        // direita
        p.setValores(posicao.getlinha(), posicao.getcoluna() + 1);
        while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().posicaoOcupada(p)) {
            mat[p.getlinha()][p.getcoluna()] = true;
            p.setcoluna(p.getcoluna() + 1);
        }
        if (getTabuleiro().posicaoExiste(p) && pecaOponente(p)) {
            mat[p.getlinha()][p.getcoluna()] = true;
        }

        // baixo
        p.setValores(posicao.getlinha() + 1, posicao.getcoluna());
        while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().posicaoOcupada(p)) {
            mat[p.getlinha()][p.getcoluna()] = true;
            p.setlinha(p.getlinha() + 1);
        }
        if (getTabuleiro().posicaoExiste(p) && pecaOponente(p)) {
            mat[p.getlinha()][p.getcoluna()] = true;
        }

        return mat;
    }
}
