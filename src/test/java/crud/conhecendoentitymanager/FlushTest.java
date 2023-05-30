package crud.conhecendoentitymanager;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import crud.entitymanager.EntityManagerTest;
import crud.enuns.StatusPedido;
import crud.model.Pedido;

public class FlushTest extends EntityManagerTest {

	@Test(expected = Exception.class)
	public void chamarFlush() {
		
		try {
			entityManager.getTransaction().begin();
			
			Pedido pedido = entityManager.find(Pedido.class, 1);
			pedido.setStatus(StatusPedido.PAGO);
			
			// Força o JPA a sincronizar o que tem na memória. Commit faz internamente
//			entityManager.flush(); 
			
			
			if(pedido.getPagamentoCartao() == null) {
				throw new RuntimeException("Pedido ainda não foi pago.");
			}
			
//			Uma consulta JPQL obriga o JPA a sincronizar o que ele tem na memória
			Pedido pedidoPago = entityManager
					.createQuery("select p from Pedido p where p.id = 1", Pedido.class)
					.getSingleResult();
			assertEquals(pedido.getStatus(), pedidoPago.getStatus());

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw e;
		}
		
		
	}

}
