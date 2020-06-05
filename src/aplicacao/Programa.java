package aplicacao;

import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.ExcecaoXadrez;
import xadrez.PosicaoXadrez;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Programa {

    public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    PartidaXadrez partidaXadrez  = new PartidaXadrez();
    List<PecaXadrez> capturada = new ArrayList<>();

		while (!partidaXadrez.getCheckMate()) {
		    try { // trantando excecao
                Interface.clearScreen();
                // imprimir tabuleiro na tela
                Interface.imprimirPartida(partidaXadrez, capturada);
                System.out.println();
                System.out.print("Origem: "); // usuario entra com a posicao de origem
                PosicaoXadrez origem = Interface.lendoPosicaoXadrez(sc);

                boolean[][] possivelMovimento = partidaXadrez.possivelMovimento(origem);
                Interface.clearScreen(); // limpar tela
                Interface.imprimirTabuleiro(partidaXadrez.getPecas(), possivelMovimento); // sobrecarga - imprindo tabuleiro para posicoes possiveis da peca
                System.out.println();
                System.out.print("Destino: "); // usuario entra com a  posicao de destino
                PosicaoXadrez destino = Interface.lendoPosicaoXadrez(sc);

                PecaXadrez pecaCapturada = partidaXadrez.movimentoXadrez(origem, destino);

                if (pecaCapturada != null) { // testando se teve peca capturada depois de um movimento
                    capturada.add(pecaCapturada);
                }
		    }
		    catch (ExcecaoXadrez e) {// faca isso caso ocorra essa excecao
                System.out.println(e.getMessage());
                sc.nextLine();
		    }
            catch (InputMismatchException e) {// faca isso caso ocorra essa excecao
                System.out.println(e.getMessage());
                sc.nextLine();
            }
		}

        Interface.clearScreen();
        Interface.imprimirPartida(partidaXadrez, capturada);
    }
}

