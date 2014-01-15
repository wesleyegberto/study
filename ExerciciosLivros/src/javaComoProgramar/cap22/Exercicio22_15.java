/**
 * @author Wesley Egberto de Brito Simulação de fila de supermercado
 */
package javaComoProgramar.cap22;

import java.util.*;

public class Exercicio22_15 {
	public static void main(String[] args) throws InterruptedException {
		Caixa caixa = new Caixa();
		Fila fila = new Fila();

		Random rnd = new Random(); // Um cliente terminará de ser atendido a
									// cada 1 a 4 minutos
		int timeProxCli = rnd.nextInt(4) + 1;
		int tempoEspera;

		for(int i = 1; i <= 720; i++) {
			// Verifica se é hora da chegada do cliente
			System.out.println("\t\tAtual: " + i + "; Prox: " + timeProxCli + "; Falta: "
					+ caixa.clienteAtendimento.tempoRestante + "; Num. Fila: " + fila.length);
			if(timeProxCli == i) {
				tempoEspera = rnd.nextInt(4) + 1;
				fila.addCliente(new Cliente(tempoEspera));
				timeProxCli += rnd.nextInt(4) + 1;
			}

			// Verifica se é hora do cliente em atendimento sair
			if(caixa.isAcabado())
				caixa.setCliente(fila.getNextCliente());
			else
				caixa.decrementTime();

			// Thread.sleep(1000);
		}
	}
}

class Caixa {
	Cliente clienteAtendimento = new Cliente(0);

	public void setCliente(Cliente c) {
		if(c == null)
			return;
		clienteAtendimento = c;
	}

	public void avisaSaida() {
		System.out.println("Cliente " + clienteAtendimento.idCliente + " sai do caixa.");
	}

	public boolean isAcabado() {
		if(clienteAtendimento.tempoRestante == 0)
			return true;
		else
			return false;
	}

	public void decrementTime() {
		clienteAtendimento.tempoRestante--;
	}
}

class Cliente {
	static int totalCliente = 0;
	final int idCliente;
	int tempoRestante;

	public Cliente(int temp) {
		idCliente = totalCliente++;
		tempoRestante = temp;
	}
}

class Fila {
	private Queue<Cliente> fila = new Queue<Cliente>();
	public int length = 0;

	public Cliente getNextCliente() {
		Cliente next;
		if(!fila.isEmpty()) {
			next = fila.dequeue();
			System.out.println(next.idCliente + " vai para o caixa.");
			this.length--;
			return next;
		}

		return null;
	}

	public void addCliente(Cliente c) {
		fila.enqueue(c);
		System.out.println("Cliente " + c.idCliente + " entrou na fila.");
		this.length++;
	}

	public boolean temCliente() {
		return !fila.isEmpty();
	}
}
