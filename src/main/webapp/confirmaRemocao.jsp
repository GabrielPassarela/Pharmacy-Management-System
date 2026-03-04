<%@page import="model.Remedio" %>
    <link rel="stylesheet" href="farmaciacss.css">

<!DOCTYPE html>
	<html>
	<head>
	<meta charset="UTF-8">
	<title>Remocao do Remédio</title>
	</head>
	<body>
		<h2>Remocao do Remédio</h2>
		
		<%
			Remedio remedio= (Remedio) request.getAttribute("remedio");
		
		%>
		
		<ul>
			<li>ID: <%= remedio.getId() %></li>
			<li>Nome: <%= remedio.getNome() %></li>
			<li>Preço: <%= remedio.getPreco() %></li>
			<li>Data de Vencimento: <%= remedio.getDataVencimento() %></li>
			<li>Tarja: <%= remedio.getTarja() %></li>
			<li>Descrição: <%= remedio.getDescricao() %></li>
					
		</ul>
		
		<p>Tem certeza que deseja remover esse remédio?</p>
		
		<form action="FarmaciaController"method = "post">
			<input type="hidden" name="id" value ="<%= remedio.getId() %>">
			<input type="submit" value="Remover" name="operacao">
		</form>
		<a href="index.html"><input type="button" value="Cancelar"></a>
		
		
		
		
		
	</body>
</html>