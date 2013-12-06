package net.acdcjunior.piloto.controller;

import net.acdcjunior.piloto.domain.Usuario;
import net.acdcjunior.piloto.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
    
    @RequestMapping("/usuarios")
    public String usuarios(Model model) {
        model.addAttribute("usuarios", usuarioRepository.findAllUsuarios());
        return "usuarios";
    }

    @RequestMapping(value = "/usuarios/novo", method = RequestMethod.GET)
    public String createUsuario(Model model) {
        return "usuario-novo";
    }

    @RequestMapping(value = "/usuarios", method = RequestMethod.POST)
    @Transactional
    public String createUsuario(Model model, String nome) {
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuarioRepository.save(usuario);
        return "redirect:/usuarios";
    }
    
}