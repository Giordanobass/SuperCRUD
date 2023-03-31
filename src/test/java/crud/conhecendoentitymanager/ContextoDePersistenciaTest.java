package crud.conhecendoentitymanager;

import java.math.BigDecimal;

import org.junit.Test;

import crud.entitymanager.EntityManagerTest;
import crud.model.Produto;

public class ContextoDePersistenciaTest extends EntityManagerTest {

	@Test
	public void usarContextoPersistencia() {

		entityManager.getTransaction().begin();

		Produto produto = entityManager.find(Produto.class, 1);
		produto.setPreco(new BigDecimal(100.0));

		Produto produto2 = new Produto();
//		produto2.setId(3);
		produto2.setNome("Caneca para café");
		produto2.setPreco(new BigDecimal(100.0));
		entityManager.merge(produto2);

		entityManager.flush();

		Produto produto3 = new Produto();
//		produto3.setId(4);
		produto3.setNome("Caneca para chá");
		produto3.setDescricao("Boa Caneca para chá");
		produto3.setPreco(new BigDecimal(10.0));
//		produto3.setCategorias(null);
//		produto3.setEstoque(null);
		entityManager.merge(produto3);

		entityManager.flush();

		produto3.setDescricao("alteração descrição");

		entityManager.getTransaction().commit();
	}

}
