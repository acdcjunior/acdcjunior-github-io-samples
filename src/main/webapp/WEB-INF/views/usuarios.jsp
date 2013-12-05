<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!DOCTYPE html>
<html>
<head>
<title>Piloto - Usuarios</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
	<h1>Usuario Cadastrados</h1>
	<table>
		<thead>
			<tr>
				<td>ID</td>
				<td>Nome</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="usuario" items="${usuarios}">
				<tr>
					<td>${usuario.id}</td>
					<td>${usuario.nome}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>