package net.acdcjunior.piloto.repository;

import java.util.List;

import net.acdcjunior.piloto.domain.Funcao;

public interface FuncaoRepository {
	
	void save(Funcao funcao);

	Funcao findById(Integer id);
    
	Funcao findByNome(String nome);
    
    List<Funcao> findAllFuncoes();
    
}