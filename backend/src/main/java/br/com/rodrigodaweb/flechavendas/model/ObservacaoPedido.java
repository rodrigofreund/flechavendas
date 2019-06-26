package br.com.rodrigodaweb.flechavendas.model;

import java.io.Serializable;
import java.util.Date;

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

@Entity
@Table(name = "ObservacaoPedido")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ObservacaoPedido implements Serializable {

	private static final long serialVersionUID = -1459550920250152239L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idObservacao;

	@ManyToOne
	@JoinColumn(name = "idPedido", foreignKey = @ForeignKey(name="FK_OBSERVACAOPEDIDO_PEDIDO"))
	private Pedido pedido;

	private Date dataCriacao;

	private Date dataLeitura;

	private boolean lido;

	@ManyToOne
	@JoinColumn(name = "idUsuario", foreignKey = @ForeignKey(name="FK_OBSERVACAOPEDIDO_USUARIO"))
	private Usuario usuario;

	@Column(length = 2000)
	private String observacao;
}
