package net.acdcjunior.piloto.repository.jpa;

import static net.acdcjunior.piloto.test.PilotoTestDados.usuario_1;
import static net.acdcjunior.piloto.test.PilotoTestDados.usuario_2;
import static net.acdcjunior.piloto.test.PilotoTestDados.usuario_3;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Set;

import net.acdcjunior.piloto.domain.Funcao;
import net.acdcjunior.piloto.domain.Usuario;
import net.acdcjunior.piloto.repository.UsuarioRepository;
import net.acdcjunior.piloto.test.InjetarEntityManagerRule;
import net.acdcjunior.piloto.test.InjetarEntityManagerRule.InjetarEntityManager;

import org.junit.Rule;
import org.junit.Test;

public class JpaUsuarioRepositoryImplTest {
	
	@Rule
	public InjetarEntityManagerRule emRule = new InjetarEntityManagerRule();
	
	@InjetarEntityManager
	UsuarioRepository jpaUsuarioRepositoryImpl = new JpaUsuarioRepositoryImpl();
	
	@Test
	public void save__deve_persistir_usuario_novo() {
		// given
		Usuario usuario = new Usuario();
		usuario.setNome(System.currentTimeMillis()+"");
		// when
		emRule.getEntityManagerInjetado().getTransaction().begin();
		jpaUsuarioRepositoryImpl.save(usuario);
		emRule.getEntityManagerInjetado().getTransaction().commit();
		// then
		Integer idAposSalvar = usuario.getId();
		assertThat(idAposSalvar, is(notNullValue()));
	}
	
	@Test
	public void save__deve_atualizar_usuario_pre_existente() {
		// given
		Usuario usuarioPreExistente = jpaUsuarioRepositoryImpl.findById(1);
		String novoNome = System.currentTimeMillis()+"";
		usuarioPreExistente.setNome(novoNome);
		// when
		emRule.getEntityManagerInjetado().getTransaction().begin();
		jpaUsuarioRepositoryImpl.save(usuarioPreExistente);
		emRule.getEntityManagerInjetado().getTransaction().commit();
		// then
		/* Apenas para garantir, usamos o detach() pra remover o 
		 * objeto "usuarioPreExistente" da sessao, o que garante que o
		 * findById trarah um objeto diferente. (Se nao fizermos isso,
		 * o findById trarah o "usuarioPreExistente" mesmo, o que nao
		 * estah errado, mas para nao deixar duvida nenhuma, usamos o detach()).
		 */
		emRule.getEntityManagerInjetado().detach(usuarioPreExistente);
		Usuario usuarioAposSalvar = jpaUsuarioRepositoryImpl.findById(1);
		assertEquals(novoNome, usuarioAposSalvar.getNome());
	}
	
	@Test
	public void findById__deve_encontrar_o_usuario_pelo_id() {
		// given
		// when
		Usuario usuarioObtido = jpaUsuarioRepositoryImpl.findById(usuario_1.getId());
		// then
		assertEquals(usuario_1.getId(), usuarioObtido.getId());
		assertEquals(usuario_1.getNome(), usuarioObtido.getNome());
	}
	
	@Test
	public void findByNome__deve_encontrar_o_usuario_pelo_nome() {
		// given
		// when
		Usuario usuarioObtido = jpaUsuarioRepositoryImpl.findByNome(usuario_1.getNome());
		// then
		assertEquals(usuario_1.getId(), usuarioObtido.getId());
		assertEquals(usuario_1.getNome(), usuarioObtido.getNome());
	}
	
	@Test
	public void findAllUsuarios__deve_trazer_todos_os_usuarios_da_base() {
		// given
		// when
		List<Usuario> todosUsuarios = jpaUsuarioRepositoryImpl.findAllUsuarios();
		// then
		assertThat(todosUsuarios, hasSize(3));
		assertThat(todosUsuarios, hasItem(jpaUsuarioRepositoryImpl.findById(usuario_1.getId())));
		assertThat(todosUsuarios, hasItem(jpaUsuarioRepositoryImpl.findById(usuario_2.getId())));
		assertThat(todosUsuarios, hasItem(jpaUsuarioRepositoryImpl.findById(usuario_3.getId())));
	}
	
	@Test
	public void save__deve_peristir_funcoes_associadas_ao_usuario() {
		// given
		Usuario usuario = new Usuario();
		usuario.setNome("Nome Qualquer");
		
		Funcao funcao = new Funcao();
		funcao.setNome("Nome Qualquer");
		emRule.getEntityManagerInjetado().getTransaction().begin();
		emRule.getEntityManagerInjetado().persist(funcao);
		emRule.getEntityManagerInjetado().getTransaction().commit();
		
		usuario.addFuncao(funcao);
		// when
		emRule.getEntityManagerInjetado().getTransaction().begin();
		jpaUsuarioRepositoryImpl.save(usuario);
		emRule.getEntityManagerInjetado().getTransaction().commit();
		// then
		emRule.getEntityManagerInjetado().detach(usuario);
		Usuario usuarioAposSalvar = jpaUsuarioRepositoryImpl.findById(usuario.getId());
		Set<Funcao> funcoesAposSalvar = usuarioAposSalvar.getFuncoes();
		assertThat(funcoesAposSalvar, hasSize(1));
		for (Funcao f : funcoesAposSalvar) {
			f.getId().equals(funcao.getId());
			f.getNome().equals(funcao.getNome());
		}
	}

}