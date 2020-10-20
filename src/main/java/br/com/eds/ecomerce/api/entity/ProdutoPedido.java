package br.com.eds.ecomerce.api.entity;

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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "PRODUTO_PEDIDO", schema = "ECOMERCE")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProdutoPedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="PRODUTO_PEDIDO_ID_SEQ")
	@SequenceGenerator(name="PRODUTO_PEDIDO_ID_SEQ", sequenceName="PRODUTO_PEDIDO_ID_SEQ",
			schema="ECOMERCE", allocationSize=1)
	@Column(name="ID", columnDefinition = "serial")
	private Long id;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	private Produto produto;

	@Column(name="QUANTIDADE", nullable = false )
	private Integer quantidade;

}
