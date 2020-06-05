package xadrez;

import tabuleiro.Posicao;

public class PosicaoXadrez {
    private char coluna;
    private int linha;

    public PosicaoXadrez(char coluna, int linha) {
        //programacao defensiva
        if (coluna < 'a' || coluna > 'h' || linha < 1 || linha > 8){
            throw new ExcecaoXadrez(" ERRO NA POSICAO DE XADREZ - VALORES VALIDOS DE A1 ATÉ H8.");
        }
        this.coluna = coluna;
        this.linha = linha;
    }

    public char getColuna() {
        return coluna;
    }

    public int getLinha() {
        return linha;
    }

    //metodo para converter posicao do xadrez, para a posicao normal
    //linha da matriz=8 vai ser menos a linha do xadrez
    //coluna do xadrez menos 'a'
    //a-a=0
    //b-a=1
    //c-a=2...
    protected Posicao toPosicao(){
        return new Posicao(8 - linha, coluna - 'a');
    }

    //implementando posicao inversa da matriz
    protected static PosicaoXadrez fromPosicao(Posicao posicao){// retornando  a formula inversa de Posicao
        return new PosicaoXadrez((char)('a' + posicao.getcoluna()), 8 - posicao.getlinha());
    }

    //imprimindo posicao do xadrez na ordem
    @Override //concatenacao de strings ("") automatica
    public String toString(){
        return " " + coluna + linha;
    }
}
