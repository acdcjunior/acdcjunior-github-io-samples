package net.acdcjunior.piloto.domain.usuario;

import net.acdcjunior.piloto.domain.BaseRepository;

public interface UsuarioRepository extends BaseRepository<Usuario> {
	
    Usuario findByNome(String nome);
    
}