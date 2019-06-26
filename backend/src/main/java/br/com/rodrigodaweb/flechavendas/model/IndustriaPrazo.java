package br.com.rodrigodaweb.flechavendas.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "IndustriaPrazo")
public class IndustriaPrazo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "idIndustria", foreignKey = @ForeignKey(name="FK_INDUSTRIAPRAZO_INDUSTRIA"))
	private Industria industria;

	@Column(length = 12)
	private String codigo;

	@Column(nullable = false, length = 40)
	private String descricao;

	@OneToMany(mappedBy = "industriaPrazo")
	private List<IndustriaClientePrazo> prazos;

	@OneToMany(mappedBy = "industriaPrazo", orphanRemoval = true, cascade = CascadeType.REMOVE)
	private Set<IndustriaPrazoDia> dias;

	@OneToMany(mappedBy = "industriaPrazo")
	private Set<Pedido> pedidos;

	private boolean excluido;
}
