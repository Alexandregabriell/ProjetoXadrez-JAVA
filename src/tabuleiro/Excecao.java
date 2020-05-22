package tabuleiro;

class TempoExcecao extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public TempoExcecao(String msg){
        super(msg);
    }

}
