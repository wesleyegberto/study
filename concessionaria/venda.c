#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include "venda.h"
#include "automovel.h"
#include "vendedor.h"

/**
 * struct para auxiliar a listagem das vendas de um determinado vendedor,
 * o rankData eh uma var usada para ordenar a venda. Uma venda do dia 05/06/2015 vira 20150605, assim a ordenacao fica mais simples.
 */
typedef struct { long rankData; venda *registro; struct NoVenda *ant; struct NoVenda *prox; } NoVenda;

/** pesquisa e retorna um automovel pelo codigo, retorna NULL se não encontrar */
automovel* get_automovel_por_codigo(int codigo) {
	// var para retornar o automovel encontrado
	automovel *automovelEncontrado = NULL;

	FILE *file;
	automovel dados;

	// abre o arquivo de automoveis
	if((file = fopen(ARQ_AUTOMOVEIS, "rb")) == NULL) {
		fprintf(stderr, "Erro ao abrir arquivo de automoveis.\n");
		return;
	}
	// percorre os registros para encontrar o automovel com codigo recebido
	while(!feof(file)) {
		// le o automovel
		fread(&dados, sizeof(automovel), 1, file);
		// compra o codigo do automovel lido com o codigo recebido
		if(dados.codigo == codigo) {
			// seta o automovel encontrado e sai do loop
			automovelEncontrado = &dados;
			break;
		}
	}
	fclose(file);
	// retorna o automovel encontrado ou NULL
	return automovelEncontrado;
}

/** função que salva o automovel que foi vendido */
void alterar_automovel_para_vendido(int codigoAuto) {
	FILE *file;
	automovel registro;

	// abre o arquivo de automoveis
	if((file = fopen(ARQ_AUTOMOVEIS, "r+b")) == NULL) {
		fprintf(stderr, "Erro ao abrir arquivo de automoveis.\n");
		return;
	}
	// veriavel para identificar a posicao do registro para alteracao
	long posicao = 0;
	// percorre os registros para encontrar o automovel com codigo recebido
	while(!feof(file)) {
		// le o automovel
		fread(&registro, sizeof(automovel), 1, file);
		// compra o codigo do automovel lido com o codigo do automovel recebido
		/*printf("Codigo em ");
		printf("[%ld]", posicao);
		printf("%d", registro.codigo);
		printf(" - %d\n", codigoAuto);*/
		if(registro.codigo == codigoAuto) {
			// alterar o automovel para vendido
			registro.vendido = true;
			// volta pro inicio do arquivo
			rewind(file);
			// avanca posicao em que o registro foi encontrado
			fseek(file, posicao, SEEK_SET);
			// salva o automovel
			fwrite(&registro, sizeof(automovel), 1, file);
			break;
		}
		// incrementa a posicao somando o tamanho do registro
		posicao = posicao + sizeof(automovel);
	}
	fclose(file);
}

/** pesquisa e retorna um vendedor pelo codigo, retorna NULL se não encontrar */
vendedor* get_vendedor_por_codigo(int codigo) {
	// var para retornar o vendedor encontrado
	vendedor *vendedorEncontrado = NULL;
	FILE *file;
	vendedor dados;
	// abre o arquivo
	if((file = fopen(ARQ_VENDEDORES, "rb")) == NULL) {
		fprintf(stderr, "Erro ao abrir arquivo de vendedores.\n");
		return;
	}
	// percorre os registros para encontrar o vendedor com codigo recebido
	while(!feof(file)) {
		// le o vendedor
		fread(&dados, sizeof(vendedor), 1, file);
		// compra o codigo do vendedor lido com o codigo recebido
		if(dados.codigo == codigo) {
			// seta o vendedor encontrado e sai do loop
			vendedorEncontrado = &dados;
			break;
		}		
	}
	fclose(file);
	// retorna o vendedor encontrado ou NULL
	return vendedorEncontrado;
}

