package crud.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import crud.enuns.StatusPedido;
import crud.listener.GenericoListener;
import crud.listener.GerarNotaFiscalListener;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@EntityListeners({ GerarNotaFiscalListener.class, GenericoListener.class })
@Entity
@Table(name = "pedido")
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedido_id_seq")
    @SequenceGenerator(name = "pedido_id_seq", sequenceName = "pedido_id_seq", initialValue = 1, allocationSize = 1)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens;

    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;
    
    @Column(name = "data_ultima_atualizacao", insertable = false)
    private LocalDateTime dataUltimaAtualizacao;

    @Column(name = "data_conclusao")
    private LocalDateTime dataConclusao;

    @OneToOne(mappedBy = "pedido")
    private NotaFiscal notaFiscal;

    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @OneToOne(mappedBy = "pedido")
    private PagamentoCartao pagamentoCartao;

    @Embedded
    private EnderecoEntregaPedido enderecoEntrega;
    
    public boolean isPago() {
    	return StatusPedido.PAGO.equals(status);
    }
    
    public void calcularTotal() {
    	if(itens != null) {
    		total = itens.stream().map(ItemPedido::getPrecoProduto).reduce(BigDecimal.ZERO, BigDecimal::add);
    	}
    }
    
    @PrePersist
    public void aoPersistir() {
    	dataCriacao = LocalDateTime.now();
    	calcularTotal();
    }

    @PreUpdate
    public void aoAtualizar() {
    	dataUltimaAtualizacao = LocalDateTime.now();
    	calcularTotal();
    }
}
