#include <stdio.h>
#include "vendedor.h"

/** função que retorna um novo código para um vendedor */
int gera_novo_codigo_vendedor() {
	int maiorCodigo = 0;
	FILE *file;
	vendedor dados;

	// abre o arquivo
	if((file = fopen(ARQ_VENDEDORES, "rb")) == NULL) {
		return 1;
	}
	while(!feof(file)) {
		// le o vendedor
		fread(&dados, sizeof(vendedor), 1, file);
		if(dados.codigo > maiorCodigo) {
			maiorCodigo = dados.codigo;
		}
	}
	fclose(file);

	return (maiorCodigo + 1);
}

/* função que cadastra um vendedor no arquivo */
void cadastrar_vendedor() {
	FILE *file;
	vendedor dados;

	printf("\n\n========== Cadastro de vendedor ==========\n");
	printf("Entre com o nome do vendedor: ");
	getchar(); // para consumir o ENTER apertado apos a insercao da opcao
	gets(dados.nome);
	dados.codigo = gera_novo_codigo_vendedor();

	// abre o arquivo
	if((file = fopen(ARQ_VENDEDORES, "ab")) == NULL) {
		fprintf(stderr, "Erro ao abrir arquivo de vendedores.\n");
		return;
	}

	// salva o vendedor
	fwrite(&dados, sizeof(vendedor), 1, file);
	fclose(file);
}

/* função que lista todos os vendedores cadastrados */
void listar_vendedores() {
	FILE *file;
	vendedor dados;

	// abre o arquivo
	if((file = fopen(ARQ_VENDEDORES, "rb")) == NULL) {
		fprintf(stderr, "Erro ao abrir arquivo de vendedores.\n");
		return;
	}
	printf("\n\n==== Vendedores cadastrados ====\n");
	printf("%s|%-41s\n", "Codigo", "Nome"); // imprime o cabecalho
	// le o vendedor
	fread(&dados, sizeof(vendedor), 1, file);
	do {
		printf("%5d |%-41s\n", dados.codigo, dados.nome);
		// le o vendedor
		fread(&dados, sizeof(vendedor), 1, file);
	} while(!feof(file));
	fclose(file);
}
