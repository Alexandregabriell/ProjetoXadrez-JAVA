package tabuleiro;

public class Tabuleiro {
    private int linhas;
    private int colunas;
    private Peca[][] pecas; // matriz de PEÇAS

//construtor

    public Tabuleiro(int linhas, int colunas){
        if (linhas < 1 || colunas < 1){ //programacao defensivas ao criar o tabuleiro
            throw new TabuleiroExcecao("ERRO criando o tabuleiro: É necessario que haja pelo menos 1 linha e 1 coluna");
        }
        this.linhas = linhas;
        this.colunas = colunas;
        pecas = new Peca[linhas][colunas]; // matriz vai ser instanciada pela qtd de linhas e colunas informadas
    }

//retornando uma peça por vez

    public int getLinhas(){
        return linhas;
    }

    public int getColunas(){
        return colunas;
    }

    //verificando se posicao existe
    public Peca peca(int linha, int coluna){
        if(!posicaoExiste(linha, coluna)){
            throw new TabuleiroExcecao("Posicao não EXISTE");
        }
        return pecas[linha][coluna];
    }

    public Peca peca(Posicao posicao){
        if(!posicaoExiste(posicao)){
            throw new TabuleiroExcecao("Posicao não EXISTE");
        }
        return pecas[posicao.getlinha()][posicao.getcoluna()];
    }

    // colocando pecas no tabuleiro
    public void entradaPeca(Peca peca, Posicao posicao){
        if (posicaoOcupada(posicao)) {//verificando se ja existe uma peca nessa posicao
            throw new TabuleiroExcecao("POSICÃO " + posicao + " JÁ EM USO ");
        }
        pecas[posicao.getlinha()][posicao.getcoluna()] = peca;
        peca.posicao = posicao;
    }

    // metodo removenco peca
    public Peca removePeca(Posicao posicao){
        if(!posicaoExiste(posicao)) {
            throw new TabuleiroExcecao("POSICÃO NÃO EXISTE NO TABULEIRO");
        }
        if (peca(posicao) == null){
            return null;
        }
        // removendo a peca
        Peca aux = peca(posicao);
        aux.posicao = null; // peca retirada representado pelo null
        // acessando matriz de pecas
        pecas[posicao.getlinha()][posicao.getcoluna()] = null; // posicao que estou removendo a peca vai ser null
        return aux; // onde contem a peca retirada
    }

    private boolean posicaoExiste(int linha, int coluna){ //codicao para ver se uma posicao existe dentro do tabuleiro
        return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
    }

    public boolean posicaoExiste(Posicao posicao){
        return posicaoExiste(posicao.getlinha(), posicao.getcoluna());
    }

    public boolean posicaoOcupada(Posicao posicao){ //codicao para verificar se existe uma peca nessa posicao
        if(!posicaoExiste(posicao)){
            throw new TabuleiroExcecao("Posicao não EXISTE");
        }
        return peca(posicao) != null;
    }
}
