package com.conferencia.controller;

import com.conferencia.model.Pessoa;
import com.conferencia.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public String listar(Model model,
                        @RequestParam(required = false) String nome,
                        @RequestParam(required = false) String igreja,
                        @RequestParam(required = false) Integer pagou,
                        @RequestParam(required = false) Integer entrou) {
        List<Pessoa> pessoas = pessoaService.buscarPorFiltros(nome, igreja, pagou, entrou);
        List<String> igrejas = pessoaService.buscarTodasIgrejas();
        
        model.addAttribute("pessoas", pessoas);
        model.addAttribute("igrejas", igrejas);
        model.addAttribute("totalPessoas", pessoas.size());
        model.addAttribute("filtroNome", nome);
        model.addAttribute("filtroIgreja", igreja);
        model.addAttribute("filtroPagou", pagou);
        model.addAttribute("filtroEntrou", entrou);
        return "lista";
    }

    @PostMapping("/confirmar-entrada/{id}")
    @ResponseBody
    public String confirmarEntrada(@PathVariable Long id) {
        pessoaService.confirmarEntrada(id);
        return "success";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Pessoa pessoa,
                        @RequestParam(required = false) boolean isPagou) {
        
        pessoa.setPagou(isPagou ? 1 : 0);
        
        if (pessoa.getId() == null) {
            pessoa.setEntrou(0);
        }
        
        pessoaService.salvar(pessoa);
        return "redirect:/";
    }

    @GetMapping("/novo")
    public String formulario(Model model) {
        model.addAttribute("pessoa", new Pessoa());
        model.addAttribute("edicao", false);
        return "formulario";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        pessoaService.buscarPorId(id).ifPresent(pessoa -> {
            model.addAttribute("pessoa", pessoa);
            model.addAttribute("edicao", true);
        });
        return "formulario";
    }

    @DeleteMapping("/deletar/{id}")
    @ResponseBody
    public String deletar(@PathVariable Long id) {
        pessoaService.deletar(id);
        return "success";
    }
} 