package br.com.rodrigodaweb.flechavendas.factory;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rodrigodaweb.flechavendas.exception.BusinessException;
import br.com.rodrigodaweb.flechavendas.model.Industria;
import br.com.rodrigodaweb.flechavendas.model.Item;
import br.com.rodrigodaweb.flechavendas.model.Tabela;
import br.com.rodrigodaweb.flechavendas.service.IndustriaService;

@Service
@Transactional
public class TabelaFactory {

	private static final String NOME_TABELA = "NOME";
	private static final String NOME_INDUSTRIA = "INDUSTRIA";
	private static final String DATA = "DATA";

	@Autowired
	private IndustriaService industriaService;

	public Tabela criaTabelaFromXlsFile(File tabelaFile) throws BusinessException {
		Tabela tabelaCriada = Tabela.builder().build();
		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream(tabelaFile);

			Workbook workbook = new XSSFWorkbook(inputStream);
			Sheet firstSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = firstSheet.iterator();
			Integer contadorLinha = 0;

			tabelaCriada.setItens(new HashSet<Item>());

			// Percorre linhas do arquivo
			while (iterator.hasNext()) {
				Row nextRow = iterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				contadorLinha++;

				if (contadorLinha == 1) {
					// Carrega nome da industria
					Industria industria = industriaService.buscaIndustriaByNome(leIndustriaTabela(cellIterator));
					tabelaCriada.setIndustria(industria);
				} else if (contadorLinha == 2) {
					// Carrega data da tabela
					tabelaCriada.setData(leDataTabela(cellIterator));
				} else if (contadorLinha == 3) {
					// Carrega nome da tabela
					tabelaCriada.setNome(leNomeTabela(cellIterator));
				} else if (contadorLinha >= 6) {
					// Carrega itens da tabela
					Item item = leItemTabela(cellIterator);
					if (item.getCodigo() != null) {
						tabelaCriada.getItens().add(item);
					}
				} else {
					continue;
				}
			}

			workbook.close();
			inputStream.close();

		} catch (Exception ex) {
			System.out.println("Erro no parser: " + ex.getMessage());
			throw new BusinessException(1, "erro no parse");
		}
		return tabelaCriada;
	}

	private Item leItemTabela(Iterator<Cell> cellIterator) {
		Item item = new Item();
		boolean end = false;

		// Percorre as colunas
		while (cellIterator.hasNext()) {
			Cell cell = cellIterator.next();

			switch (cell.getColumnIndex()) {
			// CAMPO CODIGO
			case 0:
				if (cell.getCellType().equals(CellType.BLANK)) {
					end = true;
					break;
				}
				cell.setCellType(CellType.STRING);
				item.setCodigo(cell.getStringCellValue());
				break;
			// CAMPO DESCRICAO
			case 1:
				item.setDescricao(cell.getStringCellValue());
				break;
			// CAMPO QUANTIDADE_CAIXA
			case 2:
				item.setQuantidade((int) cell.getNumericCellValue());
				break;
			// CAMPO VALOR_CAIXA_SEM_ST
			case 3:
				item.setPreco(BigDecimal.valueOf(cell.getNumericCellValue()));
				break;
			// CAMPO ST
			case 4:
				item.setSt(BigDecimal.valueOf(cell.getNumericCellValue()));
				break;
			case 5:
			// CAMPO IPI
				item.setIpi(BigDecimal.valueOf(cell.getNumericCellValue()));
				break;
			case 6:
			// CAMPO DESCONTO
				item.setDescontoSugerido(BigDecimal.valueOf(cell.getNumericCellValue()));
				break;
			default:
				end = true;
				break;
			}
			if (end) {
				break;
			}
		}
		return item;
	}

	private String leIndustriaTabela(Iterator<Cell> cellIterator) throws BusinessException {
		String valor = null;
		boolean flagOk = false;

		while (cellIterator.hasNext()) {
			Cell cell = cellIterator.next();
			if (flagOk) {
				valor = cell.getStringCellValue();
				break;
			}
			if (NOME_INDUSTRIA.equals(cell.getStringCellValue())) {
				flagOk = true;
			}
		}
		if (valor == null || valor.isEmpty()) {
			throw new BusinessException(1, "Descritivo " + NOME_INDUSTRIA + " é inválido");
		}
		return valor;
	}

	private Date leDataTabela(Iterator<Cell> cellIterator) throws BusinessException {
		Date valor = null;
		boolean flagOk = false;

		while (cellIterator.hasNext()) {
			Cell cell = cellIterator.next();
			if (flagOk) {
				valor = cell.getDateCellValue();
				break;
			}
			if (DATA.equals(cell.getStringCellValue())) {
				flagOk = true;
			}
		}
		if (valor == null) {
			throw new BusinessException(1, "Descritivo " + DATA + " é inválido");
		}
		return valor;
	}

	private String leNomeTabela(Iterator<Cell> cellIterator) throws BusinessException {
		String valor = null;
		boolean flagOk = false;

		while (cellIterator.hasNext()) {
			Cell cell = cellIterator.next();
			if (flagOk) {
				valor = cell.getStringCellValue();
				break;
			}
			if (NOME_TABELA.equals(cell.getStringCellValue())) {
				flagOk = true;
			}
		}
		if (valor == null || valor.isEmpty()) {
			throw new BusinessException(1, "Descritivo " + NOME_TABELA + " é inválido");
		}
		return valor;
	}
}
