package com.example.bancodedados.cidade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.List;

@Service

public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    public List<Cidade> buscarCidades(){
        return  cidadeRepository.todos(new Sort(Sort.Direction.ASC, "id"));
    }

    public Cidade inserir(Cidade Cidade) {
        try {
            Cidade = cidadeRepository.save(Cidade);
        } catch (ConstraintViolationException e) {

        }
        return Cidade;
    }
    public Cidade inserirOuAlterar(Cidade Cidade) {
            return cidadeRepository.save(Cidade);
    }

}
