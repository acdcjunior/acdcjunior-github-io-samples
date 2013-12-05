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

import java.util.Date;
import java.util.List;

import net.acdcjunior.piloto.domain.Funcao;
import net.acdcjunior.piloto.domain.Usuario;
import net.acdcjunior.piloto.repository.UsuarioRepository;
import net.acdcjunior.piloto.test.util.InjetarEntityManagerRule;
import net.acdcjunior.piloto.test.util.InjetarEntityManagerRule.InjetarEntityManager;

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
		usuario.setNome(Long.toString(new java.util.Date().getTime()));
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
		String novoNome = Long.toString(new java.util.Date().getTime());
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
		assertThat(todosUsuarios, hasItem(usuario_1));
		assertThat(todosUsuarios, hasItem(usuario_2));
		assertThat(todosUsuarios, hasItem(usuario_3));
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
		Usuario usuarioAposSalvar = jpaUsuarioRepositoryImpl.findById(usuario.getId());
		assertThat(usuarioAposSalvar.getFuncoes(), hasSize(1));
		assertThat(usuarioAposSalvar.getFuncoes(), hasItem(funcao));
	}

	
	@Test
	public void addFuncao() {
		Usuario usuario = new Usuario();
		usuario.setNome(Long.toString(new Date().getTime()));

		Funcao funcao = new Funcao();
		funcao.setNome(Long.toString(new Date().getTime()));

		emRule.getEntityManagerInjetado().getTransaction().begin();
		emRule.getEntityManagerInjetado().persist(usuario);
		emRule.getEntityManagerInjetado().persist(funcao);
		emRule.getEntityManagerInjetado().getTransaction().commit();

		assertEquals(0, usuario.getFuncoes().size());

		usuario.addFuncao(funcao);

		emRule.getEntityManagerInjetado().getTransaction().begin();
		emRule.getEntityManagerInjetado().merge(usuario);
		emRule.getEntityManagerInjetado().getTransaction().commit();

		assertEquals(1, usuario.getFuncoes().size());
	}

}