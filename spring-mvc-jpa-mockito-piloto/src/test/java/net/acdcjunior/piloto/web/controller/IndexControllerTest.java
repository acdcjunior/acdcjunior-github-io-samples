package net.acdcjunior.piloto.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import net.acdcjunior.piloto.test.ControllerIntegrationTest;

import org.junit.Test;

public class IndexControllerTest extends ControllerIntegrationTest {
	
	@Test
	public void index__deve_trazer_fazer_redirect() throws Exception {
		mockMvc().perform(get("/"))
        		 .andExpect(status().isMovedTemporarily())
        		 .andExpect(redirectedUrl("/usuario"));
	}
	
}