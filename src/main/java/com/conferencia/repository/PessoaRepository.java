package com.conferencia.repository;

import com.conferencia.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
       @Query("SELECT p FROM Pessoa p WHERE " +
               "(COALESCE(:nome, '') = '' OR LOWER(p.nome) LIKE LOWER(CONCAT('%', :nome, '%'))) AND " +
               "(:patrocinador IS NULL OR p.patrocinador = :patrocinador) AND " +
               "(:pagou IS NULL OR p.pagou = :pagou) AND " +
               "(:entrou IS NULL OR p.entrou = :entrou) " +
               "ORDER BY p.nome ASC")
       List<Pessoa> findByFiltros(@Param("nome") String nome,
                                  @Param("patrocinador") Integer patrocinador,
                                  @Param("pagou") Integer pagou,
                                  @Param("entrou") Integer entrou);
}