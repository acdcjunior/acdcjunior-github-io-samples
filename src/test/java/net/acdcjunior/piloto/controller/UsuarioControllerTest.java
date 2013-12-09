package net.acdcjunior.piloto.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import net.acdcjunior.piloto.test.ControllerIntegrationTest;

import org.junit.Test;

public class UsuarioControllerTest extends ControllerIntegrationTest {
	
	@Test
	public void usuarios__deve_trazer_200_ok() throws Exception {
		buildMockMvc().perform(get("/usuarios"))
                    .andDo(print())
                	.andExpect(status().isOk())
                	.andExpect(view().name("usuarios"))
                	.andExpect(forwardedUrl("/WEB-INF/layout/layout.jsp"));
	}
	
	@Test
	public void createUsuario_Model__deve_trazer_200_ok() throws Exception {
		buildMockMvc().perform(get("/usuarios/novo"))
        			.andExpect(status().isOk())
                	.andExpect(view().name("usuarios-novo"))
                	.andExpect(forwardedUrl("/WEB-INF/layout/layout.jsp"));
	}

	@Test
	public void createUsuario_Model_String__deve_trazer_fazer_redirect() throws Exception {
		buildMockMvc().perform(post("/usuarios").param("nome", "bob"))
        			.andDo(print())
        			.andExpect(status().isMovedTemporarily())
        			.andExpect(redirectedUrl("/usuarios"));
	}

}