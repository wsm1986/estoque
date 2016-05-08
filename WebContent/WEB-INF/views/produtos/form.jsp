<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cadastro de Produto</title>
</head>
<body>
	<c:import url="cabecalho.jsp" />
	Cadastro
	<form:form action="salvar" method="post" commandName="produto">
		Descrição:  
			<form:input path="descricao" />
			<form:errors path="descricao" />
			<br /> 
		Quantidade: 
			<form:input path="quantidade" />
			<form:errors path="quantidade" />
			<br />
		<input type="submit" value="Salvar">
	</form:form>
	<c:import url="rodape.jsp" />
</body>
</html>