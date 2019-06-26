package br.com.rodrigodaweb.flechavendas.domain;

public enum TIPO_PESSOA {
	PESSOA_FISICA(1, "Pessoa Física"),
	PESSOA_JURIDICA(2, "Pessoa Jurídica");
	
	private Integer id;
	private String descricao;
	
	private TIPO_PESSOA(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static TIPO_PESSOA getById(Integer id) {
		for(TIPO_PESSOA tpPessoa :TIPO_PESSOA.values()) {
			if(tpPessoa.getId().equals(id)) {
				return tpPessoa;
			}
		}
		return null;
	}
}