/* função que cadastra uma venda no arquivo */
void cadastrar_venda() {
	venda vendaAtual;
	vendaAtual.cod_automovel = -1; // flag para deixar invalido
	vendaAtual.cod_vendedor = -1;
	automovel *automovelVenda = NULL;
	vendedor *vendedorVenda = NULL;

	printf("\n\n========== Venda iniciada ==========\n");
	// carrega o automovel
	do {
		// le o codigo do automovel
		printf("Entre com o codigo do automovel ou 0 para sair: ");
		scanf("%d", &vendaAtual.cod_automovel);
		// pesquisa o automovel pelo codigo recebido
		automovelVenda = get_automovel_por_codigo(vendaAtual.cod_automovel);
		// verifica se foi retornado um automovel
		if(automovelVenda != NULL) {
			// verifica se o automovel ja foi vendido, se ja informa o usuario senao mostra os dados
			if(automovelVenda->vendido == true) {
				printf("Automovel ja foi vendido.\n");
				automovelVenda = NULL;
			} else {
				printf("Automovel: %s %s - %d - ", automovelVenda->marca, automovelVenda->modelo, automovelVenda->ano);
				printf("R$ %.2f\n", automovelVenda->preco);
			}
		} else if(vendaAtual.cod_automovel != 0) { // verifica se o usuario nao saiu
			printf("Codigo do automovel invalido.\n");
		}
	} while(automovelVenda == NULL && vendaAtual.cod_automovel != 0);
	// se informou para sair da venda
	if(vendaAtual.cod_automovel == 0) {
		printf("\n========== Venda cancelada ==========\n\n");
		return;
	}

	// carrega o vendedor
	do {
		// le o codigo do vendedor
		printf("Entre com o codigo do vendedor ou 0 para sair: ");
		scanf("%d", &vendaAtual.cod_vendedor);
		// pesquisa o vendedor pelo codigo recebido
		vendedorVenda = get_vendedor_por_codigo(vendaAtual.cod_vendedor);
		// verifica se foi retornado um vendedor
		if(vendedorVenda != NULL) {
			printf("Vendedor: %s.\n", vendedorVenda->nome);
		} else if(vendaAtual.cod_vendedor != 0) {
			printf("Codigo do vendedor invalido.\n");
		}
	} while(vendedorVenda == NULL && vendaAtual.cod_vendedor != 0);
	// se informou para sair da venda
	if(vendaAtual.cod_vendedor == 0) {
		printf("\n========== Venda cancelada ==========\n\n");
		return;
	}
	// le a data da venda
	printf("Entre com a data da venda (use espaco no lugar da /): ");
	scanf("%d %d %d", &vendaAtual.dt.dia, &vendaAtual.dt.mes, &vendaAtual.dt.ano);

	automovelVenda->vendido = true;
	// altera o status do automovel para vendido
	alterar_automovel_para_vendido(vendaAtual.cod_automovel);

	//  abre o arquivo de vendas para adicionar a nova venda
	FILE *file;
	if((file = fopen(ARQ_VENDAS, "ab")) == NULL) {
		fprintf(stderr, "Erro ao abrir arquivo de vendas.\n");
		return;
	}
	fwrite(&vendaAtual, sizeof(venda), 1, file); // salva a venda
	fclose(file);
	
	printf("========== Venda finalizada ==========\n\n");
}

/* função que lista todas as vendas realizadas em um mês/ano */
void listar_vendas_mes() {
	int mes; int ano;
	// le a data para filtrar as vendas
	printf("\n\nEntre com o mes e ano para a pesquisa, separados por espaco: ");
	scanf("%d %d", &mes, &ano);

	FILE *file;
	venda registroVenda;
	automovel *automovelVenda = NULL;
	vendedor *vendedorVenda = NULL;
	// abre o arquivo
	if((file = fopen(ARQ_VENDAS, "rb")) == NULL) {
		fprintf(stderr, "\nErro ao abrir arquivo de vendas.\n");
		return;
	}
	float valorTotal = 0.0;
	printf("\n%-11s|%-21s|%-21s|%-5s|%-14s|%s\n", "Data", "Marca", "Modelo", "Ano", "Preco", "Vendedor"); // imprime o cabecalho
	fread(&registroVenda, sizeof(venda), 1, file); // le a venda
	do {
		// so exibe se a data da venda for maior que o filtro aplicado
		if(registroVenda.dt.ano > ano || (registroVenda.dt.ano == ano && registroVenda.dt.mes >= mes)) {
			printf("%02d/%02d/%02d ", registroVenda.dt.dia, registroVenda.dt.mes, registroVenda.dt.ano);
			automovelVenda = get_automovel_por_codigo(registroVenda.cod_automovel);
			if(automovelVenda != NULL) {
				valorTotal = valorTotal + automovelVenda->preco;
				printf("|%-21s", automovelVenda->marca);
				printf("|%-21s", automovelVenda->modelo);
				printf("|%04d ", automovelVenda->ano);
				printf("|R$ % 10.2f ", automovelVenda->preco);
			} else {
				printf("|%-57s", "Automovel nao encontrado");
			}
			vendedorVenda = get_vendedor_por_codigo(registroVenda.cod_vendedor);
			printf("|%-42s\n", vendedorVenda->nome);
		}
		fread(&registroVenda, sizeof(venda), 1, file); // le a venda
	} while(!feof(file));
	printf("\nValor total:\t\tR$ %.2f", valorTotal);
	fclose(file);
}

