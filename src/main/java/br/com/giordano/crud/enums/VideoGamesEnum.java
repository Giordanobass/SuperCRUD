package br.com.giordano.crud.enums;

public enum VideoGamesEnum {
	
	XBOXONE("Xbox One"),
	PS4("Ps4"),
	WIIU("Wii U");
	
	private String label;
	
	private VideoGamesEnum(String label) {
		this.label = label;
	}
	
	public String getLabel() {
        return label;
    }

}
