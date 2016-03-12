/* concessionaria.c
*
* Implementação de um sistema de controle de vendas de uma
* concessionária de automóveis.
*
* Ciro Cirne Trindade
* 15/05/2014
*/
#include <stdio.h>
#include "automovel.h"
#include "vendedor.h"
#include "venda.h"

#define CAD_AUTOMOVEL 1
#define LIST_TODOS_AUTOS 2
#define LIST_AUTOS_A_VENDA 3
#define CAD_VENDEDOR 4
#define LIST_VENDEDOR 5
#define CAD_VENDA 6
#define LIST_VENDAS_MES 7
#define LIST_VENDAS_VENDEDOR 8
#define SAIR 0

/* exibe o menu de opções do sistema e devolve a escolha do usuário */
int menu(char * [], int);

int main() {
	char * opcoes[] = { "Cadastrar automóvel",
		"Listar todos os automóveis",
		"Listar os automóveis a venda",
		"Cadastrar vendedor",
		"Listar vendedores",
		"Cadastrar venda",
		"Listar todas as vendas de um mês/ano",
		"Listar todas as vendas de um vendedor",
		"Sair do programa" };
	int op;
	do {
		op = menu(opcoes, sizeof(opcoes) / sizeof(char *));
		switch (op) {
			case CAD_AUTOMOVEL:
				cadastrar_automovel();
				break;
			case LIST_TODOS_AUTOS:
				listar_todos_automoveis();
				break;
			case LIST_AUTOS_A_VENDA:
				listar_automoveis_a_venda();
				break;
			case CAD_VENDEDOR:
				cadastrar_vendedor();
				break;
			case LIST_VENDEDOR:
				listar_vendedores();
				break;
			case CAD_VENDA:
				cadastrar_venda();
				break;
			case LIST_VENDAS_MES:
				listar_vendas_mes();
				break;
			case LIST_VENDAS_VENDEDOR:
				listar_vendas_vendedor();
				break;
			case SAIR:
				break;
			default:
			printf("\n\tOpção inválida!\n");
		} // fim do switch
	} while (op != SAIR);
	return 0;
}

int menu(char * opcoes[], int num) {
	int i, op;
	printf("\n\n\t\tCONCESSIONÁRIA DE AUTOMÓVEIS\n\n");
	for (i = 0; i < num-1; i++) {
		printf("\t%2d - %s\n", i + 1, opcoes[i]);
	}
	printf("\t%2d - %s\n", SAIR, opcoes[i]);
	printf("\tOpção: ");
	scanf("%d", &op);
	return op;
}
