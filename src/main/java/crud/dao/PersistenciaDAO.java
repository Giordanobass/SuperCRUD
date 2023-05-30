package crud.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@SuppressWarnings("unused")
public class PersistenciaDAO<E> {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Class<E> entidade;
	
	static {
		try {
			emf = Persistence.createEntityManagerFactory("jpa_exemplo");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public PersistenciaDAO(Class<E> entidade) {
		this.entidade = entidade;
		em = emf.createEntityManager();
	}
}
