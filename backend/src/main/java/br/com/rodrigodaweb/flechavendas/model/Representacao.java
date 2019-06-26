package br.com.rodrigodaweb.flechavendas.model;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Representacao")
public class Representacao implements Serializable, Comparable<Representacao> {

	private static final long serialVersionUID = 3051654681419075796L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "idUsuario", foreignKey = @ForeignKey(name = "FK_REPRESENTACAO_USUARIO"))
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "idIndustria", foreignKey = @ForeignKey(name = "FK_REPRESENTACAO_INDUSTRIA"))
	private Industria industria;

	@Default
	@ManyToMany(mappedBy = "representantes", cascade = CascadeType.ALL)
	private Set<Cliente> clientes = new TreeSet();

	private boolean ativo;

	@Override
	public int compareTo(Representacao o) {
		if (this.id < o.id) {
			return -1;
		}
		if (this.id > o.id) {
			return 1;
		}
		return 0;
	}

}
