package br.com.rodrigodaweb.flechavendas.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.rodrigodaweb.flechavendas.dto.ArquivoTabelaDto;
import br.com.rodrigodaweb.flechavendas.dto.DownloadTabelaDto;
import br.com.rodrigodaweb.flechavendas.dto.TabelaDto;
import br.com.rodrigodaweb.flechavendas.service.TabelaService;

@CrossOrigin(origins = { "*", "https://sistema.flechavendas.com.br", "https://sistemateste.flechavendas.com.br" })
@RestController
public class TabelaController {

	@Autowired
	private TabelaService tabelaService;

	@PostMapping(value = "/uploadTabela")
	public ArquivoTabelaDto uploadTabela(HttpServletRequest req,
			@RequestParam(value = "file", required = true) MultipartFile mFile) {
		try {
			return tabelaService.uploadTabela(req, mFile);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@PostMapping(value = "/getTabelasPorIndustria")
	public List<TabelaDto> getTabelasPorIndustria(@RequestBody Integer idIndustria) {
		return tabelaService.getTabelasPorIdIndustria(idIndustria);
	}

	@PostMapping(value = "/excluirTabela")
	public TabelaDto excluirTabela(@RequestBody Long idTabela) {
		return tabelaService.excluirTabela(idTabela);
	}

	@GetMapping(value = "/downloadArquivoTabela")
	public DownloadTabelaDto downloadTabela(@RequestParam(value = "idTabela") Long idTabela)
			throws FileNotFoundException {
		return tabelaService.downloadTabela(idTabela);
	}

	@GetMapping(value = "/buscaTabelaPorId")
	public TabelaDto buscaTabelaPorId(@RequestParam("idTabela") Long idTabela) {
		return tabelaService.buscaTabelaPorId(idTabela);
	}
}
