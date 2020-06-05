package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Peao extends PecaXadrez {

    public Peao(Tabuleiro tabuleiro, Cor cor){
        super(tabuleiro, cor);
    }

    @Override
    public boolean[][] possivelMovimento() {
        boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao p = new Posicao(0, 0);

        if (getCor() == Cor.WHITE) {
            p.setValores(posicao.getlinha() - 1, posicao.getcoluna());
            if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().posicaoOcupada(p)) {
                mat[p.getlinha()][p.getcoluna()] = true;
            }
            p.setValores(posicao.getlinha() - 2, posicao.getcoluna());
            Posicao p2 = new Posicao(posicao.getlinha() - 1, posicao.getcoluna());
            if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().posicaoOcupada(p) && getTabuleiro().posicaoExiste(p2) && !getTabuleiro().posicaoOcupada(p2) && getMoveCount() == 0) {
                mat[p.getlinha()][p.getcoluna()] = true;
            }
            p.setValores(posicao.getlinha() - 1, posicao.getcoluna() - 1);
            if (getTabuleiro().posicaoExiste(p) && pecaOponente(p)) {
                mat[p.getlinha()][p.getcoluna()] = true;
            }
            p.setValores(posicao.getlinha() - 1, posicao.getcoluna() + 1);
            if (getTabuleiro().posicaoExiste(p) && pecaOponente(p)) {
                mat[p.getlinha()][p.getcoluna()] = true;
            }
        }
        else {
            p.setValores(posicao.getlinha() + 1, posicao.getcoluna());
            if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().posicaoOcupada(p)) {
                mat[p.getlinha()][p.getcoluna()] = true;
            }
            p.setValores(posicao.getlinha() + 2, posicao.getcoluna());
            Posicao p2 = new Posicao(posicao.getlinha() - 1, posicao.getcoluna());
            if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().posicaoOcupada(p) && getTabuleiro().posicaoExiste(p2) && !getTabuleiro().posicaoOcupada(p2) && getMoveCount() == 0) {
                mat[p.getlinha()][p.getcoluna()] = true;
            }
            p.setValores(posicao.getlinha() + 1, posicao.getcoluna() - 1);
            if (getTabuleiro().posicaoExiste(p) && pecaOponente(p)) {
                mat[p.getlinha()][p.getcoluna()] = true;
            }
            p.setValores(posicao.getlinha() + 1, posicao.getcoluna() + 1);
            if (getTabuleiro().posicaoExiste(p) && pecaOponente(p)) {
                mat[p.getlinha()][p.getcoluna()] = true;
            }
        }
        return mat;
    }

    @Override
    public String toString() {
        return "P";
    }
}
