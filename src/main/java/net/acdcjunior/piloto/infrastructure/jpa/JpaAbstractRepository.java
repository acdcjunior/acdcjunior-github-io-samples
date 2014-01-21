package net.acdcjunior.piloto.infrastructure.jpa;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.acdcjunior.piloto.domain.BaseEntity;
import net.acdcjunior.piloto.domain.BaseRepository;

public abstract class JpaAbstractRepository<T extends BaseEntity> implements BaseRepository<T> {
	
	@PersistenceContext
	protected EntityManager em;

	@Override
	public void save(T t) {
		if (t.isNew()) {
			this.em.persist(t);
		} else {
			this.em.merge(t);
		}
	}
	
	@Override
	public void remove(T t) {  
		this.em.remove(t);  
	}

	@Override
	public T findById(Integer id) {
		return this.em.find(implEntityClass(), id);
	}
	
	@Override
	public List<T> findAll() {
		return this.em.createQuery("FROM "+implEntityClass().getSimpleName(), implEntityClass())
				      .getResultList();
	}

    @SuppressWarnings("unchecked")  
    private Class<T> implEntityClass() {  
         ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();  
         return (Class<T>) genericSuperclass.getActualTypeArguments()[0];  
    }
    
}