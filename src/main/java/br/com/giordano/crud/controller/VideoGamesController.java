package br.com.giordano.crud.controller;

import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.com.giordano.crud.enums.AluguelJogosEnum;
import br.com.giordano.crud.enums.JogosEnum;
import br.com.giordano.crud.enums.VideoGamesEnum;

@ManagedBean
@ApplicationScoped
public class VideoGamesController  {
	
	@Enumerated(EnumType.STRING)
	private VideoGamesEnum videoGames;
	
	@Enumerated(EnumType.STRING)
	private JogosEnum jogosEnum;
	
	@Enumerated(EnumType.STRING)
	private AluguelJogosEnum aluguelJogosEnum;
	
	public JogosEnum[] carregarComboJogos() {
		return jogosEnum.values();
	}
	
	public AluguelJogosEnum[] carregarComboAluguel() {
		return aluguelJogosEnum.values();
	}
	
	public VideoGamesEnum[] carregarComboGames() {
	    return videoGames.values();
	}
	
	public List<VideoGamesEnum> getCarregarComboGames(){
		return Arrays.asList(VideoGamesEnum.values());
	}

}
