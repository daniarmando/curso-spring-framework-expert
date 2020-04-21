package com.algaworks.brewer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.algaworks.brewer.model.Usuario;
import com.algaworks.brewer.service.CadastroUsuarioService;
import com.algaworks.brewer.service.exception.EmailJaCadastradoException;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {
	
	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;
	
	@GetMapping("/novo")
	public String novo(Usuario usuario) {
		return "usuario/CadastroUsuario";
	}
	
	@PostMapping("/novo")
	public String salvar(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {			
			return novo(usuario);
		}
		
		try {
			cadastroUsuarioService.salvar(usuario);
		} catch(EmailJaCadastradoException e) {
			result.rejectValue("email", e.getMessage(), e.getMessage());
			return novo(usuario);
		}
				
		attributes.addFlashAttribute("mensagem", "Usuário cadastrado com sucesso");
		return "redirect:/usuarios/novo";
	}			
}
