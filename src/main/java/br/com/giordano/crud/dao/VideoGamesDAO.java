package br.com.giordano.crud.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.giordano.crud.entidade.VideoGames;
import br.com.giordano.crud.utils.JPAUtil;

public class VideoGamesDAO {
    
    EntityManager entityManager = JPAUtil.geEntityManagerFactory().createEntityManager();
    
    public void incluir(VideoGames videoGames) {
	entityManager.getTransaction().begin();
	entityManager.persist(videoGames);
	entityManager.getTransaction().commit();
	//JPAUtil.shutdown();
}

public void alterar(VideoGames videoGames) {
	entityManager.getTransaction().begin();
	entityManager.merge(videoGames);
	entityManager.getTransaction().commit();
	//JPAUtil.shutdown();
}

public VideoGames buscar(Long id) {
    VideoGames videoGames = new VideoGames();
    videoGames = entityManager.find(VideoGames.class, id);
	//JPAUtil.shutdown();
	return videoGames;
}

public List<VideoGames> obterVideoGames(){
	List<VideoGames> listaVideoGames = new ArrayList<>();
	Query query = entityManager.createQuery("SELECT c FROM Cliente c");
	listaVideoGames = query.getResultList();
	return listaVideoGames;
}

public void remover(Long id) {
    VideoGames videoGames = new VideoGames();
    videoGames = entityManager.find(VideoGames.class, id);
	entityManager.getTransaction().begin();
	entityManager.remove(videoGames);
	entityManager.getTransaction().commit();
	
}
    
    
}
