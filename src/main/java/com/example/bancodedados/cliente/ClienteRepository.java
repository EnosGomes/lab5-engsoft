package com.example.bancodedados.cliente;

import com.example.bancodedados.cliente.Cliente;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteRepository extends JpaRepository<com.example.bancodedados.cliente.Cliente, Long> {

    com.example.bancodedados.cliente.Cliente findFirstByNome(String nome);

    @Query("SELECT u FROM Cliente u WHERE u.telefone = ?1")
    com.example.bancodedados.cliente.Cliente buscaPor(String telefone);

    @Query(value = "From Cliente")
    List<com.example.bancodedados.cliente.Cliente> todos(Sort sort);
}
