package br.com.rodrigodaweb.flechavendas.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.rodrigodaweb.flechavendas.domain.STATUS_PEDIDO;
import br.com.rodrigodaweb.flechavendas.dto.StatusPedidoDto;

@Service
@Transactional
public class StatusPedidoService {
	public List<StatusPedidoDto> getListaStatus() {
		return STATUS_PEDIDO.getListaStatusDto();
	}
}
