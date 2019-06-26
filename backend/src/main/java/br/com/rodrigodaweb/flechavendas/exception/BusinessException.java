package br.com.rodrigodaweb.flechavendas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 2332675531966922960L;

	private Integer codigoErro;

	public BusinessException(Integer codigo, String message) {
		super(message);
		codigoErro = codigo;
	}

	public String getDescricaoErro() {
		return "Erro código: " + codigoErro + super.getMessage();
	}

	public Integer getCodigoErro() {
		return this.codigoErro;
	}

}
