package crud.conhecendoentitymanager;

import org.junit.Test;

import crud.entitymanager.EntityManagerTest;
import crud.enuns.StatusPedido;
import crud.model.Pedido;

public class GerenciamentoTransacoesTest extends EntityManagerTest {

    @Test(expected = Exception.class)
    public void abrirFecharCancelarTransacao() throws Exception {
        try {
            entityManager.getTransaction().begin();
            metodoDeNegocio();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    private void metodoDeNegocio() {
        Pedido pedido = entityManager.find(Pedido.class, 1);
        pedido.setStatus(StatusPedido.PAGO);

        if (pedido.getPagamentoCartao() == null) {
            throw new RuntimeException("Pedido ainda não foi pago.");
        }
    }
}
