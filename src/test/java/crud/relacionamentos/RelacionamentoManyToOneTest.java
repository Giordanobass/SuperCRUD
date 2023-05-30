package crud.relacionamentos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import crud.entitymanager.EntityManagerTest;
import crud.enuns.StatusPedido;
import crud.model.Cliente;
import crud.model.ItemPedido;
import crud.model.ItemPedidoId;
import crud.model.Pedido;
import crud.model.Produto;

public class RelacionamentoManyToOneTest extends EntityManagerTest {

    @Test
    public void verificarRelacionamento() {
        Cliente cliente = entityManager.find(Cliente.class, 1);
        Pedido pedidoItem = entityManager.find(Pedido.class, 1);
        Produto produtoItem = entityManager.find(Produto.class, 1);
        
        ItemPedido itens = entityManager.find(ItemPedido.class, new ItemPedidoId(1,1));
        
        itens.setPedido(pedidoItem);
        itens.setProduto(produtoItem);
        
        List<ItemPedido> listItemPedido = new ArrayList<>();
        listItemPedido.add(itens);
        
        Pedido pedido = new Pedido();
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setTotal(BigDecimal.TEN);
        pedido.setItens(listItemPedido);

        pedido.setCliente(cliente);

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        Assert.assertNotNull(pedidoVerificacao.getCliente());
    }

    @Test
    public void verificarRelacionamentoItemPedido() {
        Cliente cliente = entityManager.find(Cliente.class, 1);
        Pedido pedidoItem = entityManager.find(Pedido.class, 1);
        Produto produto = entityManager.find(Produto.class, 1);
        
        ItemPedido itens = entityManager.find(ItemPedido.class, new ItemPedidoId(1,1));

        itens.setPedido(pedidoItem);
        itens.setProduto(produto);
        
        List<ItemPedido> listItemPedido = new ArrayList<>();
        listItemPedido.add(itens);

        Pedido pedido = new Pedido();
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setTotal(BigDecimal.TEN);
        pedido.setItens(listItemPedido);

        pedido.setCliente(cliente);


        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setPrecoProduto(produto.getPreco());
        itemPedido.setQuantidade(1);
        itemPedido.setPedido(pedido);
        itemPedido.setProduto(produto);
        itemPedido.setId(new ItemPedidoId(pedido.getId(), produto.getId()));

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.persist(itemPedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        ItemPedido itemPedidoVerificacao = entityManager.find(ItemPedido.class, new ItemPedidoId(1,1));
        Assert.assertNotNull(itemPedidoVerificacao.getPedido());
        Assert.assertNotNull(itemPedidoVerificacao.getProduto());
    }
}
