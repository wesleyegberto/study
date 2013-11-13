package br.com.caelum.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.caelum.bean.Tarefa;
import br.com.caelum.dao.JdbcTarefaDao;

@Controller
public class TarefasController {

	private final JdbcTarefaDao dao;

	@Autowired
	public TarefasController(JdbcTarefaDao dao) {
		this.dao = dao;
	}

	@RequestMapping("novaTarefa")
	public String form() {
		return "tarefa/formulario";
	}

	@RequestMapping("adicionaTarefa")
	public String adiciona(@Valid Tarefa tarefa, BindingResult result) {
		if(result.hasFieldErrors()) {
			return "tarefa/formulario";
		}

		dao.adiciona(tarefa);

		return "tarefa/adicionada";
	}

	@RequestMapping("listaTarefas")
	public String lista(Model model) {
		model.addAttribute("tarefas", dao.lista());
		return "tarefa/lista";
	}

	@RequestMapping("removeTarefa")
	public String remove(Tarefa tarefa) {
		dao.remove(tarefa);
		// return "forward:listaTarefas"; // Redireciona no servidor
		return "redirect:listaTarefas"; // Redireciona no cliente
	}

	@RequestMapping("mostraTarefa")
	public String mostra(Long id, Model model) {
		model.addAttribute("tarefa", dao.buscaPorId(id));
		return "tarefa/mostra";
	}

	@RequestMapping("alteraTarefa")
	public String altera(Tarefa tarefa) {
		dao.atualiza(tarefa);
		return "redirect:listaTarefas";
	}

	@RequestMapping("finalizaTarefa")
	public void finaliza(Long id, Model model) {
		dao.finaliza(id);
		model.addAttribute("tarefa", dao.buscaPorId(id));
	}
}
