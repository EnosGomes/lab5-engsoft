package com.example.bancodedados.cliente;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message="O endereco deve ser preenchido")
    private String endereco;

    @NotEmpty(message="O Telefone deve ser preenchido")
    private String telefone;

    @NotEmpty(message="O Nome deve ser preenchido")
    private String nome;

    public Cliente() {}

    public Cliente(String nome, String endereco, String telefone) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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
        if (!(o instanceof com.example.bancodedados.cliente.Cliente)) return false;
        com.example.bancodedados.cliente.Cliente cliente = (com.example.bancodedados.cliente.Cliente) o;
        return getId().equals(cliente.getId()) &&
                getEndereco().equals(cliente.getEndereco()) &&
                getTelefone().equals(cliente.getTelefone()) &&
                getNome().equals(cliente.getNome());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEndereco(), getTelefone(), getNome());
    }
}
