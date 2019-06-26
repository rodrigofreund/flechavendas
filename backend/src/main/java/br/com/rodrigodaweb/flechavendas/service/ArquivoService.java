package br.com.rodrigodaweb.flechavendas.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;

import br.com.rodrigodaweb.flechavendas.dto.ArquivoClienteDto;
import br.com.rodrigodaweb.flechavendas.exception.BusinessException;

@Service
@Transactional
public class ArquivoService {

	static final Logger log = Logger.getLogger(ArquivoService.class);

	@Autowired
	private UploadFileService uploadFileService;

	@Value("${br.com.rodrigodaweb.flechavendas.cliente.documento.dir}")
	private String uploadDir;

	public List<ArquivoClienteDto> uploadArquivoCliente(String cpfCnpj, MultipartFile mFile[]) {
		List<ArquivoClienteDto> result = Lists.newArrayList();
		for (MultipartFile file : mFile) {
			result.add(ArquivoClienteDto.builder()
					.nomeArquivo(uploadFileService.uploadArquivoCliente(cpfCnpj, file).getName()).build());
		}
		return result;
	}

	public String downloadArquivo(String cpfCnpj, String fileName) throws FileNotFoundException {
		log.debug("Download arquivo " + fileName);
		String rootPath = System.getProperty("catalina.home");
		String path = rootPath + uploadDir + cpfCnpj + "/" + fileName;

		File file = new File(path);
		InputStream in = new FileInputStream(file);

		try {
			byte[] bytes = IOUtils.toByteArray(in);
			return Base64.getEncoder().encodeToString(bytes);
		} catch (IOException e) {
			throw new BusinessException(31, "Não foi possível localizar a imagem");
		}
	}
}
