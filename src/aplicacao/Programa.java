package aplicacao;

import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.excecaoXadrez;
import xadrez.posicaoXadrez;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Programa {

    public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    PartidaXadrez partidaXadrez  = new PartidaXadrez();

		while (true) {
		    try { // trantando excecao
                Interface.clearScreen();
                Interface.imprimirTabuleiro(partidaXadrez.getPecas()); // imprimir tabuleiro na tela
                System.out.println();
                System.out.print("Origem: "); // usuario entra com a posicao de origem
                posicaoXadrez origem = Interface.lendoPosicaoXadrez(sc);

                boolean[][] possivelMovimento = partidaXadrez.possivelMovimento(origem);
                Interface.clearScreen(); // limpar tela
                Interface.imprimirTabuleiro(partidaXadrez.getPecas(), possivelMovimento); // sobrecarga - imprindo tabuleiro para posicoes possiveis da peca
                System.out.println();
                System.out.print("Destino: "); // usuario entra com a  posicao de destino
                posicaoXadrez destino = Interface.lendoPosicaoXadrez(sc);

                PecaXadrez capturaPeca = partidaXadrez.movimentoXadrez(origem, destino);
            }
		    catch (excecaoXadrez e) {// faca isso caso ocorra essa excecao
                System.out.println(e.getMessage());
                sc.nextLine();
		    }
            catch (InputMismatchException e) {// faca isso caso ocorra essa excecao
                System.out.println(e.getMessage());
                sc.nextLine();
            }
		}
    }
}

