package com.algaworks.brewer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.algaworks.brewer.model.Usuario;
import com.algaworks.brewer.repository.Grupos;
import com.algaworks.brewer.repository.Usuarios;
import com.algaworks.brewer.repository.filter.UsuarioFilter;
import com.algaworks.brewer.service.CadastroUsuarioService;
import com.algaworks.brewer.service.exception.EmailJaCadastradoException;
import com.algaworks.brewer.service.exception.SenhaObrigatoriaUsuarioException;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {
	
	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;
	
	@Autowired 
	private Usuarios usuarios;
	
	@Autowired
	private Grupos grupos;
	
	@GetMapping("/novo")
	public ModelAndView novo(Usuario usuario) {
		ModelAndView mv = new ModelAndView("usuario/CadastroUsuario");
		mv.addObject("grupos", grupos.findAll());
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {			
			return novo(usuario);
		}
		
		try {
			cadastroUsuarioService.salvar(usuario);
		} catch(EmailJaCadastradoException e) {
			result.rejectValue("email", e.getMessage(), e.getMessage());
			return novo(usuario);
		} catch(SenhaObrigatoriaUsuarioException e) {
			result.rejectValue("senha", e.getMessage(), e.getMessage());
			return novo(usuario);
		}
				
		attributes.addFlashAttribute("mensagem", "Usuário cadastrado com sucesso");
		return new ModelAndView("redirect:/usuarios/novo");
	}	
	
	@GetMapping
	public ModelAndView pesquisar(UsuarioFilter usuarioFilter) {
		ModelAndView mv = new ModelAndView("/usuario/PesquisaUsuarios");
		mv.addObject("usuarios", usuarios.findAll());
		mv.addObject("grupos", grupos.findAll());
		return mv;
	}
}
