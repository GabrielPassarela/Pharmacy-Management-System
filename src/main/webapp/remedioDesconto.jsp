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
  Double status = (Double) request.getAttribute("status");

  if (remedio != null && status != null) {
%>
    <table border="1">
      <tr>
        <td>Nome:</td>
        <td><%= remedio.getNome() %></td>
      </tr>
      

      <tr>
        <td>Preço Original:</td>
        <td>R$ <%= String.format("%.2f", remedio.getPreco()) %></td>
      </tr>
      <tr>
        <td>Preço com Desconto:</td>
        <td>R$ <%= String.format("%.2f", remedio.calcularDesconto()) %></td>
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
