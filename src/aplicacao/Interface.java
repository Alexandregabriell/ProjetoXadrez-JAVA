package aplicacao;


import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.posicaoXadrez;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Interface {
    // codigos de cores para imprimir
    // https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
    // cores do texto
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    //cores do fundo
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    // https://stackoverflow.com/questions/2979383/java-clear-the-console
    // limpar a tela
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // metodo para ler posicao do usuario
    public static posicaoXadrez lendoPosicaoXadrez(Scanner sc) { // Recebe como argumento o scanner instaciado do programa principal
        try { // evitando problema de formato
            // lendo posicao do xadrez
            String s = sc.nextLine();
            char coluna = s.charAt(0);
            int linha = Integer.parseInt(s.substring(1));// recortando string apartir da posicao 1
            return new posicaoXadrez(coluna, linha);
        }
        catch (RuntimeException e){ // excecao, erro entrada de dados
            throw new InputMismatchException(" ERRO NA POSICAO DE XADREZ - VALORES VALIDOS DE A1 ATÉ H8.");
        }
    }

    public static void imprimirPartida(PartidaXadrez partidaXadrez, List<PecaXadrez> capturada) {
        imprimirTabuleiro(partidaXadrez.getPecas());// imprimindo o tabuleiro
        System.out.println();
        imprimirPecasCapturadas(capturada); // imprimindo pecas capturadas
        System.out.println("Turno : " + partidaXadrez.getTurno());
        System.out.println("Aguardando o Jogador : " + partidaXadrez.getJogadorAtual());
    }

        public static void imprimirTabuleiro (PecaXadrez[][] pecas){
            for (int i = 0; i < pecas.length; i++) { //percorrer a matriz para imprimir tabuleiro
                System.out.print((8 - i) + " ");
                for (int j = 0; j < pecas.length; j++) {
                    imprimirPeca(pecas[i][j], false); // false para que nenhuma tenha o fundo colorido
                }
                System.out.println(); // quebra de linha
            }
            System.out.println("  a b c d e f g h");
        }

    public static void imprimirTabuleiro(PecaXadrez[][] pecas, boolean[][] possivelMovimento) { // matriz de movimentos possiveis
        for (int i = 0; i < pecas.length; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < pecas.length; j++) {
                imprimirPeca(pecas[i][j], possivelMovimento[i][j]); // imprindo o fundo colorido dependendo da variavel[][]
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

        //imprimindo uma peça, verificando sua  cor
        private static void imprimirPeca (PecaXadrez peca, boolean fundo) {
            if (fundo) { // testando se a variavel(fundo) é verdadeira
                System.out.print(ANSI_BLUE_BACKGROUND);
            }

            if (peca == null) {
                System.out.print("-" + ANSI_RESET);
            } else {
                if (peca.getCor() == Cor.WHITE) {
                    System.out.print(ANSI_WHITE + peca + ANSI_RESET);
                } else {
                    System.out.print(ANSI_YELLOW + peca + ANSI_RESET);
                }
            }
            System.out.print(" ");
        }
        private static void imprimirPecasCapturadas(List<PecaXadrez> capturada){ // imprimindo lista de pecas capturadas
            List<PecaXadrez>  white = capturada.stream().filter(x -> x.getCor() == Cor.WHITE).collect(Collectors.toList());
            List<PecaXadrez> black = capturada.stream().filter(x -> x.getCor() == Cor.BLACK).collect(Collectors.toList());
            System.out.println("Pecas Capturadas:");
            System.out.print("Brancas: ");
            System.out.print(ANSI_WHITE);
            System.out.println(Arrays.toString(white.toArray()));//imprimindo a lista
            System.out.print(ANSI_RESET);
            System.out.print("Pretas: ");
            System.out.print(ANSI_YELLOW);
            System.out.println(Arrays.toString(black.toArray()));
            System.out.print(ANSI_RESET);
        }
    }
