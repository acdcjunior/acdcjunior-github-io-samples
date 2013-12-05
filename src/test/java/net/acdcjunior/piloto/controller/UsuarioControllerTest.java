package net.acdcjunior.piloto.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import net.acdcjunior.piloto.test.util.ControllerIntegrationTest;

import org.junit.Test;

public class UsuarioControllerTest extends ControllerIntegrationTest {
	
	@Test
	public void usuarios__deve_trazer_200_ok() throws Exception {
        this.mockMvc.perform(get("/usuarios"))
                    .andDo(print())
                	.andExpect(status().isOk())
                	.andExpect(forwardedUrl("/WEB-INF/views/usuarios.jsp"));
	}
	
	@Test
	public void createUsuario_Model__deve_trazer_200_ok() throws Exception {
        this.mockMvc.perform(get("/usuarios/novo"))
        			.andExpect(status().isOk())
        			.andExpect(forwardedUrl("/WEB-INF/views/usuario-novo.jsp"));
	}

	@Test
	public void createUsuario_Model_String__deve_trazer_fazer_redirect() throws Exception {
        this.mockMvc.perform(post("/usuarios").param("nome", "bob"))
        			.andDo(print())
        			.andExpect(status().isMovedTemporarily())
        			.andExpect(redirectedUrl("usuarios"));
	}

}