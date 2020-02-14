package com.example.bancodedados.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.List;

@Service

public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> buscarClientes(){
        return  clienteRepository.todos(new Sort(Sort.Direction.ASC, "id"));
    }

    public Cliente inserir(Cliente cliente) {
        try {
            cliente = clienteRepository.save(cliente);
        } catch (ConstraintViolationException e) {

        }
        return cliente;
    }
    public Cliente inserirOuAlterar(Cliente cliente) {
            return clienteRepository.save(cliente);
    }

}
