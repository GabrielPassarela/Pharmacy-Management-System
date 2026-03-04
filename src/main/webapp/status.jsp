<%@ page language="java" contentType="text/html; charset=UTF-8"


    pageEncoding="UTF-8"%>
<!DOCTYPE html>
    <link rel="stylesheet" href="farmaciacss.css">

<html>
<head>
<meta charset="UTF-8">
<title>A</title>
</head>
<body>
<h1>Status Remedio</h1>

<%
	boolean status = (boolean) request.getAttribute("status");
String operacao = (String) request.getAttribute("operacao");
	String mensagem;

	if(status)
		mensagem="O remédio foi " + operacao + " com sucesso!";
	else
		mensagem = "Erro! A tarefa nao foi " + operacao +".";
%>

	<p><%= mensagem %></p>
	

		<p><a href="index.html">Inicio</a></p>

   	</body>
</html>