package net.acdcjunior.piloto.controller;

import java.util.HashMap;
import java.util.Map;

import net.acdcjunior.piloto.domain.Usuario;
import net.acdcjunior.piloto.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
        return "usuarios-novo";
    }

    @RequestMapping(value = "/usuarios", method = RequestMethod.POST)
    @Transactional
    public String createUsuario(Model model, String nome) {
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuarioRepository.save(usuario);
        return "redirect:/usuarios";
    }
    
    @RequestMapping("/usuarios-string")  
    public ModelAndView variavelString() {  
        return new ModelAndView("usuarios-string", "variavelString", "Exemplo de como passar variavel!");  
    }
    
    @RequestMapping("/usuarios-map")  
    public ModelAndView variavelMap() {
    	Map<String, String> model = new HashMap<>();
    	model.put("um", "111111");
    	model.put("dois", "222222");
        return new ModelAndView("usuarios-map", "variavelMap", model);  
    }
    
}