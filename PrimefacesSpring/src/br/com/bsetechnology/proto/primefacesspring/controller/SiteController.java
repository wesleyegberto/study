package br.com.bsetechnology.proto.primefacesspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.bsetechnology.proto.primefacesspring.bean.Backlog;

@Controller
public class SiteController {


	@RequestMapping("listaTarefas")
	public String listaTarefas(Model model) {
		model.addAttribute("tarefas", new Backlog().getTarefas());
		return "tarefas";
	}
}
