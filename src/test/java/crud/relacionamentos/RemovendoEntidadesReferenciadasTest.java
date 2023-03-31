package crud.relacionamentos;

import static org.junit.Assert.assertNull;

import org.junit.Assert;
import org.junit.Test;

import crud.entitymanager.EntityManagerTest;
import crud.model.Pedido;

public class RemovendoEntidadesReferenciadasTest extends EntityManagerTest {

    @Test
    public void removerEntidadeRelacionada() {
        Pedido pedido = entityManager.find(Pedido.class, 1);

        Assert.assertFalse(pedido.getItens().isEmpty());

        entityManager.getTransaction().begin();
        pedido.getItens().forEach(i -> entityManager.remove(i));
        entityManager.remove(pedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, 1);
        assertNull(pedidoVerificacao);
    }
}
