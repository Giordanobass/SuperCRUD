package crud.conhecendoentitymanager;

import org.junit.Test;

import crud.entitymanager.EntityManagerTest;
import crud.model.Categoria;

public class EstadosECicloDeVidaTest extends EntityManagerTest {

    @Test
    public void analisarEstados() {
        Categoria categoriaNovo = new Categoria();

        Categoria categoriaGerenciadaMerge = entityManager.merge(categoriaNovo);

        Categoria categoriaGerenciada = entityManager.find(Categoria.class, 1);

        entityManager.remove(categoriaGerenciada);
        entityManager.persist(categoriaGerenciada);

        entityManager.detach(categoriaGerenciada);
    }

}
