package crud.mapeamentoavancado;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;
import java.util.Date;

import org.junit.Test;

import crud.entitymanager.EntityManagerTest;
import crud.enuns.StatusPedido;
import crud.model.Cliente;
import crud.model.ItemPedido;
import crud.model.ItemPedidoId;
import crud.model.NotaFiscal;
import crud.model.Pedido;
import crud.model.Produto;

public class MapsIdTest extends EntityManagerTest {

	@Test
	public void inserirPagamento() {
		Pedido pedido = entityManager.find(Pedido.class, 1);

		NotaFiscal notaFiscal = new NotaFiscal();
		notaFiscal.setPedido(pedido);
		notaFiscal.setDataEmissao(new Date());
		notaFiscal.setXml("<xml/>");

		entityManager.getTransaction().begin();
		entityManager.persist(notaFiscal);
		entityManager.getTransaction().commit();
		entityManager.clear();

		NotaFiscal verificacao = entityManager.find(NotaFiscal.class, notaFiscal.getId());
		assertNotNull(verificacao);
		assertEquals(pedido.getId(), verificacao.getId());
	}

	@Test
	public void inserirItemPedido() {
		Cliente cliente = entityManager.find(Cliente.class, 1);
		Produto produto = entityManager.find(Produto.class, 1);

		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);
		pedido.setDataCriacao(LocalDateTime.now());
		pedido.setStatus(StatusPedido.AGUARDANDO);
		pedido.setTotal(produto.getPreco());

		ItemPedido itemPedido = new ItemPedido();
		itemPedido.setId(new ItemPedidoId());
		itemPedido.setPedido(pedido);
		itemPedido.setProduto(produto);
		itemPedido.setPrecoProduto(produto.getPreco());
		itemPedido.setQuantidade(1);

		entityManager.getTransaction().begin();
		entityManager.persist(pedido);
		entityManager.persist(itemPedido);
		entityManager.getTransaction().commit();
		entityManager.clear();

		ItemPedido itemPedidoVerificacao = entityManager.find(ItemPedido.class,
				new ItemPedidoId(pedido.getId(), produto.getId()));
		assertNotNull(itemPedidoVerificacao);
	}

}
