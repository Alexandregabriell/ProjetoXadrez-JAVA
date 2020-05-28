package xadrez;
//Criando uma excecao na camada de xadrez
public class excecaoXadrez extends RuntimeException{
    private static final long serialVersioUID = 1L;// numero de serial padrao

    public  excecaoXadrez(String msg){
        super(msg);
    }
}
