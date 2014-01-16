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
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
    
    @RequestMapping
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioRepository.findAll());
        return "usuario/all";
    }

    @RequestMapping(value = "/novo", method = RequestMethod.GET)
    public String novoUsuario() {
        return "usuario/novo";
    }

    @RequestMapping(method = RequestMethod.POST)
    @Transactional
    public String criarUsuario(String nome) {
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuarioRepository.save(usuario);
        return "redirect:/usuario";
    }
    
    @RequestMapping("/string")  
    public ModelAndView variavelString() {  
        return new ModelAndView("usuario/string", "variavelString", "Exemplo de como passar variavel!");  
    }
    
    @RequestMapping("/map")  
    public ModelAndView variavelMap() {
    	Map<String, String> model = new HashMap<>();
    	model.put("um", "111111");
    	model.put("dois", "222222");
        return new ModelAndView("usuario/map", "variavelMap", model);  
    }
    
}