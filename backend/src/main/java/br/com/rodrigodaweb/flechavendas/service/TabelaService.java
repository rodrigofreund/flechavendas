package br.com.rodrigodaweb.flechavendas.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.rodrigodaweb.flechavendas.dao.TabelaDAO;
import br.com.rodrigodaweb.flechavendas.dto.ArquivoTabelaDto;
import br.com.rodrigodaweb.flechavendas.dto.DownloadTabelaDto;
import br.com.rodrigodaweb.flechavendas.dto.TabelaDto;
import br.com.rodrigodaweb.flechavendas.exception.BusinessException;
import br.com.rodrigodaweb.flechavendas.factory.TabelaFactory;
import br.com.rodrigodaweb.flechavendas.mapper.ArquivoTabelaMapper;
import br.com.rodrigodaweb.flechavendas.mapper.TabelaMapper;
import br.com.rodrigodaweb.flechavendas.model.ArquivoTabela;
import br.com.rodrigodaweb.flechavendas.model.Item;
import br.com.rodrigodaweb.flechavendas.model.Tabela;

@Service
@Transactional
public class TabelaService {
	@Autowired
	private TabelaDAO tabelaDAO;

	@Autowired
	private UploadFileService uploadFileService;

	@Autowired
	private TabelaFactory tabelaFactory;

	@Autowired
	private ItemService itemService;

	@Value("${br.com.rodrigodaweb.flechavendas.cliente.documento.dir}")
	private String uploadDir;

	@Value("${br.com.rodrigodaweb.flechavendas.industria.tabela.dir}")
	private String uploadTabelaDir;

	@Autowired
	private ArquivoTabelaService arquivoTabelaService;
	
	static final Logger log = Logger.getLogger(TabelaService.class);

	public List<TabelaDto> getTabelasPorIdIndustria(Integer idIndustria) {
		return tabelaDAO.getTabelasPorIdIndustria(idIndustria).stream().map(TabelaMapper::toTabelaDto)
				.collect(Collectors.toList());
	}

	public Tabela criarTabela(Tabela tabela) {
		return tabelaDAO.save(tabela);
	}

	public TabelaDto excluirTabela(Long idTabela) {
		Tabela tabela = tabelaDAO.findOne(idTabela);
		tabela.setExcluido(true);
		return TabelaMapper.toTabelaDto(tabelaDAO.save(tabela));
	}

	public ArquivoTabelaDto uploadTabela(HttpServletRequest req, MultipartFile mFile) throws IOException {
		if (mFile == null || mFile.isEmpty()) {
			throw new BusinessException(50, "Não foi possível enviar o arquivo!");
		}

		File file = uploadFileService.uploadTabela(mFile);
		if (file.exists()) {
			Tabela tabela = tabelaFactory.criaTabelaFromXlsFile(file);
			Tabela novaTabela = criarTabela(tabela);
			for (Item item : tabela.getItens()) {
				item.setTabela(novaTabela);
				itemService.criarItem(item);
			}

			String rootPath = System.getProperty("catalina.home");
			String ano = getAno();
			String nome = novaTabela.getId() + "-" + mFile.getOriginalFilename();
			String definitiveDir = rootPath + uploadTabelaDir + ano + "/" 
					+ novaTabela.getIndustria().getId() + "/";

			consistePath(definitiveDir);

			File destinationFile = new File(definitiveDir + nome);
			
			Files.copy(file.toPath(), destinationFile.toPath());

			return ArquivoTabelaMapper.toArquivoTabelaDto(arquivoTabelaService.salvaArquivoTabela(ArquivoTabela.builder()
					.diretorio(definitiveDir)
					.nomeArquivo(nome)
					.tabela(novaTabela)
					.build()));
		} else {
			throw new BusinessException(50, "Não foi possível enviar o arquivo. Erro no servidor.");
		}
	}

	public TabelaDto buscaTabelaPorId(Long id) {
		return TabelaMapper.toTabelaDto(tabelaDAO.findOne(id));
	}

	public DownloadTabelaDto downloadTabela(Long idTabela) throws FileNotFoundException {
		ArquivoTabela arquivoTabela = arquivoTabelaService.buscaTabela(idTabela);
		
		log.debug("Download arquivo " + arquivoTabela.getNomeArquivo());

		File file = new File(arquivoTabela.getDiretorio() + arquivoTabela.getNomeArquivo());
		InputStream in = new FileInputStream(file);

		try {
			byte[] bytes = IOUtils.toByteArray(in);
			return DownloadTabelaDto.builder()
					.nomeArquivo(arquivoTabela.getNomeArquivo())
					.arquivo(Base64.getEncoder().encodeToString(bytes))
					.build();
		} catch (IOException e) {
			throw new BusinessException(31, "Não foi possível localizar o arquivo");
		}
	}

	private String getAno() {
		LocalDate localDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
		return localDate.format(formatter);
	}

	private void consistePath(String path) {
		File mDest = new File(path);
		if (!mDest.exists()) {
			mDest.mkdirs();
		}
	}
}
