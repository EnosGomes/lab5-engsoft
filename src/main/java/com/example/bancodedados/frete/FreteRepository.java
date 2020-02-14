package com.example.bancodedados.frete;

import com.example.bancodedados.cidade.CidadeQtdFrete;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FreteRepository extends JpaRepository<Frete, Long> {


    @Query("FROM Frete u WHERE u.cliente.nome = ?1 order by u.valor")
    List<Frete> buscaPor(String nome);

    @Query(value = "From Frete u order by u.valor")
    List<Frete> todos(Sort sort);

    @Query(value = "select  * From Frete u order by u.valor desc limit 1", nativeQuery = true)
    Frete maxValorFrete();

    @Query("select new com.example.bancodedados.cidade.CidadeQtdFrete( u.cidade , count(u)) from Frete u group by u.cidade order by  count(u) desc")
    List<CidadeQtdFrete> cidadeComMaisFrete();
}
