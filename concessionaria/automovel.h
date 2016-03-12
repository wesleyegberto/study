/* automovel.h
*
* Define o tipo automovel e os protótipos das operações realizadas
* sobre esse tipo.
*
* Ciro Cirne Trindade
* 15/05/2014
*/

#ifndef _AUTOMOVEL_H
#define _AUTOMOVEL_H

#include <stdbool.h>

#define ARQ_AUTOMOVEIS "automoveis.dat"
#define ARQ_A_VENDA "avenda.txt"

typedef struct {
	int codigo; // código do automóvel
	char marca[21]; // marca do automóvel, por exemplo, Fiat, Ford, VW
	char modelo[21]; // modelo do automóvel, por exemplo, Palio, Fiesta, Gol
	int ano; // ano de fabricação do automóvel
	float preco; // preço de venda do automóvel
	bool vendido; // indica se o automóvel foi vendido ou não
} automovel;

/* função que cadastra um automóvel no arquivo */
void cadastrar_automovel();

/* função que lista todos os automóveis cadastrados */
void listar_todos_automoveis();

/* função que lista os automóveis a venda */
void listar_automoveis_a_venda();
#endif
