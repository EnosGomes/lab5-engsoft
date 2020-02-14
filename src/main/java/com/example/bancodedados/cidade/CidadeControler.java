package com.example.bancodedados.cidade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/cidadecontroler")
public class CidadeControler {

        @Autowired
        private CidadeService cidadeService;

        @GetMapping("/")
        public ResponseEntity<List<Cidade>> Cidades(){
            List<Cidade> cidades = cidadeService.buscarCidades();
            return ResponseEntity.ok(cidades);
        }


        @PostMapping("/inserir")
        public ResponseEntity<Cidade> inserir(@RequestBody @Valid Cidade cidade) throws URISyntaxException{
            cidade = cidadeService.inserirOuAlterar(cidade);
            return new ResponseEntity<>(cidade, HttpStatus.CREATED);
        }

       

    }

