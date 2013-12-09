package net.acdcjunior.piloto.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import net.acdcjunior.piloto.test.ControllerIntegrationTest;

import org.junit.Test;

public class UsuarioControllerTest extends ControllerIntegrationTest {
	
	@Test
	public void listarUsuarios__deve_trazer_200_ok() throws Exception {
		buildMockMvc().perform(get("/usuarios"))
                    .andDo(print())
                	.andExpect(status().isOk())
                	.andExpect(view().name("usuarios"))
                	.andExpect(forwardedUrl("/WEB-INF/layout/layout.jsp"));
	}
	
	@Test
	public void novoUsuario__deve_trazer_200_ok() throws Exception {
		buildMockMvc().perform(get("/usuarios/novo"))
        			.andExpect(status().isOk())
                	.andExpect(view().name("usuarios-novo"))
                	.andExpect(forwardedUrl("/WEB-INF/layout/layout.jsp"));
	}

	@Test
	public void criarUsuario__deve_trazer_fazer_redirect() throws Exception {
		buildMockMvc().perform(post("/usuarios").param("nome", "bob"))
        			.andDo(print())
        			.andExpect(status().isMovedTemporarily())
        			.andExpect(redirectedUrl("/usuarios"));
	}
	
	@Test
	public void variavelString__deve_trazer_200_ok() throws Exception {
		buildMockMvc().perform(get("/usuarios-string"))
			          .andDo(print())
			    	  .andExpect(status().isOk())
			    	  .andExpect(view().name("usuarios-string"))
			    	  .andExpect(forwardedUrl("/WEB-INF/layout/layout.jsp"));
	}
	
	@Test
	public void variavelMap__deve_trazer_200_ok() throws Exception {
		buildMockMvc().perform(get("/usuarios-map"))
			          .andDo(print())
			    	  .andExpect(status().isOk())
			    	  .andExpect(view().name("usuarios-map"))
			    	  .andExpect(forwardedUrl("/WEB-INF/layout/layout.jsp"));
	}
	
}