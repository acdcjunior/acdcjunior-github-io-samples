package net.acdcjunior.piloto.domain;

import javax.persistence.*;

@Entity
@Table(name = "tb_funcao")
public class Funcao {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 50, unique = true)
    private String nome;

    public void setId(Integer id) { this.id = id; }
    public Integer getId() { return id; }

    public void setNome(String nome) { this.nome = nome; }
    public String getNome() { return nome; }

}