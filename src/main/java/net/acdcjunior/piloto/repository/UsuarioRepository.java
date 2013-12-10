package net.acdcjunior.piloto.repository;

import net.acdcjunior.piloto.domain.Usuario;

public interface UsuarioRepository extends BaseRepository<Usuario> {
	
    Usuario findByNome(String nome);
    
}