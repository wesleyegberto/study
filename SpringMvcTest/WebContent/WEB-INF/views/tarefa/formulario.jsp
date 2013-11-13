<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<body>
	<h3>Adicionar tarefas</h3>
	
	<form:errors path="tarefa.descricao" cssStyle="color:red;" />
	<form action="adicionaTarefa" method="post">
		Descrição: <br />
		<textarea name="descricao" rows="5" cols="100" style="height: 114px; width: 711px; "></textarea>
		<br /> <input type="submit" value="Adicionar">
	</form>
	
</body>
</html>