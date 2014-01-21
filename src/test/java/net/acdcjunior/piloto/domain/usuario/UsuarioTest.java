package net.acdcjunior.piloto.domain.usuario;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertThat;
import net.acdcjunior.piloto.domain.usuario.Funcao;
import net.acdcjunior.piloto.domain.usuario.Usuario;

import org.junit.Test;

public class UsuarioTest {
	
	@Test
	public void addFuncao__deve_adicionar_uma_funcao_aa_lista_de_funcoes() {
		// given
		Usuario u = new Usuario();
		Funcao f = new Funcao();
		// when
		u.addFuncao(f);
		// then
		assertThat(u.getFuncoes(), hasItem(f));
	}

}