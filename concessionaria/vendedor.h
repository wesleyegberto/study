/* vendedor.h
*
* Define o tipo vendedor o os protótipos das operações realizadas
* sobre esse tipo.
*
* Ciro Cirne Trindade
* 15/05/2014
*/

#ifndef _VENDEDOR_H
#define _VENDEDOR_H
#define ARQ_VENDEDORES "vendedores.dat"

typedef struct {
	int codigo; // código do vendedor
	char nome[41]; // nome do vendedor
} vendedor;

/* função que cadastra um vendedor no arquivo */
void cadastrar_vendedor();

/* função que lista todos os vendedores cadastrados */
void listar_vendedores();

#endif