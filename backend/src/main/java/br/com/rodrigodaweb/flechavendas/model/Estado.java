package br.com.rodrigodaweb.flechavendas.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "Estado")
@Data
public class Estado implements Serializable {

	private static final long serialVersionUID = -3428684174725253121L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(unique = true)
	private String sigla;

	@Column(unique = true)
	private String nome;
}
