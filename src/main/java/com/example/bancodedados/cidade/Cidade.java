package com.example.bancodedados.cidade;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import java.util.Objects;

@Entity
public class Cidade {
//test
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @PositiveOrZero(message="O taxa deve ser preenchido")
    private double taxa;

    @NotEmpty(message="O UF deve ser preenchido")
    private String UF;

    @NotEmpty(message="O Nome deve ser preenchido")
    private String nome;

    public Cidade() {}

    public Cidade(String nome, double taxa, String UF) {
        this.nome = nome;
        this.taxa = taxa;
        this.UF = UF;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double gettaxa() {
        return taxa;
    }

    public void settaxa(double taxa) {
        this.taxa = taxa;
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof com.example.bancodedados.cidade.Cidade)) return false;
        com.example.bancodedados.cidade.Cidade cidade = (com.example.bancodedados.cidade.Cidade) o;
        return Double.compare(cidade.taxa, taxa) == 0 &&
                getId().equals(cidade.getId()) &&
                getUF().equals(cidade.getUF()) &&
                getNome().equals(cidade.getNome());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), taxa, getUF(), getNome());
    }
}
