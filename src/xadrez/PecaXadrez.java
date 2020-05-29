package xadrez;


import tabuleiro.Peca;
import tabuleiro.Posicao;
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

        protected boolean pecaOponente(Posicao posicao) {
                PecaXadrez p = (PecaXadrez) getTabuleiro().peca(posicao);
                return p != null && p.getCor() != cor; // verificando se cor da peca do oponente é a mesma
        }
}