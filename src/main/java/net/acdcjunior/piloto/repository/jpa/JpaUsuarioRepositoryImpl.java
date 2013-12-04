package net.acdcjunior.piloto.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.acdcjunior.piloto.domain.Usuario;
import net.acdcjunior.piloto.repository.UsuarioRepository;

import org.springframework.stereotype.Repository;

@Repository
public class JpaUsuarioRepositoryImpl implements UsuarioRepository {
	
	public static final String FIND_BY_NOME = "Usuario.findByNome";

	@PersistenceContext
	private EntityManager em;

	@Override
	public void save(Usuario usuario) {
		if (usuario.getId() == null) {
			this.em.persist(usuario);
		} else {
			this.em.merge(usuario);
		}
	}

	@Override
	public Usuario findById(Integer id) {
		return this.em.find(Usuario.class, id);
	}
	
	@Override
	public Usuario findByNome(String nome) {
		return this.em.createNamedQuery(FIND_BY_NOME, Usuario.class)
				      .setParameter("nome", nome)
				      .getSingleResult();
	}

	@Override
	public List<Usuario> findAllUsuarios() {
		return this.em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
	}

}