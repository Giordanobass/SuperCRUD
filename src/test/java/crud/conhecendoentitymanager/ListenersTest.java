package crud.conhecendoentitymanager;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import crud.entitymanager.EntityManagerTest;
import crud.enuns.StatusPedido;
import crud.model.Cliente;
import crud.model.Pedido;
import crud.model.Produto;

public class ListenersTest extends EntityManagerTest {
	
	@Test
	public void carregarEntidades() {
		Produto produto = entityManager.find(Produto.class, 1);
		Pedido pedido = entityManager.find(Pedido.class, 1);
		System.out.println(produto);
		System.out.println(pedido);
	}

	@Test
	public void acionarCallbacks() {
		Cliente cliente = entityManager.find(Cliente.class, 1);
		
		Pedido pedido = new Pedido();
		
		pedido.setCliente(cliente);
		pedido.setStatus(StatusPedido.AGUARDANDO);
		
		entityManager.getTransaction().begin();
		
		entityManager.persist(pedido);
		entityManager.flush();
		
		pedido.setStatus(StatusPedido.PAGO);

		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Pedido verifica = entityManager.find(Pedido.class, pedido.getId());
		assertNotNull(verifica.getDataCriacao());
		assertNotNull(verifica.getDataUltimaAtualizacao());
		
	}

}
