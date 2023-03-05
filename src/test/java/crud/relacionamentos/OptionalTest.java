package crud.relacionamentos;

import org.junit.Test;

import crud.entitymanager.EntityManagerTest;
import crud.model.Pedido;

public class OptionalTest extends EntityManagerTest {

    @Test
    public void verficarComportamento() {
        Pedido pedido = entityManager.find(Pedido.class, 1);

    }
}
