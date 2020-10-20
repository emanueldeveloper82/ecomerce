package br.com.eds.ecomerce.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "PRODUTO", schema = "ECOMERCE")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRODUTO_ID_SEQ")
	@SequenceGenerator(name="PRODUTO_ID_SEQ", sequenceName="PRODUTO_ID_SEQ", schema="ECOMERCE", allocationSize=1)
	@Column(name="ID", columnDefinition = "serial")
	private Long id;
	
	@Column(name="NOME", nullable = false )
	private String nome;

	@Column(name="VALOR", nullable = false )
	private BigDecimal valor;

	@Column(name="DISPONIVEL", nullable = false )
	private boolean disponivel;

}
