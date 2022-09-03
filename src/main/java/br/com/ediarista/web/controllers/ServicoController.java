package br.com.ediarista.web.controllers;

import br.com.ediarista.core.enums.Icone;
import br.com.ediarista.core.models.Servico;
import br.com.ediarista.core.repositories.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/servicos")
public class ServicoController {

    @Autowired
    private ServicoRepository repository;

    @GetMapping
    public ModelAndView buscarTodos(){
        var modelAndView = new ModelAndView("admin/servico/lista");

        modelAndView.addObject("servicos",repository.findAll());

        return modelAndView;
    }

    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        var modelAndView = new ModelAndView("admin/servico/form");

        modelAndView.addObject("servico", new Servico());
        return modelAndView;
    }

    @PostMapping("/cadastrar")
    public String cadastrar(Servico servico) {
        repository.save(servico);
        return "redirect:/admin/servicos";
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id){
        var modelAndView = new ModelAndView("admin/servico/form");
        modelAndView.addObject("servico", repository.getById(id));
        return modelAndView;
    }

    @PostMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Servico servico){
        repository.save(servico);
        return "redirect:/admin/servicos";
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/admin/servicos";
    }

    @ModelAttribute("icones")
    public Icone[] getIcones() {
        return Icone.values();
    }
}
