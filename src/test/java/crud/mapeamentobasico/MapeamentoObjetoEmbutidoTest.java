package crud.mapeamentobasico;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import crud.entitymanager.EntityManagerTest;
import crud.enuns.StatusPagamento;
import crud.enuns.StatusPedido;
import crud.model.Cliente;
import crud.model.EnderecoEntregaPedido;
import crud.model.ItemPedido;
import crud.model.ItemPedidoId;
import crud.model.NotaFiscal;
import crud.model.PagamentoCartao;
import crud.model.Pedido;
import crud.model.Produto;

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

		Pedido pedido = entityManager.find(Pedido.class, 1);
		Produto produto = entityManager.find(Produto.class, 1);

		List<Pedido> listaPedidos = new ArrayList<>();
		listaPedidos.add(pedido);

		Cliente cliente = entityManager.find(Cliente.class, 1);
//		cliente.setNome("Aroldo");
//		cliente.setSexo(SexoCliente.MASCULINO);
		cliente.setPedidos(listaPedidos);

		List<ItemPedido> listaItemPedido = new ArrayList<>();
		ItemPedido item = new ItemPedido();
		item.setId(new ItemPedidoId());
		item.setPedido(pedido);
		item.setProduto(produto);
		item.setPrecoProduto(new BigDecimal(10.234));
		item.setQuantidade(10);
		listaItemPedido.add(item);

		NotaFiscal notaFiscal = new NotaFiscal();
		notaFiscal.setPedido(pedido);
		notaFiscal.setXml("xml_Produto");
		notaFiscal.setDataEmissao(new Date());

		PagamentoCartao pagamentoCartao = new PagamentoCartao();
		pagamentoCartao.setPedido(pedido);
		pagamentoCartao.setStatus(StatusPagamento.PROCESSANDO);
		pagamentoCartao.setNumero("AB123456");

		Pedido pedido2 = new Pedido();
		pedido2.setId(3);
		pedido2.setCliente(cliente);
		pedido2.setItens(listaItemPedido);
		pedido2.setDataCriacao(LocalDateTime.now());
		pedido2.setDataConclusao(LocalDateTime.parse("2022-12-03T10:15:30"));
		pedido2.setDataUltimaAtualizacao(LocalDateTime.now());
		pedido2.setNotaFiscal(notaFiscal);
		pedido2.setTotal(new BigDecimal(1000));
		pedido2.setStatus(StatusPedido.AGUARDANDO);
		pedido2.setPagamentoCartao(pagamentoCartao);
		pedido2.setEnderecoEntrega(entregaPedido);

		entityManager.getTransaction().begin();
		entityManager.persist(pedido2);
		entityManager.getTransaction().commit();
		entityManager.clear();

		Pedido verificacao = entityManager.find(Pedido.class, pedido.getId());
		Assert.assertNotNull(verificacao);
		Assert.assertNotNull(verificacao.getEnderecoEntrega());
		Assert.assertNotNull(verificacao.getEnderecoEntrega().getCep());
	}

}
