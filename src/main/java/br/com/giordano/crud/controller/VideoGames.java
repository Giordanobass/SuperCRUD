package br.com.giordano.crud.controller;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.com.giordano.crud.enums.VideoGamesEnum;

@ManagedBean
@ApplicationScoped
public class VideoGames  {
	
	@Enumerated(EnumType.STRING)
	private VideoGamesEnum videoGames;
	
	public VideoGamesEnum[] carregarComboGames() {
	    return videoGames.values();
	}
	

}
