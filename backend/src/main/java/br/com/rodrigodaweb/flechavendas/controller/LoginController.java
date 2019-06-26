package br.com.rodrigodaweb.flechavendas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.rodrigodaweb.flechavendas.dto.LoginDto;
import br.com.rodrigodaweb.flechavendas.dto.UsuarioDto;
import br.com.rodrigodaweb.flechavendas.service.UsuarioService;

@CrossOrigin(origins = { "*", "https://sistema.flechavendas.com.br", "https://sistemateste.flechavendas.com.br" })
@RestController
public class LoginController {

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping(value = "/doLogin/")
	public UsuarioDto doLogin(@RequestBody LoginDto usuario) {
		return usuarioService.doLogin(usuario.getLogin(), usuario.getSenha());
	}
}
