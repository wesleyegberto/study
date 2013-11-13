<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
	<link type="text/css" href="resources/css/jquery-ui.css" rel="stylesheet" />
	<style type="text/css">
		td { padding: 2px;}
		tr { border-top:solid 1px #ccc; }
	</style>
	<script type="text/javascript" src="resources/js/jquery.js"></script>
	<script type="text/javascript" src="resources/js/jquery-ui.js"></script>
	<script type="text/javascript">
	  function finalizaAgora(id) {
	  	$.get("finalizaTarefa?id=" + id, function() {
	  		alert("Tarefa finalizada com sucesso.");
	  		$("#tarefa_" + id).html("Finalizado");
	  	});
	  }
	  function removeTarefa(id) {
	  	$.get("removeTarefa?id=" + id, function(response) {
	  		alert("Tarefa removida com sucesso.");
	  		$("#tarefa-" + id).html(response);
	  	});
	  }
	</script>
</head>
<body>
	<a href="novaTarefa">Criar nova tarefa</a>
	<br />
	<br />
	<table style="border: 1px solid #EEE;">
		<tr>
			<th>Id</th>
			<th>Descrição</th>
			<th>Finalizado?</th>
			<th>Data de finalização</th>
		</tr>

		<c:forEach items="${tarefas}" var="tarefa">
			<tr id="tarefa-${tarefa.id}">
				<td>${tarefa.id}</td>
				<td>${tarefa.descricao}</td>
				
				<c:choose>
					<c:when test="${tarefa.finalizado eq true}">
						<td>Finalizado</td>
						<td><fmt:formatDate value="${tarefa.dataFinalizacao.time}" pattern="dd/MM/yyyy" /></td>
					</c:when>
					<c:otherwise>
						<td><a href="#" onclick="finalizaAgora(${tarefa.id})">Finalizar agora</a></td>
						<td> </td>
					</c:otherwise>
				</c:choose>
				
				<td><a href="mostraTarefa?id=${tarefa.id}">Alterar</a></td>
				<td><a href="#" onClick="removeTarefa(${tarefa.id});">Remover</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>