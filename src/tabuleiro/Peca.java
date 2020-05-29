package tabuleiro;

public abstract class Peca {
    protected Posicao posicao;
    private Tabuleiro tabuleiro;

    public Peca(Tabuleiro tabuleiro){
        this.tabuleiro = tabuleiro;
        posicao = null;
    }

    protected Tabuleiro getTabuleiro(){ //só vai ser usado pela camada tabuleiro
        return tabuleiro;
    }
    public abstract boolean[][] possivelMovimento(); // criando uma matriz de valor boolean, com posicoes falso, sendo vdd onde as pecas possam se mover

    public boolean possivelMovimento(Posicao posicao) { // verificando se é possivel se mover para determinada posicao
        return possivelMovimento()[posicao.getlinha()][posicao.getcoluna()]; // metodo concreto utilizando um metodo abstrato
    }

    public boolean existeMovimentoPossivel() { // verificando se ha movimento possivel para a peca escolhida ou se esta travada
        boolean[][] mat = possivelMovimento();
        for (int i=0; i<mat.length; i++) {
            for (int j=0; j<mat.length; j++) {
                if (mat[i][j]) { // testando se extiste possivel movimento
                    return true;
                }
            }
        }
        return false;
    }
}
