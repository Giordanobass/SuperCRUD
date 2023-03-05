package crud.iniciandojpa;

import org.junit.Assert;
import org.junit.Test;

import crud.entitymanager.EntityManagerTest;
import crud.model.Cliente;

public class PrimeiroCrudTest extends EntityManagerTest {

	@Test
	public void inserirRegistro(){
		Cliente cliente = new Cliente();
		cliente.setNome("Joanna");
		
		entityManager.getTransaction().begin();
		entityManager.persist(cliente);
		entityManager.getTransaction().commit();
		entityManager.clear();
		
		Cliente verificaCliente = entityManager.find(Cliente.class, cliente.getId());
		Assert.assertNotNull(verificaCliente);
	}
	
	@Test
	public void buscarRegistro() {
		Cliente cliente = entityManager.find(Cliente.class, 1);
		
		Assert.assertNotNull(cliente);
		
	}
	
	@Test
	public void editarRegistro() {
		Cliente cliente = entityManager.find(Cliente.class, 1);
		
		entityManager.getTransaction().begin();
		cliente.setNome("Joanna");
		entityManager.getTransaction().commit();
		entityManager.clear();
		
		Cliente verificaCliente = entityManager.find(Cliente.class, cliente.getId());
		Assert.assertEquals("Joanna",verificaCliente.getNome());
	}
	
	@Test
	public void removeRegistro() {
		Cliente cliente = entityManager.find(Cliente.class, 2);
		
		entityManager.getTransaction().begin();
		entityManager.remove(cliente);
		entityManager.getTransaction().commit();
		entityManager.clear();
		
		Cliente verificaCliente = entityManager.find(Cliente.class, 2);
		Assert.assertNull(verificaCliente);
		
	}
}
