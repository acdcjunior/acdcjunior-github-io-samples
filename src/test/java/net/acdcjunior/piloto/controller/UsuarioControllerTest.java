package net.acdcjunior.piloto.controller;

import static org.hamcrest.Matchers.hasKey;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import net.acdcjunior.piloto.test.ControllerIntegrationTest;

import org.junit.Test;

public class UsuarioControllerTest extends ControllerIntegrationTest {
	
	@Test
	public void listarUsuarios__deve_trazer_200_ok() throws Exception {
		mockMvc().perform(get("/usuarios"))
            	 .andDo(print())
            	 .andExpect(status().isOk())
            	 .andExpect(view().name("usuarios"))
            	 .andExpect(forwardedUrl("/WEB-INF/layout/layout.jsp"));
	}
	
	@Test
	public void novoUsuario__deve_trazer_200_ok() throws Exception {
		mockMvc().perform(get("/usuarios/novo"))
    			 .andExpect(status().isOk())
            	 .andExpect(view().name("usuarios-novo"))
            	 .andExpect(forwardedUrl("/WEB-INF/layout/layout.jsp"));
	}

	@Test
	public void criarUsuario__deve_trazer_fazer_redirect() throws Exception {
		mockMvc().perform(post("/usuarios").param("nome", "bob"))
        		 .andDo(print())
        		 .andExpect(status().isMovedTemporarily())
        		 .andExpect(redirectedUrl("/usuarios"));
	}
	
	@Test
	public void variavelString__deve_trazer_200_ok() throws Exception {
		mockMvc().perform(get("/usuarios-string"))
		         .andDo(print())
		    	 .andExpect(status().isOk())
		    	 .andExpect(view().name("usuarios-string"))
		    	 .andExpect(model().attributeExists("variavelString"))
		    	 .andExpect(forwardedUrl("/WEB-INF/layout/layout.jsp"));
	}
	
	@Test
	public void variavelMap__deve_trazer_200_ok() throws Exception {
		mockMvc().perform(get("/usuarios-map"))
			     .andDo(print())
			     .andExpect(status().isOk())
			     .andExpect(view().name("usuarios-map"))
			     .andExpect(model().attribute("variavelMap", hasKey("um")))
			     .andExpect(model().attribute("variavelMap", hasKey("dois")))
			     .andExpect(forwardedUrl("/WEB-INF/layout/layout.jsp"));
	}

}