<%@ page import="java.util.ArrayList, model.Remedio" %>
    <link rel="stylesheet" href="farmaciacss.css">

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Remédios</title>
	</head>
	<body>
		<h3>Lista de Remédios</h3>
		
		<% ArrayList<Remedio> listaRemedios = (ArrayList<Remedio>) request.getAttribute("bancoFarmacia");
		%>
		
		<table border="1">
		<tr>
		<th>Nome: </th>					
		<th>Preço: </th>
		<th>Data de vencimento: </th>		
		<th>Tarja: </th>
		<th>Descrição: </th></tr>
		<%
			for(Remedio remedio : listaRemedios){
				out.println("<tr>");
				out.println("<td>" + remedio.getNome()+"</td>");
				out.println("<td>" + remedio.getPreco()+"</td>");
				out.println("<td>"+remedio.getDataVencimento()+"</td>");
				out.println("<td>"+remedio.getTarja()+"</td>");
				out.println("<td>"+remedio.getDescricao()+"</td>");
				out.println("<td><a href = \"FarmaciaController?operacao=Buscar&simbolo=lixeira&id=" + remedio.getId()
				+ "\"> <img src=\"lixeira.png\"height=\"20px\"></a></td>");
				
				out.println("<td><a href = \"FarmaciaController?operacao=Buscar&simbolo=editar&id=" + remedio.getId()
				+ "\"> <img src=\"editar.png\"height=\"20px\"></a></td>");
				
				out.println("<td><a href = \"FarmaciaController?operacao=Vencimento&simbolo=lixeira&id=" + remedio.getId()
				+ "\"> <img src=\"vencimento.png\"height=\"20px\"></a></td>");
				out.println("<td><a href = \"FarmaciaController?operacao=Tarja&simbolo=lixeira&id=" + remedio.getId()
				+ "\"> <img src=\"tarja.png\"height=\"20px\"></a></td>");
				out.println("<td><a href = \"FarmaciaController?operacao=Desconto&simbolo=lixeira&id=" + remedio.getId()
				+ "\"> <img src=\"desconto.png\"height=\"20px\"></a></td>");
				
				out.println("</tr>");
				
				

		}
			%>
		</table>
		
		<p><a href="index.html">Inicio</a></p>
	</body>
</html>