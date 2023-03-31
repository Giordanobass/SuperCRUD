package crud.relacionamentos;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import crud.entitymanager.EntityManagerTest;
import crud.model.Categoria;
import crud.model.Produto;

public class RelacionamentoManyToManyTest extends EntityManagerTest {

    @Test
    public void verificarRelacionamento() {
        Produto produto = entityManager.find(Produto.class, 1);
        Categoria categoria = new Categoria();
        
        insertCategoria(categoria);

        categoria = entityManager.find(Categoria.class, 1);

        entityManager.getTransaction().begin();
//        categoria.setProdutos(Arrays.asList(produto));
        produto.setCategorias(Arrays.asList(categoria));
        entityManager.getTransaction().commit();

        entityManager.clear();

        Categoria categoriaVerificacao = entityManager.find(Categoria.class, categoria.getId());
        Assert.assertTrue(categoriaVerificacao.getProdutos().isEmpty());
    }

	private void insertCategoria(Categoria categoria) {
		categoria.setNome("Cozinha");
		categoria.setCategorias(new ArrayList<Categoria>());
		categoria.setProdutos(new ArrayList<Produto>());
		entityManager.getTransaction().begin();
		entityManager.persist(categoria);
		entityManager.getTransaction().commit();
		entityManager.clear();
	}
}
