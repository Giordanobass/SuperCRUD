package crud.mapeamentobasico;

import org.junit.Assert;
import org.junit.Test;

import crud.entitymanager.EntityManagerTest;
import crud.model.Categoria;

public class EstrategiaChavePrimariaTest extends EntityManagerTest {
	
	
	@Test
	public void testEstrategiaAuto() {
		Categoria categoria = new Categoria();
		categoria.setNome("Eletrônicos");
		
		entityManager.getTransaction().begin();
		entityManager.persist(categoria);
		entityManager.getTransaction().commit();
		entityManager.clear();
		
		Categoria verificacao = entityManager.find(Categoria.class, categoria.getId());
		Assert.assertNotNull(verificacao);
	}

}
