package xadrez;

import tabuleiro.Tabuleiro;
import tabuleiro.TabuleiroExcecao;

//Criando uma excecao na camada de xadrez
public class ExcecaoXadrez extends TabuleiroExcecao {
    private static final long serialVersionUID = 1L;

    public ExcecaoXadrez(String msg) {
        super(msg);
    }
}