/* função que lista todas as vendas realizadas por um vendedor */
void listar_vendas_vendedor() {
	int codVendedor;
	vendedor *vendedorVenda = NULL;
	// le a data para filtrar as vendas
	printf("\n\nEntre com o codigo do vendedor: ");
	scanf("%d", &codVendedor);

	vendedorVenda = get_vendedor_por_codigo(codVendedor);
	if(vendedorVenda == NULL) {
		printf("\nVendedor invalido.\n");
		return;
	}
	printf("Vendas efetuadas pelo %s\n", vendedorVenda->nome);
	float valorTotal = 0.0;
	FILE *file;
	venda registroVenda;
	// abre o arquivo
	if((file = fopen(ARQ_VENDAS, "rb")) == NULL) {
		fprintf(stderr, "\nErro ao abrir arquivo de vendas.\n");
		return;
	}
	// struct que conterão as vendas (usado para auxiliar a ordenacao)
	NoVenda *head = NULL;
	NoVenda *ultima = NULL;
	NoVenda *aux = NULL;

	fread(&registroVenda, sizeof(venda), 1, file); // le a venda
	do {
		// so exibe se a data da venda for maior que o filtro aplicado
		if(registroVenda.cod_vendedor == codVendedor) {
			if(head == NULL) {
				head = malloc(sizeof(NoVenda));
				aux = head;
			} else {
				// cria um noh para o proximo (o aux sempre apontara para o ultima venda da lista)
				aux = malloc(sizeof(NoVenda));
				// vincula o prox do noh anterior com o novo noh
				ultima->prox = (struct NoVenda *) aux; // o cast para (struct NoVenda *) é para eliminar os warnings do compilador
			}
			// efetua o calculo do rank
			aux->rankData = (registroVenda.dt.ano * 10000) + (registroVenda.dt.mes * 100) + registroVenda.dt.dia ;
			// cria um novo noh pois o registroVenda eh usado no loop de leitura (e sempre sera alterado)
			aux->registro = malloc(sizeof(venda));
			aux->registro->dt.dia = registroVenda.dt.dia;
			aux->registro->dt.mes = registroVenda.dt.mes;
			aux->registro->dt.ano = registroVenda.dt.ano;
			aux->registro->cod_automovel = registroVenda.cod_automovel;
			aux->registro->cod_vendedor = registroVenda.cod_vendedor;
			// vincula o noh anterior
			aux->ant = (struct NoVenda *) ultima;
			aux->prox = NULL;
			// guarda o ultimo noh adicionado
			ultima = aux;
		}
		fread(&registroVenda, sizeof(venda), 1, file); // le a venda
	} while(!feof(file));


	// inicia a ordenacao por Bubble Sort (o registro de maior data vai sendo jogado para a direita)
	aux = head; // armazenara o noh atual
	ultima = NULL; // armazenara o noh posterior ao noh atual
	int rankAux;
	venda *regitroAux;
	// percore a lista de registro de venda
	while(aux != NULL) {
		ultima = (NoVenda *) aux->prox;
		// percorre a lista de registro de venda a partir da posicao inicial
		while(ultima != NULL) {
			// se for maior efetua a troca das posicoes
			if(aux->rankData > ultima->rankData) {
				rankAux = aux->rankData;
				aux->rankData = ultima->rankData;
				ultima->rankData = rankAux;
				regitroAux = aux->registro;
				aux->registro = ultima->registro;
				ultima->registro = regitroAux;
			}
			ultima = (NoVenda *) ultima->prox;
		}
		aux = (NoVenda *) aux->prox;
	}
 
	// percorre as vendas filtradas e exibe
	aux = head;
	automovel *automovelVenda = NULL; // var auxiliar para carregar o automovel
	printf("\n%-11s|%-21s|%-21s|%-5s|%-14s\n", "Data", "Marca", "Modelo", "Ano", "Preco"); // imprime o cabecalho
	while(aux != NULL) {
		printf("%02d/%02d/%02d ", aux->registro->dt.dia, aux->registro->dt.mes, aux->registro->dt.ano);
		automovelVenda = get_automovel_por_codigo(aux->registro->cod_automovel);
		if(automovelVenda != NULL) {
			valorTotal = valorTotal + automovelVenda->preco;
			printf("|%-21s", automovelVenda->marca);
			printf("|%-21s", automovelVenda->modelo);
			printf("|%04d ", automovelVenda->ano);
			printf("|R$ %.2f\n", automovelVenda->preco);
		} else {
			printf("|%-57s\n", "Automovel nao encontrado");
		}
		// avanca para a proxima venda
		aux = (NoVenda *) aux->prox;
	}
	printf("\nValor total vendido:\t\tR$ %.2f", valorTotal);

	fclose(file);
}
