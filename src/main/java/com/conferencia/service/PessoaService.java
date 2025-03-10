package com.conferencia.service;

import com.conferencia.model.Pessoa;
import com.conferencia.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {
    
    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Pessoa> listarTodos() {
        return pessoaRepository.findAll();
    }

    public List<Pessoa> buscarPorFiltros(String nome, Integer patrocinador, Integer pagou, Integer entrou) {
        return pessoaRepository.findByFiltros(nome, patrocinador, pagou, entrou);
    }

    @Transactional
    public void confirmarEntrada(Long id) {
        pessoaRepository.findById(id).ifPresent(pessoa -> {
            if(pessoa.getPagou() == 0){
                pessoa.setPagou(1);
            }
            pessoa.setEntrou(1);
            pessoaRepository.save(pessoa);
        });
    }

    @Transactional
    public Pessoa salvar(Pessoa pessoa) {
        if (pessoa.getId() != null) {
            Pessoa pessoaExistente = pessoaRepository.findById(pessoa.getId())
                    .orElseThrow();
            
            if (pessoaExistente.getEntrou() == 1) {
                pessoa.setEntrou(1);
            }
        }
        
        return pessoaRepository.save(pessoa);
    }

    public Optional<Pessoa> buscarPorId(Long id) {
        return pessoaRepository.findById(id);
    }

    @Transactional
    public void deletar(Long id) {
        pessoaRepository.deleteById(id);
    }
} 