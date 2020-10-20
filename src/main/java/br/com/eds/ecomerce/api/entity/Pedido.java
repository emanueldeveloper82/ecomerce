package br.com.eds.ecomerce.api.entity;

import br.com.eds.ecomerce.api.enums.StatusEntregaEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "PEDIDO", schema = "ECOMERCE")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PEDIDO_ID_SEQ")
	@SequenceGenerator(name="PEDIDO_ID_SEQ", sequenceName="PEDIDO_ID_SEQ", schema="ECOMERCE", allocationSize=1)
	@Column(name="ID", columnDefinition = "serial")
	private Long id;

	@Column(name="DATA_CADASTRO", nullable = false )
	private LocalDate dataCadastro;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	@JoinColumn(name = "ID_CLIENTE")
	private Cliente cliente;

	@Column(name="STATUS", nullable = false )
	private StatusEntregaEnum statusEntrega;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ProdutoPedido> produtoPedidos;

}
