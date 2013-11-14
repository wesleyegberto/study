package br.com.bsetechnology.proto.primefacesspring.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class Backlog implements Serializable {
	private static final long serialVersionUID = 2934941788195101662L;
	private List<Tarefa> tarefas;

	@SuppressWarnings("deprecation")
	public Backlog() {
		tarefas = new ArrayList<Tarefa>();
		tarefas.add(new Tarefa("Estudar Java", true, new Date(113, 05, 01)));
		tarefas.add(new Tarefa("Estudar JSP", false, null));
		tarefas.add(new Tarefa("Estudar JSF", false, null));
		tarefas.add(new Tarefa("Estudar Spring", true, new Date(113, 10, 06)));
		tarefas.add(new Tarefa("Estudar Primefaces", false, null));

	}

	public List<Tarefa> getTarefas() {
		return this.tarefas;
	}
}
