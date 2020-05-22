package tabuleiro;

public class Tabuleiro {
    private int linhas;
    private int colunas;
    private Peca[][] pecas; // matriz de PEÇAS

//construtor

    public Tabuleiro(int linhas, int colunas){
        this.linhas = linhas;
        this.colunas = colunas;
        pecas = new Peca[linhas][colunas]; // matriz vai ser instanciada pela qtd de linhas e colunas informadas
    }

//retornando uma peça por vez

    public int getLinhas(){
        return linhas;
    }

    public void setLinhas(int linhas){
        this.linhas = linhas;
    }

    public int getColunas(){
        return colunas;
    }

    public void setColunas(int colunas){
        this.colunas = colunas;
    }

    public Peca peca(int linha, int coluna){
        return pecas[linha][coluna];
    }

    public Peca peca(Posicao posicao){
        return pecas[posicao.getlinha()][posicao.getcoluna()];
    }

    // colocando pecas no tabuleiro
    public void entradaPeca(Peca peca, Posicao posicao){
        pecas[posicao.getlinha()][posicao.getcoluna()] = peca;
        peca.posicao = posicao;
    }
}
