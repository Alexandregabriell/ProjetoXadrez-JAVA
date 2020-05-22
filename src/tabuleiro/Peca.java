package tabuleiro;

public class Peca {
    protected Posicao posicao;
    private Tabuleiro tabuleiro;

    public Peca(Tabuleiro tabuleiro){
        this.tabuleiro = tabuleiro;
        posicao = null;
    }

    protected Tabuleiro getTabuleiro(){ //só vai ser usado pela camada tabuleiro
        return tabuleiro;
    }
}
