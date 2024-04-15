package crud.mapeamentoavancado;

import static org.junit.Assert.*;

import org.junit.Test;

import crud.entitymanager.EntityManagerTest;
import crud.model.Cliente;

public class PropriedadesTransientesTest extends EntityManagerTest {

	@Test
	public void validarPrimeiroNome() {
		Cliente cliente = entityManager.find(Cliente.class, 1);
		
		assertEquals("Fernando", cliente.getPrimeiroNome());
	}

}
