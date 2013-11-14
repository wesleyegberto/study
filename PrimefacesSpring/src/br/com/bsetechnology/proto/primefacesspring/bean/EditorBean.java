package br.com.bsetechnology.proto.primefacesspring.bean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//@ManagedBean(name = "editorBean") // @Component consegui substituir
@Component
@Controller
//@Scope("session")
//@SessionScoped
public class EditorBean {	
	private String message = "Hello World from EditorBean ";
	@Autowired
	private Backlog backlog;
	
	public void setBacklog(Backlog backlog) {
		this.backlog = backlog;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Tarefa> getTarefasBse() {
		return this.backlog.getTarefas();
	}
	
	@RequestMapping("/function/listaTarefas")
	public String listaTarefas() {
		return "tarefas";
	}
}
