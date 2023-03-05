package crud.iniciandojpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;

import org.junit.Test;

import crud.entitymanager.EntityManagerTest;
import crud.model.Produto;

public class OperacoesComTransacaoTest extends EntityManagerTest {
	
	@Test
	public void impedirOperacaoComBancoDeDados() {
		Produto produto = entityManager.find(Produto.class, 1);
		entityManager.detach(produto);

		entityManager.getTransaction().begin();
		produto.setNome("Kindle Paperwhite 2ª Geraçao");
		entityManager.getTransaction().commit();
		entityManager.clear();

		Produto verificaProduto = entityManager.find(Produto.class, 1);
		assertEquals("Kindle", verificaProduto.getNome());
	}

	@Test
	public void inserirPrimeiroObjeto() {
		Produto produto = new Produto();
		produto.setNome("Câmera Canon");
		produto.setDescricao("A melhor definição para suas fotos");
		produto.setPreco(new BigDecimal(5000));

		entityManager.getTransaction().begin();
		entityManager.persist(produto);
		entityManager.getTransaction().commit();

		entityManager.clear();

		Produto verificaProduto = entityManager.find(Produto.class, produto.getId());
		assertNotNull(verificaProduto);
	}

	@Test
	public void inserirObjetoComMerge() {
		Produto produto = new Produto();
		produto.setNome("Microfone Rode Videmic");
		produto.setDescricao("A melhor qualidade de som");
		produto.setPreco(new BigDecimal(1000));

		entityManager.getTransaction().begin();
		entityManager.merge(produto);
		entityManager.getTransaction().commit();

		entityManager.clear();

		Produto verificaProduto = entityManager.find(Produto.class, produto.getId());
		assertNotNull(verificaProduto);
	}

	@Test
	public void removeObjeto() {
		Produto produto = entityManager.find(Produto.class, 3);

		entityManager.getTransaction().begin();
		entityManager.remove(produto);
		entityManager.getTransaction().commit();

		Produto verificaProduto = entityManager.find(Produto.class, 3);
		assertNull(verificaProduto);
	}

	@Test
	public void atualizaObjeto() {
		Produto produto = new Produto();
		produto.setNome("Kindle Paperwhite");
		produto.setDescricao("Conheça o novo Kindle");
		produto.setPreco(new BigDecimal(599));

		entityManager.getTransaction().begin();
		entityManager.merge(produto);
		entityManager.getTransaction().commit();
		entityManager.clear();

		Produto verificaProduto = entityManager.find(Produto.class, 1);
		assertEquals("Kindle Paperwhite", verificaProduto.getNome());
	}

	@Test
	public void mostrarDiferencaPersistMerge() {
		Produto produtoPersiste = new Produto();
		produtoPersiste.setNome("Smartphone One Plus");
		produtoPersiste.setDescricao("O processador mais rápido");
		produtoPersiste.setPreco(new BigDecimal(2599));

		entityManager.getTransaction().begin();
		entityManager.persist(produtoPersiste);
		produtoPersiste.setNome("Smartphone Two Plus");
		entityManager.getTransaction().commit();
		entityManager.clear();

		Produto verificaProduto = entityManager.find(Produto.class, produtoPersiste.getId());
		assertNotNull(verificaProduto);
		
		Produto produtomerge = new Produto();
		produtomerge.setNome("Notebook Dell");
		produtomerge.setDescricao("O melhor da categoria");
		produtomerge.setPreco(new BigDecimal(6599));

		entityManager.getTransaction().begin();
		produtomerge = entityManager.merge(produtomerge);
		produtomerge.setNome("Notebook Dell 2");
		entityManager.getTransaction().commit();
		entityManager.clear();

		Produto verificaMerge = entityManager.find(Produto.class, produtomerge.getId());
		assertNotNull(verificaMerge);
	}

	@Test
	public void atualizaObjetoGerenciado() {
		Produto produto = entityManager.find(Produto.class, 1);

		entityManager.getTransaction().begin();
		produto.setNome("Kindle Paperwhite 2ª Geraçao");
		entityManager.getTransaction().commit();
		entityManager.clear();

		Produto verificaProduto = entityManager.find(Produto.class, 1);
		assertEquals("Kindle Paperwhite 2ª Geraçao", verificaProduto.getNome());
	}

}
