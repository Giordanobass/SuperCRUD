package br.com.giordano.crud.enums;

public enum AluguelJogosEnum {
	
	ALUGADO("Alugual disponível"),
	NAOALUGADO("Aluguel indisponível");
	
	
	private String label;
	
	private AluguelJogosEnum(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}

}
