package br.com.eds.ecomerce.api.entity;

import br.com.eds.ecomerce.api.enums.StatusEnum;
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
import java.time.LocalDate;

@Entity
@Table(name = "CLIENTE", schema = "ECOMERCE")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CLIENTE_ID_SEQ", sequenceName="CLIENTE_ID_SEQ", schema="ECOMERCE", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLIENTE_ID_SEQ")
	@Column(name="ID", columnDefinition = "serial")
	private Long id;
	
	@Column(name="NOME", nullable = false )
	private String nome;

	@Column(name="DATA_CADASTRO", nullable = false )
	private LocalDate dataCadastro;

	@Column(name="STATUS", nullable = false )
	private StatusEnum status;

}
