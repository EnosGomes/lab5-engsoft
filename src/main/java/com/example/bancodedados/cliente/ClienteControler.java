package com.example.bancodedados.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/clientecontroler")
public class ClienteControler {

        @Autowired
        private ClienteService clienteService;

        @GetMapping("/")
        public ResponseEntity<List<Cliente>> Clientes(){
            List<Cliente> clientes = clienteService.buscarClientes();
            return ResponseEntity.ok(clientes);
        }


        @PostMapping("/inserir")
        public ResponseEntity<Cliente> inserir(@RequestBody @Valid Cliente cliente) throws URISyntaxException{
            cliente = clienteService.inserirOuAlterar(cliente);
            return new ResponseEntity<>(cliente, HttpStatus.CREATED);
        }

       

    }

