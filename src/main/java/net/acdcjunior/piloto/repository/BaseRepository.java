package net.acdcjunior.piloto.repository;

import java.util.List;

import net.acdcjunior.piloto.domain.BaseEntity;

public interface BaseRepository<T extends BaseEntity> {
	
	void save(T t);

	T findById(Integer id);
    
    List<T> findAll();

	void remove(T t);

}