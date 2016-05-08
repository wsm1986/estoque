<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Detalhes do Produto</title>
</head>
<body>
	<c:import url="cabecalho.jsp" />
	Detalhar ID:
	<input value="${produto.id}" disabled="disabled" />
	<br /> Descrição:
	<input value="${produto.descricao}" disabled="disabled" />
	<br /> Quantidade:
	<input value="${produto.quantidade}" disabled="disabled" />
	<br />

	<h2>Lista de Movimentações</h2>
	<c:forEach items="${produto.movimentacoes}" var="mov">
		<li>${mov.tipo} -${mov.quantidade} - <fmt:formatDate value="${mov.data.time }" pattern="dd/MM/yyyy - hh:mm"/> </li>
	</c:forEach>
		<c:import url="rodape.jsp" />
</body>
</html>