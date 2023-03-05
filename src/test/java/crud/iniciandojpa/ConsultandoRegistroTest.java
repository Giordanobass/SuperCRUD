package crud.iniciandojpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import crud.entitymanager.EntityManagerTest;
import crud.model.Produto;

public class ConsultandoRegistroTest extends EntityManagerTest {
	
	 @Test
	 public void buscarPorIdentificador() {
		 Produto produto = entityManager.find(Produto.class, 1);
		 System.out.println(produto);
		 assertNotNull(produto);
		 assertEquals("Kindle", produto.getNome());
	 }
	 
	 @Test
	 public void buscarPorIdentificador2() {
		 Produto produto = entityManager.getReference(Produto.class, 1);
		 
		 System.out.println(">>>> AINDA NÃO BUSCOU");
		 
		 assertNotNull(produto);
		 assertEquals("Kindle", produto.getNome());// busca se usar alguma propriedade da classe
	 }
	 
	 @Test
	 public void atualizarReferencia() {
		 Produto produto = entityManager.find(Produto.class, 1);
		 produto.setNome("Microfone");
		 System.out.println(">> Nome alterado: "+produto.getNome());
		 entityManager.refresh(produto);
		 System.out.println(">> Nome atualizado com banco: "+produto.getNome());		 
		 assertEquals("Kindle", produto.getNome());
	 }
	

}
