package br.com.rodrigodaweb.flechavendas.model;

import java.io.Serializable;

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

@Entity
@Table(name = "IndustriaClientePrazo")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IndustriaClientePrazo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "idIndustriaCliente", foreignKey=@ForeignKey(name="FK_INDUSTRIACLIENTEPRAZO_INDUSTRIACLIENTE"))
	private IndustriaCliente industriaCliente;

	@ManyToOne
	@JoinColumn(name = "idIndustriaPrazo",  foreignKey=@ForeignKey(name="FK_INDUSTRIACLIENTEPRAZO_INDUSTRIAPRAZO"))
	private IndustriaPrazo industriaPrazo;

	private boolean padrao;
}
