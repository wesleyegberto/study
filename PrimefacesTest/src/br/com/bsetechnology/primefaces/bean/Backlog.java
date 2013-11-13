package br.com.bsetechnology.primefaces.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class Backlog {
	private List<Tarefa> tarefas;
	
	@SuppressWarnings("deprecation")
	@PostConstruct // Chama quando o bean � inst�nciado
	public void preparaDados() {
		tarefas = new ArrayList<Tarefa>();
		tarefas.add(new Tarefa("Estudar Java", true, new Date(113, 05, 01)));
		tarefas.add(new Tarefa("Estudar JSP", false, null));
		tarefas.add(new Tarefa("Estudar JSF", false, null));
		tarefas.add(new Tarefa("Estudar Spring", true, new Date(113, 10, 06)));
		tarefas.add(new Tarefa("Estudar Primefaces", false, null));
	
	}

	// Ser� chamado varias vezes durante uma requisi��o
	public List<Tarefa> getTarefas() {
		return this.tarefas;
	}
}
