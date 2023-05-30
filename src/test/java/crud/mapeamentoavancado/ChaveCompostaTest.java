package crud.mapeamentoavancado;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;

import org.junit.Test;

import crud.entitymanager.EntityManagerTest;
import crud.enuns.StatusPedido;
import crud.model.Cliente;
import crud.model.ItemPedido;
import crud.model.ItemPedidoId;
import crud.model.Pedido;
import crud.model.Produto;

public class ChaveCompostaTest extends EntityManagerTest {

	@Test
	public void testSalvaItem() {
		entityManager.getTransaction().begin();
		Cliente cliente = entityManager.find(Cliente.class, 1);
		Produto produto = entityManager.find(Produto.class, 1);
		
		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);
		pedido.setDataCriacao(LocalDateTime.now());
		pedido.setStatus(StatusPedido.AGUARDANDO);
		pedido.setTotal(produto.getPreco());
		
		entityManager.persist(pedido);
		entityManager.flush();
		
		ItemPedido itemPedido = new ItemPedido();
		itemPedido.setId(new ItemPedidoId(pedido.getId(), produto.getId()));
		itemPedido.setPedido(pedido);
		itemPedido.setProduto(produto);
		itemPedido.setPrecoProduto(produto.getPreco());
		itemPedido.setQuantidade(1);
		
		entityManager.persist(itemPedido);
		entityManager.getTransaction().commit();
		entityManager.clear();
		
		Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
		assertNotNull(pedidoVerificacao);
		assertFalse(pedidoVerificacao.getItens().isEmpty());
	}
	
	@Test
	public void buscarItem() {
		ItemPedido itemPedido = entityManager.find(ItemPedido.class, new ItemPedidoId(1, 1));
		assertNotNull(itemPedido);
	}

}
