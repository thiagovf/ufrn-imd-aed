package br.gov.ufrn.imd.blackjack;

import java.util.Scanner;

import br.gov.ufrn.imd.blackjack.pojo.Baralho;

public class Blackjack {

	public static void main(String[] args) {
		System.out.println("Bem vindo ao Vinte-Um");

		Baralho morto = new Baralho();
		morto.criarBaralhoCompleto();
		morto.embaralhar();

		Baralho jogadorCartas = new Baralho();
		Baralho mesaCartas = new Baralho();
		jogar(morto, jogadorCartas, mesaCartas);
	}

	private static void jogar(Baralho morto, Baralho jogadorCartas, Baralho mesaCartas) {
		Scanner userInput = new Scanner(System.in);
		boolean acabarRodada = false;
		
		//duas cartas pro jogador
		jogadorCartas.adicionarNovaCarta(morto);
		jogadorCartas.adicionarNovaCarta(morto);

		//duas cartas pra mesa
		mesaCartas.adicionarNovaCarta(morto);
		mesaCartas.adicionarNovaCarta(morto);

		while (true) {
			System.out.println("Sua mão: " + jogadorCartas.toString());
			System.out.println("Valor total na sua mão: " + jogadorCartas.calcularValorTotal());
			
			System.out.println("Mão da mesa: " + mesaCartas.recuperarCarta(0).toString() + " e [oculto]");
			
			System.out.println("Você quer (1)continuar ou (2)parar?");
			int resposta = userInput.nextInt();
			if (resposta == 1) {
				jogadorCartas.adicionarNovaCarta(morto);
				System.out.println("Você pegou um:" + jogadorCartas.recuperarCarta(jogadorCartas.tamanhoDoBaralho()-1).toString());
				int valorTotal = jogadorCartas.calcularValorTotal();
				if (valorTotal > 21) {
					System.out.println("A mesa sempre ganha! Seu valor total é: " + valorTotal);
					acabarRodada = true;
					break;
				}
			} else if (resposta == 2) {
				break;
			}
		}

		System.out.println("Cartas da Mesa: " + mesaCartas.toString());

		// verifica se a mesa ganhou.
		if (mesaCartas.calcularValorTotal() > jogadorCartas.calcularValorTotal() && !acabarRodada) {
			System.out.println("A mesa sempre ganha! Total da mesa: " + mesaCartas.calcularValorTotal() + ". Seu total: " + jogadorCartas.calcularValorTotal());
			acabarRodada = true;
		}

		while (mesaCartas.calcularValorTotal() < 17 && !acabarRodada) {
			mesaCartas.adicionarNovaCarta(morto);
			System.out.println("Carta da mesa:" + mesaCartas.recuperarCarta(mesaCartas.tamanhoDoBaralho()-1).toString());
		}

		System.out.println("Total das cartas da mesa:" + mesaCartas.calcularValorTotal());

		// verifica se a mesa passou de 21.
		if (mesaCartas.calcularValorTotal() > 21 && !acabarRodada) {
			System.out.println("Dessa vez, você ganhou!");
			acabarRodada = true;
		}

		// verifica se foi empate.
		if (mesaCartas.calcularValorTotal() == jogadorCartas.calcularValorTotal() && !acabarRodada) {
			System.out.println("Dessa vez, deu empate..");
			acabarRodada = true;
		}
		
		// verifica se você ganhou.
		if (mesaCartas.calcularValorTotal() < jogadorCartas.calcularValorTotal() && !acabarRodada) {
			System.out.println("Dessa vez, você ganhou!");
			acabarRodada = true;
		} else if(!acabarRodada) {
			System.out.println("A mesa sempre ganha!");
		}

		jogadorCartas.moverTudoParaBaralho(morto);
		mesaCartas.moverTudoParaBaralho(morto);
		System.out.println("Fim do jogo.");
		userInput.close();
	}
}
