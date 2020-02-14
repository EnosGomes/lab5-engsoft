package com.example.bancodedados.frete;

import com.example.bancodedados.cidade.Cidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@Service

public class FreteService {

    @Autowired
    private FreteRepository freteRepository;

    public Frete inserir(Frete frete) throws FreteException {
        try {
            frete = freteRepository.save(frete);
        } catch (ConstraintViolationException e) {
            throw new FreteException(e);
        }
        return frete;
    }
    public Frete inserirOuAlterar(Frete frete) {
            return freteRepository.save(frete);
    }

    public List<Frete> buscarFretes(){
        return  freteRepository.todos(new Sort(Sort.Direction.ASC, "id"));
    }
    public Optional<Frete> buscarFreteId(long id){
        return  freteRepository.findById(id);
    }

    public void remover(Long id) {
        freteRepository.deleteById(id);
    }

    public Frete getMaiorValorFrete(){
        return freteRepository.maxValorFrete();
    }

    public Cidade getCidadeComMaiorNumeroFretes(){
        if( freteRepository.cidadeComMaisFrete().size() > 0 )
            return freteRepository.cidadeComMaisFrete().get(0).getCidade();
        return null;
    }
}
