package br.com.rodrigodaweb.flechavendas.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rodrigodaweb.flechavendas.dao.ItemDAO;
import br.com.rodrigodaweb.flechavendas.dto.ItemDto;
import br.com.rodrigodaweb.flechavendas.mapper.ItemMapper;
import br.com.rodrigodaweb.flechavendas.model.Item;

@Service
@Transactional
public class ItemService {
	@Autowired
	private ItemDAO itemDAO;
	
	public List<ItemDto> getItensPorIdTabela(Long idTabela) {
		return ItemMapper.toItemDtoList(itemDAO.getItensPorIdTabela(idTabela));
	}
	
	public Item criarItem(Item item) {
		return itemDAO.save(item);
	}
}
