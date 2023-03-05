package crud.mapeamentobasico;

import org.junit.Assert;
import org.junit.Test;

import crud.entitymanager.EntityManagerTest;
import crud.enuns.SexoCliente;
import crud.model.Cliente;

public class MapeandoEnumeracoesTest extends EntityManagerTest {
	
	@Test
	public void testarEnum() {
		Cliente cliente = new Cliente();
		cliente.setNome("José Mineiro");
		cliente.setSexo(SexoCliente.MASCULINO);
		
		entityManager.getTransaction().begin();
		entityManager.persist(cliente);
		entityManager.getTransaction().commit();
		entityManager.clear();
		
		Cliente verificacao = entityManager.find(Cliente.class, cliente.getId());
		Assert.assertNotNull(verificacao);
	}

}
