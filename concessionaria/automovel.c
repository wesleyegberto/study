#include <stdio.h>
#include <stdbool.h>
#include "automovel.h"

/** função que retorna um novo código para um automóvel */
int gera_novo_codigo_automovel() {
	int maiorCodigo = 0;
	FILE *file;
	automovel dados;

	// abre o arquivo
	if((file = fopen(ARQ_AUTOMOVEIS, "rb")) == NULL) {
		return 1;
	}
	while(!feof(file)) {
		// le o automovel
		fread(&dados, sizeof(automovel), 1, file);
		if(dados.codigo > maiorCodigo) {
			maiorCodigo = dados.codigo;
		}
	}
	fclose(file);

	return (maiorCodigo + 1);
}

/* função que cadastra um automóvel no arquivo */
void cadastrar_automovel() {
	FILE *file;
	automovel dados;
	dados.vendido = false;

	printf("\n\n========== Cadastro de automovel ==========\n");
	printf("Entre com a marca: ");
	getchar(); // para consumir o ENTER apertado apos a insercao da opcao
	gets(dados.marca);
	printf("Entre com o modelo: ");
	gets(dados.modelo);
	printf("Entre com o ano: ");
	scanf("%d", &dados.ano);
	printf("Entre com o preco: ");
	scanf("%f", &dados.preco);
	dados.codigo = gera_novo_codigo_automovel();

	// abre o arquivo
	if((file = fopen(ARQ_AUTOMOVEIS, "ab")) == NULL) {
		fprintf(stderr, "Erro ao abrir arquivo de automoveis.\n");
		return;
	}

	// salva o automovel
	fwrite(&dados, sizeof(automovel), 1, file);
	fclose(file);
}

void listar_todos_automoveis() {
	FILE *file;
	automovel dados;

	// abre o arquivo
	if((file = fopen(ARQ_AUTOMOVEIS, "rb")) == NULL) {
		fprintf(stderr, "Erro ao abrir arquivo de automoveis.\n");
		return;
	}
	printf("\n\n==== Automoveis cadastrados ====\n");
	printf("%s|%-21s|%-21s|%s\n", "Codigo", "Marca", "Modelo", "Preco"); // imprime o cabecalho
	fread(&dados, sizeof(automovel), 1, file);
	do {
		printf("%5d |%-21s|%-21s|R$ %.2f\n", dados.codigo, dados.marca, dados.modelo, dados.preco);		
		// le o automovel
		fread(&dados, sizeof(automovel), 1, file);
	} while(!feof(file));
	fclose(file);
}

void listar_automoveis_a_venda() {
	FILE *file;
	automovel dados;

	// abre o arquivo
	if((file = fopen(ARQ_AUTOMOVEIS, "rb")) == NULL) {
		fprintf(stderr, "Erro ao abrir arquivo de automoveis.\n");
		return;
	}
	printf("\n\n==== Automoveis a venda ====\n");
	printf("%s|%-21s|%-21s|%s\n", "Codigo", "Marca", "Modelo", "Preco"); // imprime o cabecalho
	fread(&dados, sizeof(automovel), 1, file);
	do {
		if(dados.vendido == false) {
			printf("%5d |%-21s|%-21s|R$ %.2f\n", dados.codigo, dados.marca, dados.modelo, dados.preco);		
		}	
		// le o automovel
		fread(&dados, sizeof(automovel), 1, file);
	} while(!feof(file));
	fclose(file);
}
