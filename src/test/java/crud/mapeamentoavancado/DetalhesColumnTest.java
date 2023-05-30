package crud.mapeamentoavancado;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.junit.Assert;
import org.junit.Test;

import crud.entitymanager.EntityManagerTest;
import crud.model.Produto;

public class DetalhesColumnTest  extends EntityManagerTest {
	
	@Test
	public void impedirInsercaoColunaAtualizacao() {
		entityManager.getTransaction().begin();
		
		Produto produto = entityManager.find(Produto.class, 1);
		produto.setPreco(BigDecimal.TEN);
		produto.setDataCriacao((LocalDateTime.now()));
		produto.setDataUltimaAtualizacao((LocalDateTime.now()));
		
//		entityManager.persist(produto);
		
		entityManager.getTransaction().commit();

		entityManager.clear();
		
		Produto verificacao = entityManager.find(Produto.class, produto.getId());
		
		Assert.assertNotEquals(produto.getDataCriacao().truncatedTo(ChronoUnit.SECONDS),
				verificacao.getDataCriacao().truncatedTo(ChronoUnit.SECONDS));
		
		Assert.assertEquals(produto.getDataUltimaAtualizacao().truncatedTo(ChronoUnit.SECONDS),
				verificacao.getDataUltimaAtualizacao().truncatedTo(ChronoUnit.SECONDS));
		
	}

}
