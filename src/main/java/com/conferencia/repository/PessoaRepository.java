package com.conferencia.repository;

import com.conferencia.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    @Query("SELECT p FROM Pessoa p WHERE " +
           "(COALESCE(:nome, '') = '' OR LOWER(p.nome) LIKE LOWER(CONCAT('%', :nome, '%'))) AND " +
           "(COALESCE(:igreja, '') = '' OR p.igreja = :igreja) AND " +
           "(:pagou IS NULL OR p.pagou = :pagou) AND " +
           "(:entrou IS NULL OR p.entrou = :entrou) " +
           "ORDER BY p.nome ASC")
    List<Pessoa> findByFiltros(@Param("nome") String nome,
                              @Param("igreja") String igreja,
                              @Param("pagou") Integer pagou,
                              @Param("entrou") Integer entrou);

    @Query("SELECT DISTINCT p.igreja FROM Pessoa p WHERE p.igreja IS NOT NULL AND p.igreja <> '' ORDER BY p.igreja")
    List<String> findAllIgrejas();
}