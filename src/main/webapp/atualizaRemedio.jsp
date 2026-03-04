<%@page import="model.Remedio" %>
    <link rel="stylesheet" href="farmaciacss.css">

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Atualiza Remédio</title>
</head>
<body>
	<h2>Atualização do Remédio</h2>
	
	<%
		Remedio remedio = (Remedio) request.getAttribute("remedio");
	%>
	<form action="FarmaciaController"method="post">
	
	
			<label for = "id">ID:</label>
			<input type="number" id="id" name="id" value="<%= remedio.getId() %>"readonly><br><br>
			
			<label for="nomeId">Nome:</label>
			<input type="text" id="nomeId" name="nome" value="<%= remedio.getNome() %>"><br><br>
			
			<label for = "preco">Preço:</label>
			<input type="number" id="preco" name="preco" step = 0.1 value="<%= remedio.getPreco() %> "><br><br>
			
			<label for = "data_vencimento">Data de vencimento:</label>
			<input type="date" id="data_vencimento" name="dataVencimento" value="<%= remedio.getDataVencimento() %>"><br><br>
			
			<label for="tarja">Tarja do remédio:<br></label>
					<input type="radio" id="tarja Branca" name="tarja" value="tarja Branca">
					<label for="tarja Branca">Tarja Branca</label>
					<input type="radio" id="tarja Amarela" name="tarja" value="tarja Amarela">
					<label for="tarja Amarela">Tarja Amarela</label>
					<input type="radio" id="tarja Vermelha" name="tarja" value="tarja Vermelha">
					<label for="tarja Vermelha">Tarja Vermelha:</label><br><br>
					<input type="radio" id="tarja Preta" name="tarja" value="tarja Preta">
					<label for="tarja Preta">Tarja Preta:</label><br><br>
					<input type="radio" id="Tarja Amarela/Vermelha/Preta" name="tarja" value="Tarja Amarela/Vermelha/Preta">
					<label for="Tarja Amarela/Vermelha/Preta">Tarja Amarela/Vermelha/Preta:</label><br><br>
	
			<label for="descricaoId">Descrição:</label>
			<input type="text" id="descricaoId" name="descricao" value="<%= remedio.getDescricao() %>"><br><br>

			<input type="submit" value="Atualizar" name="operacao">
	
	</form>


</body>
</html>