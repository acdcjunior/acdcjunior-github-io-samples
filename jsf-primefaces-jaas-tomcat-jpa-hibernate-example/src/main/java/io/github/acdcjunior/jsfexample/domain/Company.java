package io.github.acdcjunior.jsfexample.domain;

import javax.persistence.*;

@Entity
@Table(name = "COMPANY")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "[#"+getId()+"\t:"+getName()+"\t @"+hashCode()+"]";
    }

    @Column(length = 50)
    private String name;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

}