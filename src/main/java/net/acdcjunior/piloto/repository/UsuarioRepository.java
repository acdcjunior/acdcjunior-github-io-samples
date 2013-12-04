package net.acdcjunior.piloto.repository;

import java.util.List;

import net.acdcjunior.piloto.domain.Usuario;

public interface UsuarioRepository {
	
	void save(Usuario usuario);

	Usuario findById(Integer id);
    
    Usuario findByNome(String nome);
    
    List<Usuario> findAllUsuarios();
    
}