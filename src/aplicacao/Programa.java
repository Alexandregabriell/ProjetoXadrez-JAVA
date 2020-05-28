package aplicacao;

import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.posicaoXadrez;

import java.util.Scanner;

public class Programa {

    public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    PartidaXadrez partidaXadrez  = new PartidaXadrez();

		while (true) {
                Interface.imprimirTabuleiro(partidaXadrez.getPecas()); // imprimir tabuleiro na tela
                System.out.println();
                System.out.print("Origem: "); // usuario entra com a posicao de origem
                posicaoXadrez origem = Interface.lendoPosicaoXadrez(sc);

                System.out.println();
                System.out.print("Destino: "); // usuario entra com a  posicao de destino
                posicaoXadrez destino = Interface.lendoPosicaoXadrez(sc);

                PecaXadrez capturaPeca = partidaXadrez.movimentoXadrez(origem, destino);
		}
    }
}

