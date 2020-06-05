package xadrez;

import tabuleiro.Tabuleiro;
import tabuleiro.TabuleiroExcecao;

//Criando uma excecao na camada de xadrez
public class excecaoXadrez extends TabuleiroExcecao {
    private static final long serialVersionUID = 1L;

    public excecaoXadrez(String msg) {
        super(msg);
    }
}
