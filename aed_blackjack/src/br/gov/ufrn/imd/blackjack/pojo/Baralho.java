package br.gov.ufrn.imd.blackjack.pojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Baralho {

	private List<Carta> cartas;
	
	public Baralho() {
		this.cartas = new ArrayList<>();
	}

	public void criarBaralhoCompleto() {
		for (Naipe naipe : Naipe.values()) {
			for (Valor valor : Valor.values()) {
				this.cartas.add(new Carta(naipe, valor));
			}
		}
	}

	public void embaralhar() {
		List<Carta> tempBaralho = new ArrayList<>();
		Random random = new Random();
		int indiceRandomico = 0;
		int tamanhoOriginal = this.cartas.size();

		for (int i=0; i<tamanhoOriginal; i++) {
			indiceRandomico = random.nextInt((this.cartas.size()-1) + 1) + 0;
			tempBaralho.add(this.cartas.get(indiceRandomico));
			this.cartas.remove(indiceRandomico);
		}

		this.cartas = tempBaralho;
	}

	public void removerCarta(int indice) {
		this.cartas.remove(indice);
	}

	public Carta recuperarCarta(int indice) {
		return this.cartas.get(indice);
	}

	public void adicionarNovaCarta(Carta carta) {
		this.cartas.add(carta);
	}

	public void adicionarNovaCarta(Baralho origem) {
		this.cartas.add(origem.recuperarCarta(0));
		origem.removerCarta(0);
	}

	public String toString() {
		StringBuilder print = new StringBuilder();
		for (Carta carta : this.cartas) {
			print.append(carta.toString()).append("\n");
		}
		return print.toString();
	}

	public void moverTudoParaBaralho (Baralho destino) {
		int tamanhoDesseBaralho = this.cartas.size();
		for (int i = 0; i < tamanhoDesseBaralho; i++) {
			destino.adicionarNovaCarta(this.recuperarCarta(i));
		}
		
		for (int i=0; i < tamanhoDesseBaralho; i++) {
			this.removerCarta(0);
		}
	}
	
	public int tamanhoDoBaralho() {
		return this.cartas.size();
	}

	public int calcularValorTotal() {
		int valorTotal = 0;
		int aces = 0;
		for (Carta carta : this.cartas) {
			switch (carta.getValor()) {
			case DOIS : valorTotal +=2; break;
			case TRES : valorTotal +=3; break;
			case QUATRO : valorTotal +=4; break;
			case CINCO : valorTotal +=5; break;
			case SEIS : valorTotal +=6; break;
			case SETE : valorTotal +=7; break;
			case OITO : valorTotal +=8; break;
			case NOVE : valorTotal +=9; break;
			case DEZ : valorTotal +=10; break;
			case VALETE : valorTotal +=10; break;
			case DAMA : valorTotal +=10; break;
			case REI : valorTotal +=10; break;
			case ACE : aces +=1; break;
			}
		}

		for (int i=0; i<aces; i++) {
			if (valorTotal > 10) {
				valorTotal += 1;
			} else {
				valorTotal += 11;
			}
		}

		return valorTotal;
	}
}
