package net.acdcjunior.piloto.domain;

import java.util.List;

public interface BaseRepository<T extends BaseEntity> {
	
	void save(T t);

	T findById(Integer id);
    
    List<T> findAll();

	void remove(T t);

}