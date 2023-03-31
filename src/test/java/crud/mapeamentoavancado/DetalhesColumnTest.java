package crud.mapeamentoavancado;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.Test;

import crud.entitymanager.EntityManagerTest;
import crud.model.Produto;

public class DetalhesColumnTest  extends EntityManagerTest {
	
	@Test
	public void impedirInsercaoColunaAtualizacao() {
		
		Produto produto = new Produto();
		produto.setNome("Teclado para smartphone");
		produto.setDescricao("O mais confortável");
		produto.setPreco(BigDecimal.ONE);
		produto.setDataCriacao((LocalDateTime.now()));
		produto.setDataUltimaAtualizacao((LocalDateTime.now()));
		
		entityManager.getTransaction().begin();
		entityManager.persist(produto);
		entityManager.getTransaction().commit();
		entityManager.clear();
		
		Produto verificacao = entityManager.find(Produto.class, produto.getId());
		assertNotNull(verificacao.getDataCriacao());
		assertNull(verificacao.getDataUltimaAtualizacao());
	}

}
