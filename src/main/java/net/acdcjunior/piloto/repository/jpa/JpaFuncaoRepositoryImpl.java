package net.acdcjunior.piloto.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.acdcjunior.piloto.domain.Funcao;
import net.acdcjunior.piloto.repository.FuncaoRepository;
import net.acdcjunior.piloto.repository.UsuarioRepository;

import org.springframework.stereotype.Repository;

/**
 * Implementacao JPA da interface {@link UsuarioRepository}.
 * 
 * @author acdcjunior
 */
@Repository
public class JpaFuncaoRepositoryImpl implements FuncaoRepository {
	
	public static final String FIND_BY_NOME = "Funcao.findByNome";

	@PersistenceContext
	private EntityManager em;

	@Override
	public void save(Funcao funcao) {
		if (funcao.getId() == null) {
			this.em.persist(funcao);
		} else {
			this.em.merge(funcao);
		}
	}

	@Override
	public Funcao findById(Integer id) {
		return this.em.find(Funcao.class, id);
	}
	
	@Override
	public Funcao findByNome(String nome) {
		return this.em.createNamedQuery(FIND_BY_NOME, Funcao.class)
					  .setParameter("nome", nome)
					  .getSingleResult();
	}

	@Override
	public List<Funcao> findAllFuncoes() {
		return this.em.createQuery("SELECT f FROM Funcao f", Funcao.class).getResultList();
	}

}