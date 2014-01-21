package net.acdcjunior.piloto.domain.usuario;

import javax.persistence.*;

import net.acdcjunior.piloto.domain.BaseEntity;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_usuario") 
public class Usuario extends BaseEntity {

	@Column(length = 50, unique = true)
    private String nome;
    
	@ManyToMany
	@JoinTable(name="tb_usuario_funcao", joinColumns={@JoinColumn(name="usuario_id")}, inverseJoinColumns={@JoinColumn(name="funcao_id")})
    private final Set<Funcao> funcoes = new HashSet<Funcao>();

    public void setNome(String nome) { this.nome = nome; }
    public String getNome() { return nome; }

    public boolean addFuncao(Funcao funcao) { return funcoes.add(funcao); }
    public Set<Funcao> getFuncoes() { return funcoes; }
    
}