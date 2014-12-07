package net.acdcjunior.piloto.test;

import net.acdcjunior.piloto.domain.usuario.Funcao;
import net.acdcjunior.piloto.domain.usuario.Usuario;

/**
 * Esta classe contem, como objetos, as linhas inseridas no banco de testes.
 * 
 * Por exemplo, a linha:
 * - INSERT INTO `TB_USUARIO` (`ID`, `NOME`) VALUES (1, 'Charles Francis "Professor" Xavier');
 * Equivale ao objeto:
 * {@link PilotoTestDados#usuario_1}.
 */
public class PilotoTestDados {
	
	public static final Funcao funcao_1 = new Funcao();
	public static final Funcao funcao_2 = new Funcao();
	public static final Funcao funcao_3 = new Funcao();
	public static final Usuario usuario_1 = new Usuario();
	public static final Usuario usuario_2 = new Usuario();
	public static final Usuario usuario_3 = new Usuario();
	
	static {
		buildFuncao(funcao_1, 1, "Lider");
		buildFuncao(funcao_2, 2, "Mutante");
		buildFuncao(funcao_3, 3, "Barbudo");
		buildUsuario(usuario_1, 1, "Charles Francis \"Professor\" Xavier", funcao_1, funcao_2);
		buildUsuario(usuario_2, 2, "John \"Wolverine\" Logan", funcao_2, funcao_3);
		buildUsuario(usuario_3, 3, "Papai Smurf", funcao_1, funcao_3);
	}
	
	public static void buildFuncao(Funcao funcao, Integer id, String nome) {
		funcao.setId(id);
		funcao.setNome(nome);
	}

	public static void buildUsuario(Usuario usuario, Integer id, String nome, Funcao... funcoes) {
		usuario.setId(id);
		usuario.setNome(nome);
		for (Funcao funcao : funcoes) { usuario.addFuncao(funcao); }
	}

}