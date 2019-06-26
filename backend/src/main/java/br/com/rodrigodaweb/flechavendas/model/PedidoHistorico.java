package br.com.rodrigodaweb.flechavendas.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PedidoHistorico")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PedidoHistorico implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name = "idPedido")
	private Pedido pedido;

	private Date dataHoraAlteracao;

	private Integer status;

	@ManyToOne
	@JoinColumn(name = "idUsuario")
	private Usuario usuario;

}
