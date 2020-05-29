package xadrez;


import tabuleiro.Peca;
import tabuleiro.Tabuleiro;

public abstract class PecaXadrez extends Peca { //subclasse de Peca
        private Cor cor; //atributo da classe

//contrutor
public PecaXadrez(Tabuleiro tabuleiro, Cor cor){
        super(tabuleiro); // chamada para o construtor da super classe
        this.cor = cor;
        }

public Cor getCor(){
        return cor;
        }

public void setCor(Cor cor){
        this.cor = cor;
        }
}