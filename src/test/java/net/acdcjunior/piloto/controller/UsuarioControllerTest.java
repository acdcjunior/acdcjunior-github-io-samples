package net.acdcjunior.piloto.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import net.acdcjunior.piloto.test.ControllerIntegrationTest;

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
        this.mockMvc.perform(get("/create-usuario")).andExpect(status().isOk());
	}

	@Test
	public void createUsuario_Model_String__deve_trazer_fazer_redirect_302() throws Exception {
        this.mockMvc.perform(post("/create-usuario").param("nome", "bob")).andExpect(status().isMovedTemporarily());
	}

}