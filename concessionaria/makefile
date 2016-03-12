trab2: concessionaria.o automovel.o vendedor.o venda.o
	gcc -o trab2 concessionaria.o automovel.o vendedor.o venda.o
concessionaria.o: concessionaria.c automovel.h vendedor.h venda.h
	gcc -c concessionaria.c
automovel.o: automovel.c automovel.h
	gcc -c automovel.c
vencedor.o: vendedor.c vendedor.h
	gcc -c vendedor.c
venda.o: venda.c venda.h automovel.h vendedor.h
	gcc -c venda.c
