package br.com.giordano.crud.enums;

public enum JogosEnum {
	
	SONIC("Sonic the hedgehog"),
	MARIO("Mario Kart"),
	BLACKTORN("Blacktorn");
	
	private String label;
	
	private JogosEnum(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
}
