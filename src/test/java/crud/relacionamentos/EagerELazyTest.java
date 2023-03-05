package crud.relacionamentos;

import org.junit.Test;

import crud.entitymanager.EntityManagerTest;
import crud.model.Pedido;

public class EagerELazyTest extends EntityManagerTest {

    @Test
    public void verficarComportamento() {
        Pedido pedido = entityManager.find(Pedido.class, 1);

//        pedido.getItens().isEmpty();
    }
}
