package br.com.rodrigodaweb.flechavendas.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "IndustriaPrazoDia")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IndustriaPrazoDia implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = false)
	private Integer prazo;

	@ManyToOne
	@JoinColumn(name = "idIndustriaPrazo", foreignKey = @ForeignKey(name="FK_INDUSTRIAPRAZODIA_INDUSTRIAPRAZO"))
	private IndustriaPrazo industriaPrazo;

	private boolean excluido;
}
