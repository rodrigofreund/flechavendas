package br.com.rodrigodaweb.flechavendas.facade;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import br.com.rodrigodaweb.flechavendas.dto.IndustriaPrazoDto;
import br.com.rodrigodaweb.flechavendas.dto.IndustriaPrazoPedidoDto;
import br.com.rodrigodaweb.flechavendas.mapper.IndustriaClientePrazoMapper;
import br.com.rodrigodaweb.flechavendas.mapper.IndustriaPrazoDiaMapper;
import br.com.rodrigodaweb.flechavendas.mapper.IndustriaPrazoMapper;
import br.com.rodrigodaweb.flechavendas.model.Industria;
import br.com.rodrigodaweb.flechavendas.model.IndustriaPrazo;
import br.com.rodrigodaweb.flechavendas.service.IndustriaClientePrazoService;
import br.com.rodrigodaweb.flechavendas.service.IndustriaPrazoDiaService;
import br.com.rodrigodaweb.flechavendas.service.IndustriaPrazoService;
import br.com.rodrigodaweb.flechavendas.service.IndustriaService;

@Service
public class IndustriaPrazoFacade {
	
	@Autowired
	private IndustriaClientePrazoService industriaClientePrazoService;

	@Autowired
	private IndustriaPrazoService industriaPrazoService;

	@Autowired
	private IndustriaPrazoDiaService industriaPrazoDiaService;
	
	@Autowired
	private IndustriaService industriaService;

	public void salvar(IndustriaPrazoDto industriaPrazoDto) {
		Industria industria = industriaService.findById(industriaPrazoDto.getIdIndustria());
		IndustriaPrazo ip = industriaPrazoService.salvar(IndustriaPrazoMapper.toIndustriaPrazo(industria, industriaPrazoDto));
		industriaPrazoDto.getDias()
				.forEach(dia -> {
					dia.setIdIndustriaPrazo(ip.getId());
					industriaPrazoDiaService.salvar(IndustriaPrazoDiaMapper.toIndustriaPrazoDia(dia));
				});

	}

	public List<IndustriaPrazoDto> getIndustriaPrazo(Integer idIndustria) {
		List<IndustriaPrazoDto> listaPrazos = industriaPrazoService.buscarPorIdIndustria(idIndustria);
		listaPrazos.stream()
				.forEach(item -> item.setDias(industriaPrazoDiaService.buscaPorIdIndustriaPrazo(item.getId()).stream()
						.map(IndustriaPrazoDiaMapper::toIndustriaPrazoDia).collect(Collectors.toList())));
		return listaPrazos;

	}

	public void removerIndustriaPrazo(Integer idIndustriaPrazo) {
		industriaPrazoService.excluir(idIndustriaPrazo) ;
		industriaPrazoDiaService.excluirPorIdIndustriaPrazo(idIndustriaPrazo);
	}

	public List<IndustriaPrazoPedidoDto> buscaPrazoParaPedido(Integer idCliente, Integer idIndustria) {
		List<IndustriaPrazoPedidoDto> result = Lists.newArrayList();
		result = industriaClientePrazoService.getIndustriaClientePrazo(idIndustria, idCliente).stream()
				.map(IndustriaClientePrazoMapper::toIndustriaPrazoPedidoDto).collect(Collectors.toList());
		if(CollectionUtils.isEmpty(result)) {
			result = industriaPrazoService.buscarPorIdIndustria(idIndustria).stream()
					.map(IndustriaPrazoMapper::toIndustriaPrazoPedidoDto).collect(Collectors.toList());
		}
		return result;
	}

}
