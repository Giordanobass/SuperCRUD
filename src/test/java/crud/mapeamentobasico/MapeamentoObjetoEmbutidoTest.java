package crud.mapeamentobasico;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;

import crud.entitymanager.EntityManagerTest;
import crud.enuns.StatusPedido;
import crud.model.EnderecoEntregaPedido;
import crud.model.Pedido;

public class MapeamentoObjetoEmbutidoTest extends EntityManagerTest {

	@Test
	public void testAnalisarMapeamentoObjetoEmbutido() {
		
		EnderecoEntregaPedido entregaPedido = new EnderecoEntregaPedido();
		entregaPedido.setCep("72922351");
		entregaPedido.setLogradouro("Rua Catalão");
		entregaPedido.setNumero("3a2");
		entregaPedido.setBairro("Jardim America 2");
		entregaPedido.setCidade("Aguas Lindas GO");
		entregaPedido.setEstado("Goiás");
		
		Pedido pedido = new Pedido();
		pedido.setDataPedido(LocalDateTime.now());
		pedido.setDataConclusao(LocalDateTime.parse("2022-12-03T10:15:30"));
		pedido.setStatus(StatusPedido.AGUARDANDO);
		pedido.setTotal(new BigDecimal(1000));
		pedido.setEnderecoEntrega(entregaPedido);
		
		entityManager.getTransaction().begin();
		entityManager.persist(pedido);
		entityManager.getTransaction().commit();
		entityManager.clear();
		
		Pedido verificacao = entityManager.find(Pedido.class, pedido.getId());
		Assert.assertNotNull(verificacao);
		Assert.assertNotNull(verificacao.getEnderecoEntrega());
		Assert.assertNotNull(verificacao.getEnderecoEntrega().getCep());
	}

}
