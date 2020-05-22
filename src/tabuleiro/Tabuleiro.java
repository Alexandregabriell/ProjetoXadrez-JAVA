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
}
