package com.conferencia.controller;

import com.conferencia.model.Pessoa;
import com.conferencia.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public String listar(Model model,
                        @RequestParam(required = false) String nome,
                        @RequestParam(required = false) Integer pagou,
                        @RequestParam(required = false) Integer entrou) {
        model.addAttribute("pessoas", pessoaService.buscarPorFiltros(nome, pagou, entrou));
        model.addAttribute("filtroNome", nome);
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
    public String salvar(@ModelAttribute Pessoa pessoa) {
        pessoaService.salvar(pessoa);
        return "redirect:/";
    }
} 