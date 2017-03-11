package br.gov.ufrn.imd.blackjack.pojo;

public class Carta {

	private Naipe naipe;
	private Valor valor;

	public Carta(Naipe naipe, Valor valor) {
		this.naipe = naipe;
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Carta [naipe=" + naipe + ", valor=" + valor + "]";
	}

	public Valor getValor() {
		return valor;
	}

}
