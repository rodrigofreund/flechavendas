package br.com.rodrigodaweb.flechavendas.service;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class UploadFileService {

	static final Logger log = LoggerFactory.getLogger(UploadFileService.class);

	@Autowired
	private HttpServletRequest request;

	@Value("${br.com.rodrigodaweb.flechavendas.cliente.documento.dir}")
	private String uploadDir;

	public File uploadArquivoCliente(String cpfCnpj, MultipartFile mFile) {
		String rootPath = System.getProperty("catalina.home");

		String fullPath = rootPath + uploadDir + cpfCnpj + "/";

		return uploadFile(fullPath, mFile);
	}

	public File uploadTabela(MultipartFile mFile) {
		String realPathtoUploads = request.getServletContext().getRealPath(uploadDir);
		
		return uploadFile(realPathtoUploads, mFile);
	}

	private File uploadFile(String fullPath, MultipartFile mFile) {

		consistePath(fullPath);

		String orgName = mFile.getOriginalFilename();
		String filePath = fullPath + orgName;

		log.debug("Enviando arquivo {}", filePath);
		File dest = new File(filePath);

		try {
			mFile.transferTo(dest);
			return dest;
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void consistePath(String path) {
		File mDest = new File(path);
		if (!mDest.exists()) {
			mDest.mkdirs();
		}
	}
}
