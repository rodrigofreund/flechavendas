package br.com.rodrigodaweb.flechavendas.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "IndustriaCliente")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IndustriaCliente implements Serializable {

	private static final long serialVersionUID = 5742091232359171847L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "idCliente", foreignKey=@ForeignKey(name="FK_INDUSTRIACLIENTE_CLIENTE"))
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "idIndustria", foreignKey=@ForeignKey(name="FK_INDUSTRIACLIENTE_INDUSTRIA"))
	private Industria industria;

	private String codigo;

	private boolean ativo;

	private boolean bloqueioVenda;

	private BigDecimal limiteCredito;

	@OneToMany(mappedBy = "industriaCliente")
	private List<IndustriaClientePrazo> prazos;
}
