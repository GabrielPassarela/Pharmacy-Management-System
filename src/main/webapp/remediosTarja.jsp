<%@ page import="model.Remedio" %>
    <link rel="stylesheet" href="farmaciacss.css">

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Status do Remédio</title>
</head>
<body>

<h2>Status do Remédio</h2>

<%
  Remedio remedio = (Remedio) request.getAttribute("remedio");
  String status = (String) request.getAttribute("status");

  if (remedio != null && status != null) {
%>
    <table border="1">
      <tr>
        <td>Nome:</td>
        <td><%= remedio.getNome() %></td>
      </tr>
      <tr>
        <td>Tarja:</td>
        <td><%= remedio.getTarja() %></td>
      </tr>
      <tr>
        <td>Receita Necessária:</td>
        <td><%= remedio.verificarTarja() %></td>
      </tr>
  
    </table>
<%
  } else {
%>
    <p>Remédio não encontrado.</p>
<%
  }
%>

<br>
<a href="FarmaciaController?operacao=listar">
  <button>Voltar à lista</button>
</a>

</body>
</html>
